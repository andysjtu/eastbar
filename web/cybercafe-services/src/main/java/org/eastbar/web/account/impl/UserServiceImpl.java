/********************************************************************************
 *                  上海交通大学-鹏越惊虹信息技术发展有限公司                       *
 *                          Copyright © 2003-2014                               *
 ********************************************************************************/
package org.eastbar.web.account.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.eastbar.web.*;
import org.eastbar.web.account.UserService;
import org.eastbar.web.account.biz.UserBO;
import org.eastbar.web.account.biz.UserRoleBO;
import org.eastbar.web.account.dao.RoleDao;
import org.eastbar.web.account.dao.UserDao;
import org.eastbar.web.account.dao.UserRoleDao;
import org.eastbar.web.account.entity.User;
import org.eastbar.web.account.entity.UserRole;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2014年08月17
 * @time 下午3:35
 * @description :
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserBO get(Integer id) {
        UserBO ubo = new UserBO();
        try {
            BeanUtils.copyProperties(ubo,userDao.getUser(id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return ubo;
    }

    @Override
    @Transactional
    public Boolean save(UserBO userBO) {
        try {
            User user=new User();
            BeanUtils.copyProperties(user, userBO);
            user.setCreatetime(Times.now());
            rebuildPW(user,userBO.getPassword());

            userDao.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByLoginName(String loginName) {
        return userDao.findByLoginName(loginName);
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        try {
            userDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public PageInfo getAllUser(UserBO userBO){
        try {
            //PageHelper.startPage(userBO.getPage(),userBO.getRows());
            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(userBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitorCode",monitorCodes.get(0));
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);

            Integer startRow = userBO.getPage() > 0 ? (userBO.getPage() - 1) * userBO.getRows() : 0;
            re.put("startRow",startRow);
            re.put("endRow",startRow + userBO.getRows() * (userBO.getPage() > 0 ? 1 : 0));

            List<User> list = userDao.getAllUser(re);
            for(int i=0;i<list.size();i++){
               if(list.get(i).getRoles().size()>0){
                Integer roleId=list.get(i).getRoles().get(0).getId();
                List<Monitor> monitors=roleDao.getRoleMonitor(roleId).getMonitor();
                list.get(i).getRoles().get(0).setMonitor(monitors);
               }
            }
            userBO.setListing(list);
            userBO.setTotal(userDao.getAllUserCount(re));
            return userBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserBO getUserRole(Integer id) {
        UserBO ubo = new UserBO();
        try {
            BeanUtils.copyProperties(ubo,userDao.getUserRole(id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return ubo;
    }

    @Override
    public Boolean update(UserBO userBO) {
        User user=new User();
        try {
            BeanUtils.copyProperties(user,userBO);
            rebuildPW(user,userBO.getPassword());
            userDao.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteMany(String[] ids) {
        UserRole userRole=new UserRole();
        try {
            for(int i=0;i<ids.length;i++){
                Integer id=Integer.parseInt(ids[i]+"");
                userRole.setUserId(id);
                userRoleDao.delete(userRole);
                userDao.delete(id);

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteRoleAndUser(UserRoleBO userRoleBO) {
        UserRole userRole=new UserRole();
        try{
            BeanUtils.copyProperties(userRole,userRoleBO);
            userRoleDao.delete(userRole);
            userDao.delete(userRole.getUserId());
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateLoginInfo(User user) {
        try {
            userDao.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * 在保存用户时,发送用户修改通知消息, 由消息接收者异步进行较为耗时的通知邮件发送.
     *
     * 如果企图修改超级用户,取出当前操作员用户,打印其信息然后抛出异常.
     *
     */
    private User rebuildPW(User user,String plainPassword) {

        if (isSupervisor(user)) {
            logger.warn("操作员{}尝试修改超级管理员用户", ShiroCustomUtils.getCurrentUserName());
            throw new ServiceException("不能修改超级管理员用户");
        }

        // 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
        if (StringUtils.isNotBlank(plainPassword)) {
            entryptPassword(user,plainPassword);
        }
        return user;
    }

    /**
     * 判断是否超级管理员.
     */
    private boolean isSupervisor(User user) {
        return ((user.getId() != null) && (user.getId() == 1));
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user,String plainPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }


}

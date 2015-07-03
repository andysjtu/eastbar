/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.web.account;

import org.eastbar.web.PR2Json;
import org.eastbar.web.PageInfo;
import org.eastbar.web.account.RolePermissionService;
import org.eastbar.web.account.RoleService;
import org.eastbar.web.account.UserRoleService;
import org.eastbar.web.account.UserService;
import org.eastbar.web.account.biz.PermissionBO;
import org.eastbar.web.account.biz.RoleBO;
import org.eastbar.web.account.biz.UserBO;
import org.eastbar.web.account.biz.UserRoleBO;
import org.eastbar.web.account.entity.Role;
import org.eastbar.web.account.entity.User;
import org.eastbar.web.ipc.MonitorService;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2014年09月28
 * @time 下午3:26
 * @description :
 */
@Controller
@RequestMapping("/account")
public class AccountController {//add

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private MonitorService monitorService;


    /**
     * 进入用户列表页面
     * @param userBO
     * @return
     */
    @RequestMapping("/user")
    public String user(@ModelAttribute UserBO userBO){
        return "account/userList";
    }

    /**
     * 页面异步调用json返回用户列表的数据
     * @param userBO
     * @return
     */
    @RequestMapping("/userjson")
    @ResponseBody
    public String getUserList(@ModelAttribute UserBO userBO){

        String json = null;
        try{
            PageInfo pr = userService.getAllUser(userBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * 进入用户添加页面
     * @param model
     * @return
     */
    @RequestMapping("/intoUserAdd")
    public String intoUserAdd(Model model){
        List<Monitor> monitors=monitorService.getMonitors();
        model.addAttribute("monitors",monitors);
        return "account/userAdd";
    }

    @RequestMapping("/getByName/{name}")
    @ResponseBody
    public Boolean getByName(@PathVariable String name){
        User user=userService.findByLoginName(name);
        if(user!=null){//user不为空，则说明存在该用户
            return true;
        }else{//user为空，则说明不能存在改用户，返回0
            return false;
        }

    }

    /**
     * 页面异步调用用户角色列表数据
     * @param monitor
     * @return
     */
    @RequestMapping("/getRoles/{monitor}")
    @ResponseBody
    public String getRoles(@PathVariable String monitor){
        String json=null;
        List<Role> roles=roleService.getRoles(monitor);
        json = PR2Json.go(roles);
        return json;
    }

    /**
     * 提交添加用户信息并返回用户列表页面
     * @param userBO
     * @param roleId
     * @param modle
     * @return
     */
    @RequestMapping("/submitUserAdd")
    public String submitUserAdd(@ModelAttribute UserBO userBO,Integer roleId,RedirectAttributes modle){
         try{
            userService.save(userBO);
            UserRoleBO userRoleBO=new UserRoleBO();
         //添加用户时判断是否同时给用户添加了角色，roleId==-1  代表没有添加角色
         if(roleId!=-1){ //roleId!=1，即给用户新添加了角色，下面执行添加角色操作
            userRoleBO.setUserId(userService.findByLoginName(userBO.getLoginName()).getId());
            userRoleBO.setRoleId(roleId);
            userRoleService.save(userRoleBO);
         }
            modle.addFlashAttribute("loadmsg","保存成功!");
         }catch(Exception e){
               e.printStackTrace();
               modle.addFlashAttribute("loadmsg","保存失败!");
         }
         return "redirect:/account/user#_1";
    }

    /**
     * 进入用户修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/intoUserEdit/{id}")
    public String intoUserEdit(@PathVariable Integer id,Model model){
        UserBO userBO=userService.get(id);
        model.addAttribute("userBO",userBO);
        return "account/userEdit";
    }

    /**
     * 提交修改的用户信息，返回用户列表
     * @param userBO
     * @param modle
     * @return
     */
    @RequestMapping("/submitUserEdit")
    public String submitUserEdit(@ModelAttribute UserBO userBO,RedirectAttributes modle){
        if(userService.update(userBO)){
            modle.addFlashAttribute("loadmsg","修改成功!");
        }else{
            modle.addFlashAttribute("loadmsg","修改失败!");
        }
        return "redirect:/account/user#_1";
    }

    /**
     * 进入某个用户的角色修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/intoUserRoleEdit/{id}")
    public String intoRoleUserEdit(@PathVariable Integer id,Model model){

        UserBO userBO=userService.getUserRole(id);
        List<Role> roles=roleService.getRoles();
        List<Monitor> monitors=monitorService.getMonitors();
        model.addAttribute("monitors",monitors);
        model.addAttribute("userBO", userBO);
        model.addAttribute("roles",roles);
        return "account/userRoleEdit";
    }

    /**
     * 提交某个用户角色修改信息
     * @param userRoleBO
     * @param modle
     * @return
     */
    @RequestMapping("/submitUserRoleEdit")
    public String submitRoleUserEdit(@ModelAttribute UserRoleBO userRoleBO,RedirectAttributes modle){
        if(userRoleService.edit(userRoleBO)){
            modle.addFlashAttribute("loadmsg","修改成功!");
        }else{
            modle.addFlashAttribute("loadmsg","修改失败!");
        }
        return "redirect:/account/user#_1";
    }

    /**
     *进入用户查找页面
     * @return
     */
    @RequestMapping("/intoRoleUserSearch")
    public String intoRoleUserSearch(){
        return "account/userSearch";
    }

    /**
     * 删除用户
     * @param id
     * @param modle
     * @return
     */
    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable Integer id,RedirectAttributes modle){
        UserRoleBO userRoleBO=new UserRoleBO();
        userRoleBO.setUserId(id);
        if(userService.deleteRoleAndUser(userRoleBO)){
            modle.addFlashAttribute("loadmsg","删除成功!");
        }else{
            modle.addFlashAttribute("loadmsg","删除失败!");
        }
        return "redirect:/account/user#_1";
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @RequestMapping("/deleteManyUser/{ids}")
    @ResponseBody
    public Map<String, String> deleteManyUser(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        String[] sid=ids.split(",");
        if(userService.deleteMany(sid)){
            msg.put("msg","批量删除成功");
        }else{
            msg.put("msg","批量删除失败");
        }
        return msg;

    }

    /**
     * 进入角色列表页面
     * @return
     */
    @RequestMapping("/role")
    public String role(){
        return "account/roleList";
    }

    /**
     * 页面异步调用json返回角色列表数据
     * @param roleBO
     * @return
     */
    @RequestMapping("/rolejson")
    @ResponseBody
    public String getRoleList(@ModelAttribute RoleBO roleBO){
        String json = null;
        try{
            PageInfo pr = roleService.getAllRole(roleBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * 进入角色添加页面
     * @param model
     * @return
     */
    @RequestMapping("/intoRoleAdd")
    public String intoRoleAdd(Model model){
        Integer id= ShiroCustomUtils.getCurrentID();
        List<Monitor> monitors=roleService.showMonitors(id);
        model.addAttribute("monitors",monitors);
        return "account/roleAdd";
    }


    /**
     * 提交添加角色信息，返回角色列表
     * @param roleBO
     * @param modle
     * @return
     */
    @RequestMapping("/submitRoleAdd")
    public String submitRoleAdd(@ModelAttribute RoleBO roleBO,RedirectAttributes modle){
        if(roleService.save(roleBO)){
            modle.addFlashAttribute("loadmsg","保存成功!");
        }else{
            modle.addFlashAttribute("loadmsg","保存失败!");
        }
        return "redirect:/account/role#_1";
    }

    /**
     * 进入角色修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/intoRoleEdit/{id}")
    public String intoRoleEdit(@PathVariable Integer id,Model model){
        RoleBO roleBO=roleService.getRoleMonitor(id);
        Integer sid=ShiroCustomUtils.getCurrentID();
        List<Monitor> monitors=roleService.showMonitors(sid);
        model.addAttribute("monitors",monitors);
        model.addAttribute("roleBO",roleBO);
        return "account/roleEdit";
    }

    /**
     * 提交修改角色信息，返回角色列表页面
     * @param roleBO
     * @param modle
     * @return
     */
    @RequestMapping("/submitRoleEdit")
    public String submitRoleEdit(@ModelAttribute RoleBO roleBO,RedirectAttributes modle){
        if(roleService.update(roleBO)){
            modle.addFlashAttribute("loadmsg","修改成功!");
        }else{
            modle.addFlashAttribute("loadmsg","修改失败!");
        }
        return "redirect:/account/role#_1";
    }

    /**
     * 删除角色
     * @param id
     * @param modle
     * @return
     */
    @RequestMapping("/removeRole/{id}")
    public String removeRole(@PathVariable Integer id,RedirectAttributes modle){
        if(roleService.delete(id)){
            modle.addFlashAttribute("loadmsg","删除成功!");
        }else{
            modle.addFlashAttribute("loadmsg","删除失败!");
        }
        return "redirect:/account/role#_1";
    }

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    @RequestMapping("/deleteManyRole/{ids}")
    @ResponseBody
    public Map<String, String> deleteManyRole(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        String[] sid=ids.split(",");
        if(roleService.deleteMany(sid)){
            msg.put("msg","批量删除成功");
        }else{
            msg.put("msg","批量删除失败");
        }
        return msg;
    }

    /**
     * 进入某个角色权限修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/intoPermissionsEdit/{id}")
    public String intoPermissionsEdit(@PathVariable Integer id,Model model){
        RoleBO roleBO=roleService.get(id);
        model.addAttribute("roleBO",roleBO);
        return "account/permissionsEdit";
    }

    /**
     * 提交某个角色修改信息,返回角色列表页面
     * @param permissionBO
     * @param modle
     * @return
     */
    @RequestMapping("/submitPermissionsEdit")
    public String submitPermissionsEdit(@ModelAttribute PermissionBO permissionBO,RedirectAttributes modle){
        String permission=permissionBO.getPermission();//获取页面选中的所有权限
        String[] permissions=null;
        if(!"".equals(permission) && permission!=null){
            permissions=permission.split(",");
        }
        if(rolePermissionService.updatePermissions(permissions,permissionBO.getId())){
            modle.addFlashAttribute("loadmsg","修改权限成功!");
        }else{
            modle.addFlashAttribute("loadmsg","修改权限失败!");
        }
        return "redirect:/account/role#_1";
    }

    /**
     * 进入修改密码页面
     * @param model
     * @return
     */
    @RequestMapping("/intoPasswordEdit")
    public String passwordEdit(Model model){
        UserBO userBO=userService.get(ShiroCustomUtils.getCurrentID());
        model.addAttribute("userBO",userBO);
        return "account/passwordEdit";
    }

    /**
     * 提交修改密码信息，返回用户列表页面
     * @param userBO
     * @param modle
     * @return
     */
    @RequestMapping("/submitPasswordEdit")
    public String submitPasswordEdit(@ModelAttribute UserBO userBO,RedirectAttributes modle){
        if(userService.update(userBO)){
            modle.addFlashAttribute("loadmsg","修改密码成功!");
        }else{
            modle.addFlashAttribute("loadmsg","修改密码失败!");
        }
        return "redirect:/account/user#_1";
    }


}

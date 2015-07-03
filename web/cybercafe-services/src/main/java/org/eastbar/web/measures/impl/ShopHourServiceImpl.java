/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures.impl;

import com.sun.corba.se.spi.activation._ServerManagerStub;
import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Times;
import org.eastbar.web.measures.ShopHourService;
import org.eastbar.web.measures.biz.ShopHourBO;
import org.eastbar.web.measures.dao.ManageRuleDao;
import org.eastbar.web.measures.dao.ShopHourDao;
import org.eastbar.web.measures.entity.ManageRule;
import org.eastbar.web.measures.entity.ShopHour;
import org.eastbar.web.version.Versions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:40
 * @description :
 */
@Service
@Transactional
public class ShopHourServiceImpl implements ShopHourService {

    @Autowired
    private ShopHourDao shopHourdao;

    @Autowired
    private ManageRuleDao manageRuleDao;

    @Override
    public ShopHourBO getShopHour(Integer id){
        try {
            ShopHourBO shopHourBO = new ShopHourBO();
            BeanUtils.copyProperties(shopHourBO, shopHourdao.getShopHour(id));
            return shopHourBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ShopHour> getAllShopHour(){
        return shopHourdao.getAllShopHour();
    }


    @Override
    @Transactional
    public Boolean delete(Integer id){
       try{
           ManageRule manageRule=manageRuleDao.get();
           ShopHour shopHour=shopHourdao.getShopHour(id);
           shopHour.setOperation("remove");
           shopHour.setDeleteFlag(1);
           shopHour.setIsPub(0);
           shopHour.setVersion(Versions.computeVersion(manageRule.getOperHourVer()));
           shopHour.setVerNum(manageRule.getHourVerNum()+1);
           shopHourdao.update(shopHour);
           manageRule.setHourVerNum(shopHour.getVerNum());
           manageRule.setOperHourVer(shopHour.getVersion());
           manageRule.setUpdateTime(Times.now());
           manageRuleDao.update(manageRule);
           return true;
       }
       catch(Exception e){
            e.printStackTrace();;
       }
       return false;
    }

    @Override
    @Transactional
    public Boolean update(ShopHourBO shopHourBO){
        try{
            ShopHour shopHour=new ShopHour();
            ManageRule manageRule=manageRuleDao.get();
            BeanUtils.copyProperties(shopHour, shopHourBO);
            shopHour.setVersion(Versions.computeVersion(manageRule.getOperHourVer()));
            shopHour.setOperation("edit");
            shopHour.setVerNum(manageRule.getHourVerNum()+1);
            shopHourdao.update(shopHour);
            manageRule.setOperHourVer(shopHour.getVersion());
            manageRule.setHourVerNum(shopHour.getVerNum());
            manageRule.setUpdateTime(Times.now());
            manageRuleDao.update(manageRule);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ShopHour> getShopHourByType(Integer shopHourType) {
        return shopHourdao.getShopHourByType(shopHourType);
    }

    @Override
    public Boolean save(ShopHourBO shopHourBO) {
       try{
           ShopHour shopHour=new ShopHour();
           ManageRule manageRule=manageRuleDao.get();
           List<ShopHourBO> shopHourBOs=shopHourBO.getShopHourBOList();
           if(shopHourBOs.size()>0){
               for(int i=0;i<shopHourBOs.size();i++){
                   ShopHourBO shopHourBO1=shopHourBOs.get(i);
                   BeanUtils.copyProperties(shopHour, shopHourBO1);
                   shopHour.setOperation("add");
                   if(shopHour.getIsPub()==null){
                       shopHour.setIsPub(0);
                   }
                   shopHour.setDeleteFlag(0);
                   if(i==0){//同时添加多条规则，他们共享一个版本号,即只有第一条记录会+1
                       shopHour.setVerNum(manageRule.getHourVerNum()+1);
                       shopHour.setVersion(Versions.computeVersion(manageRule.getOperHourVer()));
                       manageRule.setHourVerNum(shopHour.getVerNum());
                       manageRule.setOperHourVer(shopHour.getVersion());
                       manageRule.setUpdateTime(Times.now());
                       manageRuleDao.update(manageRule);
                   }else{//同时添加的第二条规则，还是跟第一条的版本一样
                       shopHour.setVerNum(manageRule.getHourVerNum());
                       shopHour.setVersion(manageRule.getOperHourVer());
                   }
                   if("".equals(shopHour.getStartDate())){
                       shopHour.setStartDate("");
                       shopHour.setEndDate("");
                   }
                   shopHourdao.save(shopHour);
               }
           }
           return true;
       }catch(Exception e){
           e.printStackTrace();
       }
         return false;
    }

    @Override
    public List<ShopHour> getSome(ShopHourBO shopHourBO) {
        try {
            ShopHour shopHour = new ShopHour();
            BeanUtils.copyProperties(shopHour, shopHourBO);
            return shopHourdao.getSome(shopHour);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean publish(Integer id) {
        try{
            ShopHour shopHour = shopHourdao.getShopHour(id);
            shopHour.setIsPub(1);
           // shopHour.setVersion(Times.now().substring(0,10).replace("-", "."));
            shopHour.setStartDate(null);
            shopHour.setEndDate(null);
            shopHourdao.update(shopHour);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean releaseMany(String[] ids) {
        try {
            for(int i=0;i<ids.length;i++){
                Integer id=Integer.parseInt(ids[i]+"");
                ShopHour  shopHour=shopHourdao.getShopHour(id);
                shopHour.setIsPub(1);
                shopHourdao.update(shopHour);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.redis.core;

import com.fasterxml.jackson.annotation.JsonValue;
import org.eastbar.common.redis.SiteRedisService;
import org.eastbar.common.redis.util.Po2Json;
import org.eastbar.common.redis.util.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springside.modules.mapper.JsonMapper;

import java.util.*;

/**
 * @author cindy-jia
 * @date 2015年05月11
 * @time 上午10:32
 * @description :主要包含场所端对redis的操作，包括根据场所提供的版本号和编码获取相应的版本列表，以及获取redis最新版本号和已更新的本版号
 */
@Service
public class SiteRedisServiceImpl implements SiteRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据场所提供的编码和版本号返回需更新的list
     * @param siteCode
     * @param version
     * @return
     * @throws Exception
     */
    @Override
    public String returnProgVersionList(String siteCode, Integer version) throws Exception {
        String list="{";
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        String hashValue=hashOperations.get(Strategy.PROG_CODE_VERSION_INDEX, version + "");
        String strs=hashValue.replace("[","").replace("]", "");
        String[] str=strs.split(",");
        ValueOperations valueOperations=redisTemplate.opsForValue();
        String add="";
        String edit="";
        String remove="";
        String addProgList="";
        String editProgList="";
        String removeProgList="";
        String s3=siteCode.substring(0,3);
        String s4=siteCode.substring(0,4);
        String s6=siteCode.substring(0,6);
        for(int i=0;i<str.length;i++){
            String value=str[i];
            int length=value.trim().length();

            switch (length){
                case 3:
                    if(s3.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case 4:
                    if(s4.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case 6:
                    if(s6.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case  10:
                    if(siteCode.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.BANNEDPROG+"."+value.trim()+"."+version+".remove");
                    }
                    break;
            }
            ;

            if(!"".equals(add) && add!=null){
                add=add.substring(1,add.length()-1);
                addProgList+=add+",";
            }
            if(!"".equals(edit) && edit!=null){
                edit=edit.substring(1,edit.length()-1);
                editProgList+=add+",";
            }
            if(!"".equals(remove) && remove!=null){
                remove=remove.substring(1,remove.length()-1);
                removeProgList+=add+",";
            }
        }

        if(addProgList.equals("") && editProgList.equals("") && removeProgList.equals("")){
            list="";
        }else{
            list+="\"verNum\":"+version+",";
            if(!"".equals(addProgList)){
                addProgList=addProgList.substring(0,addProgList.length()-1);
                list+="\"add\":["+addProgList+"]";
            }else{
                list+="\"add\":[]";
            }
            if(!"".equals(editProgList)){
                editProgList=editProgList.substring(0,editProgList.length()-1);
                list+=",\"edit\":["+editProgList+"]";
            }else{
                list+=",\"edit\":[]";
            }
            if(!"".equals(removeProgList)){
                removeProgList=removeProgList.substring(0,removeProgList.length()-1);
                list+=",\"remove\":["+removeProgList+"]";
            }else{
                list+=",\"remove\":[]";
            }
            list+="}";
        }
        return list;
    }

    /**
     * 根据场所提供的编码和版本号返回需更新的list
     * @param siteCode
     * @param version
     * @return
     * @throws Exception
     */
    @Override
    public String returnUrlVersionList(String siteCode, Integer version) throws Exception {
        String list="{";
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        String hashValue=hashOperations.get(Strategy.URL_CODE_VERSION_INDEX, version + "");
        String strs=hashValue.replace("[","").replace("]", "");
        String[] str=strs.split(",");
        ValueOperations valueOperations=redisTemplate.opsForValue();
        String add="";
        String edit="";
        String remove="";
        String addUrlList="";
        String editUrlList="";
        String removeUrlList="";
        String s3=siteCode.substring(0,3);
        String s4=siteCode.substring(0,4);
        String s6=siteCode.substring(0,6);
        for(int i=0;i<str.length;i++){
            String value=str[i];
            int length=value.trim().length();

            switch (length){
                case 3:
                    if(s3.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case 4:
                    if(s4.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case 6:
                    if(s6.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case  10:
                    if(siteCode.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.BANNEDURL+"."+value.trim()+"."+version+".remove");
                    }
                    break;
            }
            ;

            if(!"".equals(add) && add!=null){
                add=add.substring(1,add.length()-1);
                addUrlList+=add+",";
            }
            if(!"".equals(edit) && edit!=null){
                edit=edit.substring(1,edit.length()-1);
                editUrlList+=add+",";
            }
            if(!"".equals(remove) && remove!=null){
                remove=remove.substring(1,remove.length()-1);
                removeUrlList+=add+",";
            }
        }

        if(addUrlList.equals("") && editUrlList.equals("") && removeUrlList.equals("")){
            list="";
        }else{
            list+="\"verNum\":"+version+",";
            if(!"".equals(addUrlList)){
                addUrlList=addUrlList.substring(0,addUrlList.length()-1);
                list+="\"add\":["+addUrlList+"]";
            }else{
                list+="\"add\":[]";
            }
            if(!"".equals(editUrlList)){
                editUrlList=editUrlList.substring(0,editUrlList.length()-1);
                list+=",\"edit\":["+editUrlList+"]";
            }else{
                list+=",\"edit\":[]";
            }
            if(!"".equals(removeUrlList)){
                removeUrlList=removeUrlList.substring(0,removeUrlList.length()-1);
                list+=",\"remove\":["+removeUrlList+"]";
            }else{
                list+=",\"remove\":[]";
            }
            list+="}";
        }
        return list;
    }

    /**
     * 根据场所提供的编码和版本号返回需更新的list
     * @param siteCode
     * @param version
     * @return
     * @throws Exception
     */
    @Override
    public String returnKeyWordVersionList(String siteCode, Integer version) throws Exception {
        String list="{";
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        String hashValue=hashOperations.get(Strategy.KEYWORD_CODE_VERSION_INDEX, version + "");
        String strs=hashValue.replace("[","").replace("]", "");
        String[] str=strs.split(",");
        ValueOperations valueOperations=redisTemplate.opsForValue();
        String add="";
        String edit="";
        String remove="";
        String addKeywordList="";
        String editKeywordList="";
        String removeKeywordList="";
        String s3=siteCode.substring(0,3);
        String s4=siteCode.substring(0,4);
        String s6=siteCode.substring(0,6);
        for(int i=0;i<str.length;i++){
            String value=str[i];
            int length=value.trim().length();

            switch (length){
                case 3:
                    if(s3.equals(value.trim())){
                          add =(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".add");
                          edit =(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".edit");
                          remove=(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case 4:
                    if(s4.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case 6:
                    if(s6.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case  10:
                    if(siteCode.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.KEYWORD+"."+value.trim()+"."+version+".remove");
                    }
                    break;
            }
            ;

            if(!"".equals(add) && add!=null){
                add=add.substring(1,add.length()-1);
                addKeywordList+=add+",";
            }
            if(!"".equals(edit) && edit!=null){
                edit=edit.substring(1,edit.length()-1);
                editKeywordList+=add+",";
            }
            if(!"".equals(remove) && remove!=null){
                remove=remove.substring(1,remove.length()-1);
                removeKeywordList+=add+",";
            }
        }

        if(addKeywordList.equals("") && editKeywordList.equals("") && removeKeywordList.equals("")){
            list="";
        }else{
            list+="\"verNum\":"+version+",";
            if(!"".equals(addKeywordList)){
                addKeywordList=addKeywordList.substring(0,addKeywordList.length()-1);
                list+="\"add\":["+addKeywordList+"]";
            }else{
                list+="\"add\":[]";
            }
            if(!"".equals(editKeywordList)){
                editKeywordList=editKeywordList.substring(0,editKeywordList.length()-1);
                list+=",\"edit\":["+editKeywordList+"]";
            }else{
                list+=",\"edit\":[]";
            }
            if(!"".equals(removeKeywordList)){
                removeKeywordList=removeKeywordList.substring(0,removeKeywordList.length()-1);
                list+=",\"remove\":["+removeKeywordList+"]";
            }else{
                list+=",\"remove\":[]";
            }
            list+="}";
        }
        return list;
    }

    /**
     * 根据场所提供的编码和版本号返回需更新的list
     * @param siteCode
     * @param version
     * @return
     * @throws Exception
     */
    @Override
    public String returnSpecialCustomerVersionList(String siteCode, Integer version) throws Exception {
        String list="{";
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        String hashValue=hashOperations.get(Strategy.SPECIALcUSTOMER_CODE_VERSION_INDEX, version + "");
        String strs=hashValue.replace("[","").replace("]", "");
        String[] str=strs.split(",");
        ValueOperations valueOperations=redisTemplate.opsForValue();
        String add="";
        String edit="";
        String remove="";
        String addSpecialList="";
        String editSpecialList="";
        String removeSpecialList="";
        String s3=siteCode.substring(0,3);
        String s4=siteCode.substring(0,4);
        String s6=siteCode.substring(0,6);
        for(int i=0;i<str.length;i++){
            String value=str[i];
            int length=value.trim().length();

            switch (length){
                case 3:
                    if(s3.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case 4:
                    if(s4.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case 6:
                    if(s6.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".remove");
                    }
                    break;
                case  10:
                    if(siteCode.equals(value.trim())){
                        add =(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".add");
                        edit =(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".edit");
                        remove=(String)valueOperations.get(Strategy.SPECIALCUSTOMER+"."+value.trim()+"."+version+".remove");
                    }
                    break;
            }
            ;

            if(!"".equals(add) && add!=null){
                add=add.substring(1,add.length()-1);
                addSpecialList+=add+",";
            }
            if(!"".equals(edit) && edit!=null){
                edit=edit.substring(1,edit.length()-1);
                editSpecialList+=add+",";
            }
            if(!"".equals(remove) && remove!=null){
                remove=remove.substring(1,remove.length()-1);
                removeSpecialList+=add+",";
            }
        }

        if(addSpecialList.equals("") && editSpecialList.equals("") && removeSpecialList.equals("")){
            list="";
        }else{
            list+="\"verNum\":"+version+",";
            if(!"".equals(addSpecialList)){
                addSpecialList=addSpecialList.substring(0,addSpecialList.length()-1);
                list+="\"add\":["+addSpecialList+"]";
            }else{
                list+="\"add\":[]";
            }
            if(!"".equals(editSpecialList)){
                editSpecialList=editSpecialList.substring(0,editSpecialList.length()-1);
                list+=",\"edit\":["+editSpecialList+"]";
            }else{
                list+=",\"edit\":[]";
            }
            if(!"".equals(removeSpecialList)){
                removeSpecialList=removeSpecialList.substring(0,removeSpecialList.length()-1);
                list+=",\"remove\":["+removeSpecialList+"]";
            }else{
                list+=",\"remove\":[]";
            }
            list+="}";
        }
        return list;
    }

    /**
     * 根据提供的信息，返回最新的版本编号
     * @param meaurea
     * @return
     * @throws Exception
     */
    @Override
    public String returnLastedVersion(String meaurea) throws Exception {

        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        String version=hashOperations.get(Strategy.LASTED_VERSIONS,meaurea);
        return version;
    }

    /**
     * 根据提供的信息，返回最近更新的版本号
     * @param meaure
     * @return
     * @throws Exception
     */
    @Override
    public String returnUpdatedVersion(String meaure) throws Exception {
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        String version=hashOperations.get(Strategy.UPDATED_VERSIONS,meaure);
        return version;
    }


}

package org.eastbar.web;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月14
 * @time 下午2:52
 * @description : MD5
 */
public class Md5Util {
       
	
		 public static String getMD5Str(String str) {   
	        MessageDigest messageDigest = null;
	        try {   
	            messageDigest = MessageDigest.getInstance("MD5");    
	            messageDigest.reset();   	   
	            messageDigest.update(str.getBytes("UTF-8"));   
	        } catch (NoSuchAlgorithmException e) {   
	            System.out.println("NoSuchAlgorithmException caught!");   
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {   
	            e.printStackTrace();   
	        }	   
	        byte[] byteArray = messageDigest.digest();	   
	        StringBuffer md5StrBuff = new StringBuffer();  	   
	        for (int i = 0; i < byteArray.length; i++) {               
	            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)   
	                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));   
	            else   
	                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));   
	        }   	   
	        return md5StrBuff.toString();   
	    }   
}


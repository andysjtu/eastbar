///**
// * 上海交通大学-鹏越惊虹信息技术发展有限公司
// *         Copyright © 2003-2014
// */
//package org.eastbar.web.service;
//
//import com.pengyue.eastbar.service.RMIService;
//import org.springframework.stereotype.Service;
//
//import java.io.BufferedInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
///**
// * @author C.lins@aliyun.com
// * @date 2014年11月04
// * @time 下午5:01
// * @description :
// */
//@Service
//public class BerService implements RMIService {
//    @Override
//    public int Shutdown(String s, String s2) {
//        Random ra = new Random();
//        int num = ra.nextInt(5);
//        System.out.println("调用了Shutdown(String s, String s2)方法");
//        System.out.println("产生的随机数是：" + num);
//        return num;
//    }
//
//    @Override
//    public int Restart(String s, String s2) {
//        Random ra = new Random();
//        int num = ra.nextInt(5);
//        System.out.println("Restart(String s, String s2)方法");
//        System.out.println("产生的随机数是：" + num);
//        return num;
//    }
//
//    @Override
//    public int Locking(String s, String s2) {
//        Random ra = new Random();
//        int num = ra.nextInt(5);
//        System.out.println("调用了Locking(String s, String s2)方法");
//        System.out.println("产生的随机数是：" + num);
//        return num;
//    }
//
//    @Override
//    public int Unlock(String s, String s2) {
//        Random ra = new Random();
//        int num = ra.nextInt(5);
//        System.out.println("调用了Unlock(String s, String s2)方法");
//        System.out.println("产生的随机数是：" + num);
//        return num;
//    }
//
//    @Override
//    public byte[] Screenshot(String s, String s2) {
//
//        byte[] bytePic = new byte[1024];
//        String[] path= {"14","32","33"};
//        Random ra = new Random();
//        int num = ra.nextInt(3);
//        try {
//            File file = new File("D:/"+path[num]+".jpg");
//            BufferedInputStream bis = null;
//            ByteArrayOutputStream out = null;
//            try {
//                FileInputStream input = new FileInputStream(file);
//                bis = new BufferedInputStream(input, 1024);
//                byte[] bytes = new byte[1024];
//                out = new ByteArrayOutputStream();
//                int len;
//                while ((len = bis.read(bytes)) > 0) {
//                    out.write(bytes, 0, len);
//                }
//                bis.close();
//                bytePic = out.toByteArray();
//            } finally {
//                if (bis != null)
//                    bis.close();
//                if (out != null)
//                    out.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bytePic;
//    }
//
//    @Override
//    public int sendSpecialCustomer(String s) {
//        Random ra = new Random();
//        int num = ra.nextInt(5);
//        System.out.println("sendSpecialCustomer(String s)方法");
//        System.out.println("产生的随机数是：" + num);
//        return num;
//    }
//
//    @Override
//    public int sendKeyWord(String s){
//        Random ra = new Random();
//        int num = ra.nextInt(5);
//        System.out.println("sendKeyWord(String s)方法");
//        System.out.println("产生的随机数是：" + num);
//        return num;
//    }
//
//    @Override
//    public int sendBannedUrl(String s) {
//        Random ra = new Random();
//        int num = ra.nextInt(5);
//        System.out.println("sendBannedUrl(String s)方法");
//        System.out.println("产生的随机数是：" + num);
//        return num;
//    }
//
//    @Override
//    public int sendBannedProg(String s) {
//        Random ra = new Random();
//        int num = ra.nextInt(5);
//        System.out.println("sendBannedProg(String s)方法");
//        System.out.println("产生的随机数是：" + num);
//        return num;
//    }
//
//    @Override //status,totalSite,openSite,totalTerminal,totalAlarm,totalPunish
//    public Map<String, Map<String, Integer>> monirorCount() {
//        Random ra = new Random();
//        Map<String, Integer> moniror = new HashMap<>();
//        moniror.put("status", 0);
//        moniror.put("totalSite", ra.nextInt(386) + 1);
//        moniror.put("openSite", ra.nextInt(256) + 1);
//        moniror.put("totalTerminal", ra.nextInt(460) + 1);
//        moniror.put("totalAlarm", ra.nextInt(63) + 1);
//        moniror.put("totalPunish", ra.nextInt(35) + 1);
//        Map<String, Map<String, Integer>> monirors = new HashMap<>();
//        monirors.put("3101", moniror);
//        monirors.put("310101", moniror);
//        monirors.put("310102", moniror);
//        monirors.put("3102", moniror);
//        monirors.put("310201", moniror);
//        monirors.put("310202", moniror);
//        System.out.println("调用的方法是:Map<String, Map<String, String>> monirorCount()");
//        return monirors;
//    }
//
//
//    @Override//connectTerm,totalAlarm,totalPunish,installationRate
//    public Map<String, Map<String, Integer>> siteCount() {
//        Random ra = new Random();
//        Map<String, Integer> site = new HashMap<>();
//        site.put("activeCustomerCount", ra.nextInt(386) + 1);//connectTerm
//        site.put("totalAlarm", ra.nextInt(56) + 1);
//        site.put("totalPunish", ra.nextInt(35) + 1);
//        site.put("installationRate", ra.nextInt(75) + 1);//installationRate
//        Map<String, Map<String, Integer>> sites = new HashMap<>();
//        sites.put("3101010001", site);
//        sites.put("3101010002", site);
//        sites.put("3101010003", site);
//        System.out.println("调用的方法是:Map<String, Map<String, String>> siteCount");
//        return sites;
//    }
//
//    @Override
//    public Map<String, String> operationsCount(String s) {
//        Random ra = new Random();
//        Map<String, String> operation = new HashMap<>();
//        operation.put("freeHost", ra.nextInt(13) + 1 + "");
//        operation.put("useHost", ra.nextInt(49) + 1 + "");
//        operation.put("locking", ra.nextInt(5) + 1 + "");
//        operation.put("close", ra.nextInt(12) + 1 + "%");
//        return operation;
//    }
//}

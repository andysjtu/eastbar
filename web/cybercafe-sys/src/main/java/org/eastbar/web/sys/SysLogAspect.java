package org.eastbar.web.sys;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.eastbar.web.Times;
import org.eastbar.web.log.OperLogService;
import org.eastbar.web.log.biz.OperLogBO;
import org.eastbar.web.log.dao.OperLogDao;
import org.eastbar.web.log.entity.OperLog;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.syslog.SysLogService;
import org.eastbar.web.syslog.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 15-5-8.
 */
@Component
@Aspect
public class SysLogAspect {

    @Autowired
    private SysLogService syslogService;

    @Autowired
    private OperLogDao operLogDao;

    @Pointcut("execution(* org.eastbar.web.*.*Service.save(..))")
    public void addCall(){ }

    @Pointcut("execution(* org.eastbar.web.*.*Service.del*(..))")
    public void delCall(){ }

    @Pointcut("execution(* org.eastbar.web.*.*Service.update(..))")
    public void updateCall(){ }

    @Pointcut("execution(* org.eastbar.web.account.UserService.updateLoginInfo(..))")
    public void loginCall(){ }

    //关机操作
    @Pointcut("execution(* org.eastbar.web.*.*Service.shutdown(..))")
    public void shutDownCall(){}

    //重启操作
    @Pointcut("execution(* org.eastbar.web.*.*Service.restart(..))")
    public void restartCall(){}

    //锁定操作
    @Pointcut("execution(* org.eastbar.web.*.*Service.lock(..))")
    public void lockCall(){}

    //解锁操作
    @Pointcut("execution(* org.eastbar.web.*.*Service.unlock(..))")
    public void unlockCall(){}

    //截屏操作
    @Pointcut("execution(* org.eastbar.web.*.*Service.capture(..))")
    public void captureCall(){}




    public void calls(JoinPoint jp,String methodCall) throws Throwable{
        //当前用户ID
        int id = ShiroCustomUtils.getCurrentID();

        //方法名
        String methodName = jp.getSignature().getName();
        //判断参数是否为空
        if(jp.getArgs() == null){
            return;
        }
       String terminalMethods="shutdown,restart,lock,unlock,capture";


       //获取内容
       String content = adminOptionContent(jp.getArgs(),methodName);
	   String cl = jp.getTarget().getClass().toString();
	   String impl = cl.substring(cl.indexOf(".impl.")+6,cl.length());

       SysLog sysLog=new SysLog();
       sysLog.setUserId(id);
       sysLog.setCreateDate(Times.now());
       sysLog.setContent(impl + "  " + content);
       sysLog.setOperation(methodCall);
       syslogService.add(sysLog);
    }

    @AfterReturning(value = "addCall()")
    public void addLog(JoinPoint jp) throws Throwable{
        calls(jp,"add");
    }

    @AfterReturning(value = "updateCall()")
    public void updateLog(JoinPoint jp) throws Throwable{
        calls(jp,"edit");
    }

	@AfterReturning(value = "delCall()")
    public void delLog(JoinPoint jp) throws Throwable{
        calls(jp,"remove");
    }

    @AfterReturning(value = "loginCall()")
    public void loginLog(JoinPoint jp) throws Throwable{
        calls(jp,"login");
    }

    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值，
     * 将参数值拼接为操作内容
     */
    public String adminOptionContent(Object[] args, String mName) throws Exception{
        if (args == null) {
            return null;
        }
        StringBuffer rs = new StringBuffer();
        rs.append(mName);
        String className = null;
        int index = 1;
        // 遍历参数对象
        for (Object info : args) {
            //获取对象类型
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("[参数" + index + "，类型：" + className +"]");
            index++;
        }
        return rs.toString();
    }
}

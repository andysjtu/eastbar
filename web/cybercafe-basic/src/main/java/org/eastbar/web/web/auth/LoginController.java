/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.web.auth;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.eastbar.web.Times;
import org.eastbar.web.account.entity.User;
import org.eastbar.web.auth.AccountService;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author C.Lins@aliyun.com
 * @date 2014年09月02
 * @time 上午11:48
 * @description :
 */
@Controller
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String fail() {
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,HttpServletRequest request, RedirectAttributes model) {
        String loadmsg = "ERROR:L0001";
        Object error = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        if(error==null){
            loadmsg = "ERROR:L0002";
        }else {
            if(error instanceof UnknownAccountException || error instanceof IncorrectCredentialsException){
                //TODO
                User user = accountService.findUserByLoginName(userName);
                Long failCount = user.getFailCount();
                if(user.getStatus()!=0){
                    user.setLastlogin(Times.now());
                    if(failCount>=3){
                        user.setStatus(1);//true
                    }
                }
                user.setFailCount(failCount+1L);
                accountService.updateLoginInfo(user);
            }
        }
        model.addFlashAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
        model.addFlashAttribute("loadmsg",loadmsg);
        return "redirect:/";
    }

    @RequestMapping(value = "/login/ok", method = RequestMethod.GET)
    public String succeed() {
        User user = new User();
        user.setId(ShiroCustomUtils.getCurrentID());
        user.setFailCount(0L);
        user.setLastlogin(Times.now());
        user.setStatus(0);//false
        accountService.updateLoginInfo(user);
        return "redirect:/account/user#_1";
    }

}

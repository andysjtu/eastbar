/********************************************************************************
 *                  上海交通大学-鹏越惊虹信息技术发展有限公司                       *
 *                          Copyright © 2003-2014                               *
 ********************************************************************************/
package org.eastbar.web.cache;

import org.springside.modules.web.Servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author C.lins@aliyun.com
 * @date 2014年08月25
 * @time 上午10:29
 * @description : 为Response设置客户端缓存控制Header的Filter.
 */
public class CacheControlHeaderFilter implements Filter {

    private static final String PARAM_EXPIRES_SECONDS = "expiresSeconds";
    private long expiresSeconds;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        Servlets.setExpiresHeader((HttpServletResponse) res, expiresSeconds);
        chain.doFilter(req, res);
    }

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) {
        String expiresSecondsParam = filterConfig.getInitParameter(PARAM_EXPIRES_SECONDS);
        if (expiresSecondsParam != null) {
            expiresSeconds = Long.valueOf(expiresSecondsParam);
        } else {
            expiresSeconds = Servlets.ONE_YEAR_SECONDS;
        }
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }
}

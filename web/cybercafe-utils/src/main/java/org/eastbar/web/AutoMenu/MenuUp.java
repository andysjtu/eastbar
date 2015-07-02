/********************************************************************************
 *                  上海交通大学-鹏越惊虹信息技术发展有限公司                       *
 *                          Copyright © 2003-2014                               *
 ********************************************************************************/
package org.eastbar.web.AutoMenu;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author C.lins@aliyun.com
 * @date 2014年08月20
 * @time 下午3:06
 * @description :
 */
public class MenuUp extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try {

            JspWriter out = this.pageContext.getOut();

            out.println("<%@ include file=\"index44.html\" %>");
            out.println("<h2>Hello World!444444444</h2>");

//            out.println("<a href=\"#\" title=\"XX\" style=\"color:red;\">RREWF</a>");

        } catch(Exception e) {

            throw new JspException(e.getMessage());

        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
    }
}

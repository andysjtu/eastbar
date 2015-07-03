<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>系统设置</title>
    <link href="${ctx}/static/styles/sys.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="mc_bg mc_marg">
    <table width="100%">
        <tr>
            <td width="50%"><table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                <tr>
                    <td class="mc_tab_top02" align="center">
                        <table class="tab_top_title">
                            <tr>
                                <td>报警等级设置</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="border:0px solid;">
                        <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>设置不同报警等级，进行报警通知的方式和形式。</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="/sys/alert#_3" class="mc_btn01">进&nbsp;入</a>
                                </td>

                            </tr>

                        </table>
                    </td>
                </tr>

            </table></td>
            <td><table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                <tr>
                    <td class="mc_tab_top02" align="center">
                        <table class="tab_top_title">
                            <tr>
                                <td>短消息报警通知设置</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="border:0px solid;">
                        <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>设置短消息报警通知的付费方和接受者。</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="/sys/sMs#_3" class="mc_btn01">进&nbsp;入</a>
                                </td>

                            </tr>

                        </table>
                    </td>
                </tr>

            </table></td>
        </tr>
    </table>

</div>
<div class="mc_bg mc_marg">
    <table width="100%">
        <tr>
            <td width="50%"><table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                <tr>
                    <td class="mc_tab_top02" align="center">
                        <table class="tab_top_title">
                            <tr>
                                <td>邮件报警通知设置</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="border:0px solid;">
                        <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>设置邮件报警通知的发送方和接受者。</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="/sys/email#_3" class="mc_btn01">进&nbsp;入</a>
                                </td>

                            </tr>

                        </table>
                    </td>
                </tr>

            </table></td>
       <!--     <td><table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                <tr>
                    <td class="mc_tab_top02" align="center">
                        <table class="tab_top_title">
                            <tr>
                                <td>消息服务器设置</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="border:0px solid;">
                        <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>设置消息服务器的服务器ip和对应的监管中心编码。</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="/sys/server#_3" class="mc_btn01">进&nbsp;入</a>
                                </td>

                            </tr>

                        </table>
                    </td>
                </tr>

            </table></td>-->
        </tr>
    </table>
</div>
</body>
</html>
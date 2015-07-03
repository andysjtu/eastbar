<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>消息通知管理</title>
    <link href="${ctx}/static/styles/msgman.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="mc_bg">
    <table width="100%">
        <tr>
            <td width="50%"><table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                <tr>
                    <td class="mc_tab_top02" align="center">
                        <table class="tab_top_title">
                            <tr>
                                <td>撰写发布的消息管理</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="border:0px solid;">
                        <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>各个监管中心根据权限撰写和发布信息。</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="/msgman/public#_2" class="mc_btn01">进&nbsp;入</a>
                                </td>

                            </tr>

                        </table>
                    </td>
                </tr>

            </table></td>

        </tr>
    </table>
    <table width="100%" style="margin-top:30px;">
        <tr>

            <td><table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                <tr>
                    <td class="mc_tab_top02" align="center">
                        <table class="tab_top_title">
                            <tr>
                                <td>收到的消息管理</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="border:0px solid;">
                        <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>查看收到的消息。</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="/msgman/receive#_2" class="mc_btn01">进&nbsp;入</a>
                                </td>

                            </tr>

                        </table>
                    </td>
                </tr>

            </table></td>
        </tr>
    </table>
</div>
</body>
</html>
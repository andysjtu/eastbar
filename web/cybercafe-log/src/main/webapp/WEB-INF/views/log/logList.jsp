<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>日志管理</title>
    <link href="${ctx}/static/styles/log.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="mc_bg" style="margin-top:70px;">
    <table width="100%">
        <tr>
            <td width="50%">
                <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                    <tr>
                        <td width="190">系统运行维护日志 </td>
                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>为了保证系统正常运行，准确解决遇到的各种各样的系统问题,系统运行
                                        维护日志是系统管理员维护系统和保障各种应用正常进行的一个重要的工具
                                    </td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/log/sys#_7" class="mc_btn01">进&nbsp;入</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="50%">
                <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                    <tr>
                        <td width="190" class="sr_tit">管理员操作行为日志</td>
                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>日志文件中记录了每个管理员对系统进行的各种操作行为事件，例如记
                                        录管理员登录时间，登出系统的时间
                                    </td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/log/admin#_7" class="mc_btn01">进&nbsp;入</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td width="50%">
                <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                    <tr>
                       <td width="190">监管系统历史在线信息</td>
                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>上海市监管系统历史在线信息</td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/log/monitor#_7" class="mc_btn01">进&nbsp;入</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="50%">
                <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                    <tr>
                        <td width="190">场所维修记录查询</td>
                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>场所维修记录日志的查询</td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/log/place#_7" class="mc_btn01">进&nbsp;入</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td width="50%">
                <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                    <tr>

                        <td width="190">离线统计</td>

                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>查询长期不在线的网吧以及当前计费系统未配置的网吧</td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/log/outLine#_7" class="mc_btn01">进&nbsp;入</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
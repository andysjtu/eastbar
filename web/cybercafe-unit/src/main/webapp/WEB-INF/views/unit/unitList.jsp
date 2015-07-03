<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>单位资料查询</title>
    <link href="${ctx}/static/styles/unit.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="mc_bg">
    <table width="100%">
        <tr>
            <td width="50%">
                <table width="100%" class="tab_line">
                    <tr>
                        <td width="190">报警信息查询</td>
                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>监管中心稽查人员可以查询各种违规报警事件。</td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/unit/alert#_5" class="mc_btn01">进&nbsp;入</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="50%">
                <table width="100%" class="tab_line">
                    <tr>
                        <td width="190">场所处罚查询</td>
                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>监管中心网吧场所管理人员可以查看监管中心或其他相关稽查单位对违
                                        规场所进行的处罚情况。</td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/unit/place#_5" class="mc_btn01">进&nbsp;入</a>
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
                        <td width="190">客户资料查询</td>
                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>查询场所内客户的基本信息，如身份证号和姓名等等。</td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/unit/customer#_5" class="mc_btn01">进&nbsp;入</a>
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

                        <td width="190">顾客上网日志查询</td>

                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>监管中心管理员在线查询客户在场所内终端上网的情况,如使用时间和使
                                        用终端上网的情况，如使用时间和使用终端等等。</td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/unit/log#_5" class="mc_btn01">进&nbsp;入</a>
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
                        <td width="190">URL历史记录查询</td>
                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>监控中心管理员可以查看客户URL的历史记录。</td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/unit/url#_5" class="mc_btn01">进&nbsp;入</a>
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
                        <td width="190">程序使用记录</td>
                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>监控中心管理员可以查看客户使用游戏和应用程序的历史记录。</td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/unit/program#_5" class="mc_btn01">进&nbsp;入</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
 <!--   <table width="100%">
        <tr>
            <td width="50%">
                <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                    <tr>
                       <td width="190">场所离线历史日志查询</td>
                        <td style="border:0px solid;">
                            <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                <tr class="tab_cont01" height="40">
                                    <td>监控中心管理员可以查看场所离线的历史记录。</td>
                                </tr>
                                <tr class="tab_cont04">
                                    <td align="center" height="35">
                                        <a href="${ctx}/unit/outPlace#_5" class="mc_btn01">进&nbsp;入</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="50%">
                            <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                                <tr>
                                    <td width="190">场所状态查询</td>
                                    <td style="border:0px solid;">
                                        <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                                            <tr class="tab_cont01" height="40">
                                                <td>监控中心管理员可以查看所有场所的状态</td>
                                            </tr>
                                            <tr class="tab_cont04">
                                                <td align="center" height="35">
                                                    <a href="${ctx}/unit/statePlace#_5" class="mc_btn01">进&nbsp;入</a>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>-->
</div>
</body>
</html>
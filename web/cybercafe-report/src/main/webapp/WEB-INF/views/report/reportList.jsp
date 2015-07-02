<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>统计分析</title>
    <link href="${ctx}/static/styles/report.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="mc_bg">
<table width="100%">
    <tr>
        <td width="50%">
            <table width="100%" class="tab_line" cellpadding="1" cellspacing="1">
                <tr>
                    <td width="190">运营状况统计</td>
                    <td style="border:0px solid;">
                        <table width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>监管中心运营规模统计采用选择统计时段和选择统计对象两种方式来完
                                    成,以此了解整个监管系统包括所有的网吧场所的运营情况</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="${ctx}/report/history#_6" class="mc_btn01">进&nbsp;入</a>
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
                    <td width="190" class="sr_tit">顾客上座人次及使用时间时段分析</td>
                    <td style="border:0px solid;">
                        <table  width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>统计分析在某段时间内网吧场所中的客户上座人次以及平均使用时间</br>
                                    <span class="text_color">注：客户使用时间（分钟）=登出时间 - 登录时间</span>
                                </td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="${ctx}/report/period#_6" class="mc_btn01">进&nbsp;入</a>
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
                    <td width="190">报警统计</td>
                    <td style="border:0px solid;">
                        <table width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>统计在某段时间内、监管中心或场所报警信息的统计分析</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="${ctx}/report/alert#_6" class="mc_btn01">进&nbsp;入</a>
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
                    <td width="190">处罚统计</td>
                    <td style="border:0px solid;">
                        <table width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>统计在某段时间内、监管中心或场所处罚信息的统计分析</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="${ctx}/report/punish#_6" class="mc_btn01">进&nbsp;入</a>
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
                    <td width="190">运营状况排行榜</td>
                    <td style="border:0px solid;">
                        <table  width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>系统对某段时间内网吧场所运营状况包括上座人次和客户使用时间进行排
                                    名统计</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="${ctx}/report/operateRank#_6" class="mc_btn01">进&nbsp;入</a>
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
                    <td width="190">URL访问排行榜</td>
                    <td style="border:0px solid;">
                        <table width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>系统对某段时间内顾客所访问的URL进行排名统计</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="${ctx}/report/urlRank#_6" class="mc_btn01">进&nbsp;入</a>
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
                    <td width="190">程序使用排行榜</td>
                    <td style="border:0px solid;">
                        <table width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>系统对某段时间内顾客所访问的应用程序使用状况进行排名统计</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="${ctx}/report/programRank#_6" class="mc_btn01">进&nbsp;入</a>
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
                    <td width="190">场所在线率统计</td>
                    <td style="border:0px solid;">
                        <table  width="100%" class="mc_tab_line">
                            <tr class="tab_cont01" height="40">
                                <td>系统对某段时间内场所在线率进行排名统计</td>
                            </tr>
                            <tr class="tab_cont04">
                                <td align="center" height="35">
                                    <a href="${ctx}/unit/statePlace#_6" class="mc_btn01">进&nbsp;入</a>
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
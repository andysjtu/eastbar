<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>上海市区县历史在线信息查询</title>
    <link href="${ctx}/static/styles/log.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="mc_tit um_box" style="width:950px;">
    <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
        <tr>
            <td class="mc_tab_top02" align="center">
                上海市区县历史在线信息查询
            </td>
        </tr>
    </table>
         <form id="f1" action="${ctx}/log/submitLogMonitor#_7" method="post">
                <table border="1" width="100%" class="mc_tab_line">
                    <tr class="tab_cont01">
                        <td colspan="4" align="left" style="padding-left:10px;"><b>区县历史在线信息查询输入</b></td>
                    </tr>
                    <tr class="tab_cont03">
                        <td width="130">选择时间</td>
                        <td style="padding:0px;" colspan="3">
                            <table width="100%">
                                <tr>
                                    <td align="left" style="border:0px; width:100px;">
                                        &nbsp;<input name="" type="radio" value="">
                                        按年统计
                                    </td>
                                    <td align="left" style="border-top:0px; border-right:0px; width:38%">
                                        &nbsp;从<select name=""  style="width:100px;">
                                        <option>2014</option>
                                    </select>
                                        年
                                    </td>
                                    <td align="left" style="border-top:0px; border-right:0px;">
                                        &nbsp;到<select name=""  style="width:100px;">
                                        <option>2014</option>
                                    </select>
                                        年
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" style="border-left:0px;">
                                        &nbsp;<input name="" type="radio" value="">
                                        按月统计
                                    </td>
                                    <td align="left" style="line-height:30px;">
                                        &nbsp;从<select name=""  style="width:100px;">
                                        <option>2014</option>
                                    </select>
                                        年
                                        <select name=""  style="width:60px;">
                                            <option>11</option>
                                        </select>
                                        月
                                    </td>
                                    <td style="border:0px; line-height:30px;">
                                        &nbsp;到<select name=""  style="width:100px;">
                                        <option>2014</option>
                                    </select>
                                        年
                                        <select name=""  style="width:60px;">
                                            <option>11</option>
                                        </select>
                                        月
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" style=" border-bottom:0px; border-right:0px; border-left:0px;">
                                        &nbsp;<input name="" type="radio" value="">
                                        按日统计
                                    </td>
                                    <td align="left" style=" line-height:30px;border-bottom:0px;">
                                        &nbsp;从<input type="text" name="begindate" class="easyui-datetimebox" required style="width:200px" />
                                    </td>
                                    <td align="left" style="line-height:30px; border-bottom:0px;">
                                        &nbsp;到<input type="text" name="enddate" class="easyui-datetimebox" required style="width:200px" />
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr class="tab_cont03">
                        <td width="130">统计对象</td>
                        <td style="padding:0px;" colspan="3">
                            <table width="100%">
                                <tr>
                                    <td align="left" style="border:0px; width:100px;">
                                        &nbsp;<input name="" type="radio" value="">
                                        当前系统
                                    </td>
                                    <td style="border-top:0px; border-right:0px;">
                                        &nbsp;以当前系统作为整体进行历史营业状况统计
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" style="border-left:0px;">
                                        &nbsp;<input name="" type="radio" value="">
                                        按市级
                                    </td>
                                    <td align="left" style="border:0px; line-height:30px;">
                                        &nbsp;<select name="" style="width:100px;">
                                        <option>市辖区</option>
                                    </select>

                                    </td>

                                </tr>
                                <tr>
                                    <td align="left" style="border:0px;">
                                        &nbsp;<input name="" type="radio" value="">
                                        按区/县级
                                    </td>
                                    <td align="left" style=" border-right:0px;border-bottom:0px;  line-height:30px;">
                                        &nbsp;<select name=""  style="width:160px;">
                                        <option>黄浦区监管中心</option>
                                    </select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <table width="100%" class="mc_btnbox">
                    <tr>
                        <td align="right" style="padding-top: 4px"><a href="#" onclick="save()" class="mc_btn03">确&nbsp;定</a></td>
                        <td align="center" style="padding-top: 4px" width="150"><a href="#" class="mc_btn03">清&nbsp;空</a></td>
                        <td align="left" style="padding-top: 4px"><a href="${ctx}/log/list#_7" class="mc_btn03">返&nbsp;回</a></td>
                    </tr>
                </table>
         </form>
</div>
<script>
    function save(){
        $('#f1').submit();
    }
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>部级日志信息查询</title>
    <link href="${ctx}/static/styles/log.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="mc_tit um_box" style="width:950px;">
    <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
        <tr>
            <td class="mc_tab_top02" align="center">
                部级日志信息查询
            </td>
        </tr>
    </table>
         <form id="f1" action="${ctx}/log/submitLogDepart#_7" method="post">
             <table border="1" width="100%" class="mc_tab_line">
                 <tr class="tab_cont01">
                     <td colspan="4" align="left" style="padding-left:10px;"><b>部级日志信息查询</b></td>
                 </tr>
                 <tr class="tab_cont03 is_lheight">
                     <td width="130">起始时间</td>
                     <td align="left">
                         &nbsp;<input type="text" name="begindate" class="easyui-datetimebox" required style="width:200px" />
                     </td>
                 </tr>
                 <tr class="tab_cont03 is_lheight">
                     <td width="130">结束时间</td>
                     <td align="left">
                         &nbsp;<input type="text" name="begindate" class="easyui-datetimebox" required style="width:200px" />
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
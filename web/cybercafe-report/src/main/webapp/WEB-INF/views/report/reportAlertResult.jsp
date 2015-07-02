<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>报警信息统计</title>
    <link href="${ctx}/static/styles/report.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform" action="" method="post">
<!--查询结果列表 begin-->
<div class="mc_tit_list um_box_list">
<div class="result mlr2 mt5">
    <div>
         <table id="rtt" class="easyui-datagrid" title="报警信息统计结果" style="width:100%;height:auto"
                       data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/report/alertjson',method:'post'">
            <thead>
            <tr>
                <th data-options="field:'monitorCode',align:'center'" width="15%">监管中心编码</th>
                <th data-options="field:'siteCode',align:'center'" width="10%">场所编码</th>
                <th data-options="field:'alarmCount',align:'center'" width="13%">报警数</th>
                <th data-options="field:'punishCount',align:'center'" width="12%">处罚数</th>
                <th data-options="field:'customerCount',align:'center'" width="10%">顾客数</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!--查询结果列表 end-->
</div>
</form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>应用程序使用状况排行</title>
    <link href="${ctx}/static/styles/report.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform">
<!--查询结果列表 begin-->
    <div class="result mlr2 mt5">
        <div>
        <table id="rtt"  class="easyui-datagrid" title="应用程序使用状况排行榜结果（ ${Btime} ---- ${Etime} ）" style="width:100%;height:auto"
               data-options="rownumbers:true,
				singleSelect:false,
				striped:true,
				autoRowHeight:false,
				pagination:true,
				pageSize:10,url:'${ctx}/report/progjson',method:'get'">
            <thead>
            <tr>
                <th data-options="field:'progName',align:'center'" width="35%">应用程序名称</th>
                <th data-options="field:'count',align:'center'" width="15%">访问次数</th>
                <th data-options="field:'totalhour',align:'center'" width="25%"> 总计使用时间（小时）</th>
                <th data-options="field:'averagehour',align:'center'" width="24%">平均每次使用时间（小时）</th>
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
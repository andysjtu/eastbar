<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>运营状况排行</title>
    <link href="${ctx}/static/styles/report.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform" action="" method="post">
    <!--查询结果列表 begin-->
    <div class="result mlr2 mt5">
        <div>
            <table id="rtt" class="easyui-datagrid" title="运营状况排行" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/report/operateRankJson',method:'get'">
            <thead>
            <tr>
                <th data-options="field:'monitorCode',align:'center'" width="6%">编码</th>
                <th data-options="field:'staYear',align:'center'" width="18%">名称</th>
                <th data-options="field:'placeNum',align:'center'" width="8%">场所数</th>
                <th data-options="field:'terminatotal',align:'center'" width="9%">终端总数</th>
                <th data-options="field:'customerCount',align:'center'" width="8%">上座人次</th>
                <th data-options="field:'placeAveragePerson',align:'center'" width="10%">场所平均上座人次</th>
                <th data-options="field:'terminaAveragePerson',align:'center'" width="10%">终端平均上座人次</th>
                <th data-options="field:'totalhours',align:'center'" width="10%">使用时间（小时）</th>
                <th data-options="field:'placeAverageHour',align:'center'" width="10%">场所平均使用时间（小时）</th>
                <th data-options="field:'terminaAverageHour',align:'center'" width="10%">终端平均使用时间（小时）</th>
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
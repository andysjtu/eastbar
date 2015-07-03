<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>场所离线信息列表</title>
    <link href="${ctx}/static/styles/log.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform" action="" method="post">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">场所离线信息查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">中心名称<input type="text" name="mname"/></span>
                            <span class="ipm">场所名称<input type="text" name="lname"/></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${siteLiveDataBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${siteLiveDataBO.etime}"/></span></p>
                    </div>
                    <input type="button" class="btn btn-primary sub" style="margin-left: 25px;" onclick="rertt()" value="查询"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--查询筛选条件 end -->
    <!--查询结果列表 begin-->
    <div class="result mlr2 mt5">
        <div>
            <table id="rtt" class="easyui-datagrid" title="场所离线信息列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/log/outlinejson',method:'post',queryParams:$.formParams('#sform')">
                <thead>
                <tr>
                    <th data-options="field:'mname',align:'center'" width="10%">区县</th>
                    <th data-options="field:'siteCode',align:'center'" width="12%">场所编码 </th>
                    <th data-options="field:'lname',align:'center'" width="12%">场所名称</th>
                    <th data-options="field:'lastUpdateTime',align:'center'" width="15%">离线时间</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <!--查询结果列表 end-->
</div>
 </form>
<script>
    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }
</script>
</body>
</html>
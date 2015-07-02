<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>场所处罚</title>
    <link href="${ctx}/static/styles/unit.css"  type="text/css" rel="stylesheet"/>
    <script src="${ctx}/static/js/unit.js" type="text/javascript"></script>
</head>
<body>
<form id="sform">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">场所处罚查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">场所编码<input type="text" name="siteCode"/></span>
                            <span class="ipm">处罚原因<input type="text" name="punishReason"/></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${sitePunishBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${sitePunishBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="场所处罚信息列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/unit/sitepunishjson',method:'post',queryParams:$.formParams('#sform')">
            <thead>
            <tr>
                <th data-options="field:'siteCode',align:'center'" width="10%">场所编码</th>
                <th data-options="field:'siteName',align:'center'" width="13%">场所名称</th>
                <th data-options="field:'punishTime',align:'center'" width="14%">处罚日期</th>
                <th data-options="field:'punishReason',align:'center',formatter:replaceReason" width="15%">处罚原因</th>
                <th data-options="field:'punishResult',align:'center',formatter:replaceResult" width="19%">处罚结果</th>
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
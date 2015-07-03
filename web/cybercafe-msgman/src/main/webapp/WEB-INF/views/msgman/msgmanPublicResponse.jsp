<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>响应消息列表</title>
    <link href="${ctx}/static/styles/msgman.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">响应消息查询</td>
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
                            <span class="ipm">响应状态<select name="responseCode">
                                <option value="1">已阅读</option>
                                <option value="2">已反馈</option>
                            </select></span></p>
                        <p class="dp pt5"><span class="ipm">响应时间<input name="btime"  type="text" class="easyui-datetimebox" value="${noticeRecoveryBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${noticeRecoveryBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="响应消息列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/msgman/publicresponsejson/${id}',method:'get',queryParams:$.formParams('#sform')">
                <thead>
                <tr>
                    <th data-options="field:'id',checkbox:true"></th>
                    <th data-options="field:'siteCode',align:'center'" width="10%">场所编码</th>
                    <th data-options="field:'siteName',align:'center'" width="24%">场所名称</th>
                    <th data-options="field:'responseCode',align:'center',formatter:replaceStatus" width="20%">响应状态</th>
                    <th data-options="field:'responseContent',align:'center'" width="13%">反馈内容</th>
                    <th data-options="field:'responseTime',align:'center'" width="10%">响应时间</th>
                </tr>
                </thead>
            </table>
        </div>
        <!-- 发布列表 end-->
    </div>
    <!--查询结果列表 end-->
</form>
<script>

    function replaceStatus(val,row,index){
        if(row.responseCode==1){
            return '已阅读'
        }
        else{
            return '已反馈';
        }
    }
    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>URL历史记录</title>
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
                <td class="panel-title">url历史信息查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">URL<input type="text" name="url"/></span>
                            <span class="ipm">场所编码<input type="text" name="siteCode"/></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${urlHistoryBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${urlHistoryBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="URL历史记录列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/unit/urljson',method:'post',queryParams:$.formParams('#sform')">
            <thead>
            <tr>
                <th data-options="field:'url',align:'center'" width="30%">URL</th>
                <th data-options="field:'siteCode',align:'center'" width="6%">场所编码</th>
                <th data-options="field:'hostIp',align:'center'" width="10%">客户机地址</th>
                <th data-options="field:'accessTime',align:'center'" width="9%">上网时间</th>
                <th data-options="field:'customerName',align:'center'" width="7%">顾客姓名</th>
                <th data-options="field:'customerId',align:'center'" width="10%">顾客账号</th>
                <th data-options="field:'customerType',align:'center',formatter:replaceCert" width="7%">证件类型</th>
                <th data-options="field:'isBlock',align:'center',formatter:replaceBlock" width="7%">是否拦截</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!--查询结果列表 end-->
</div>
</form>
<script>
    function replaceBlock(val,row,index){
        if(row.isBlock==1){
            return '是';
        }
        else{
            return '否';
        }
    }

    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>客户信息列表</title>
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
                <td class="panel-title">客户信息查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">客户姓名<input type="text" name="name"/></span>
                            <span class="ipm">IP<input type="text" name="ipAdd"/></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${customerHostBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${customerHostBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="客户信息列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/unit/customerjson',method:'post',queryParams:$.formParams('#sform')">
            <thead>
            <tr>
                <th data-options="field:'customerName',align:'center',formatter:showName" width="15%">客户姓名</th>
                <th data-options="field:'accountId',align:'center',formatter:showId" width="15%">客户账号</th>
                <th data-options="field:'ipAdd',align:'center'" width="15%">客户端IP</th>
                <th data-options="field:'onlineTime',align:'center'" width="15%">上机时间</th>
                <th data-options="field:'offlineTime',align:'center'" width="15%">下机时间</th>
                <th data-options="field:'osSystem',align:'center'" width="15%">客户机操作系统名称</th>
                <th data-options="field:'version',align:'center'" width="9%">监管软件版本</th>
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

    function showName(val, row, index){
        var value="";
        if("undefined"!=typeof row.customer){
            for(var i=0;i<row.customer.length;i++){
               value=row.customer[i].name;
            }
            return value;

        }else{
            return "";
        }

    }

    function showId(val, row, index){
        var value="";
        if("undefined"!=typeof row.customer){
            for(var i=0;i<row.customer.length;i++){
                value=row.customer[i].accountId;
            }
            return value;

        }else{
            return "";
        }

    }
</script>
</body>
</html>
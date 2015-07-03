<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>顾客上网日志列表</title>
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
                <td class="panel-title">顾客上网日志查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">姓名<input type="text" name="name"/></span>
                            <span class="ipm">证件类型<select name="certType">
                                <option></option>
                                <option value="2">身份证</option>
                            </select></span>

                        <p/>
                        <p><span class="ipm">状态<select name="status">
                            <option></option>
                            <option value="0">online</option>
                            <option value="1">offline</option>
                        </select></span>
                            <span class="ipm">场所编码<input type="text" name="siteCode"/></span></p>
                        <p class="dp pt5" style="display: none;"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value=""/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value=""/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="顾客上网日志列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/unit/logjson',method:'post',queryParams:$.formParams('#sform')">
            <thead>
            <tr>
                <th data-options="field:'accountId',align:'center'" width="8%">客户账号</th>
                <th data-options="field:'siteCode',align:'center'" width="8%">场所编码</th>
                <th data-options="field:'name',align:'center'" width="8%">姓名</th>
                <th data-options="field:'certType',align:'center',formatter:replaceCert" width="8%">证件类型</th>
                <th data-options="field:'certId',align:'center'" width="14%">证件号码</th>
                <th data-options="field:'nationality',align:'center'" width="10%">国籍</th>
                <th data-options="field:'status',align:'center',formatter:replaceStatus" width="4%">状态</th>
                <th data-options="field:'openTime',align:'center'" width="10%">开户时间</th>
                <th data-options="field:'closeTime',align:'center'" width="10%">销户时间</th>
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
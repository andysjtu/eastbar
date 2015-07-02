<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>场所在线率统计</title>
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
                <td class="panel-title">场所状态查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">监管中心<select name="monitorCode">
                            <c:forEach items="${monitors}" var="monitor">
                                <option value="${monitor.monitorCode}">${monitor.name}</option>
                            </c:forEach>
                            </select></span>
                            <span class="ipm">是否在线<select name="runStatus">
                                <option value="0">在线</option>
                                <option value="1">离线</option>
                            </select></span>

                        <p/>
                        <p><span class="ipm">状态&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="regStatus">
                            <option value="0">开业</option>
                            <option value="1">停业</option>
                            <option value="2">整顿</option>
                            <option value="3">装修</option>
                            <option value="4">搬迁</option>
                            <option value="5">吊销</option>
                            </select></span>
                        </p>
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
            <table id="rtt" class="easyui-datagrid" title="场所状态查询列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/report/statePlaceJson',method:'post',queryParams:$.formParams('#sform')">
                <thead>
                <tr>
                    <th data-options="field:'siteCode',align:'center'" width="15%">场所编码</th>
                    <th data-options="field:'name',align:'center'" width="15%">场所名称</th>
                    <th data-options="field:'regStatus',align:'center',formatter:replaceReg" width="10%">状态</th>
                    <th data-options="field:'runStatus',align:'center',formatter:replaceRun" width="10%">是否在线</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <!--查询结果列表 end-->
    </div>
</form>
<script>
    function replaceReg(val,row,index){
        if(row.regStatus==0){
            return '开业'
        }else if(row.regStatus==1){
            return '停业';
        }else if(row.regStatus==2){
            return '整顿';
        }else if(row.regStatus==3){
            return '装修';
        }else if(row.regStatus==4){
            return '搬迁';
        }else if(row.regStatus==5){
            return '吊销';
        }

    }

    function replaceRun(val,row,index){
        if(row.runStatus==0){
            return '在线';
        }else if(row.runStatus==1){
            return '离线';
        }

    }

    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }
</script>
</body>
</html>
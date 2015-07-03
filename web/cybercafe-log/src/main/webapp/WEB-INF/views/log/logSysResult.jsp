<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>系统日志列表</title>
    <link href="${ctx}/static/styles/log.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">系统日志查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">管理员账号<input type="text" name="userName"/></span>
                            <span class="ipm">事件类型<select name="operation">
                                <option></option>
                                <option value="add">添加</option>
                                <option value="edit">修改</option>
                                <option value="remove">删除</option>
                                <option value="login">登录</option>
                            </select></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${sysLogBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${sysLogBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="系统日志列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/log/syslogjson',method:'get',queryParams:$.formParams('#sform')">
                <thead>
                <tr>
                    <th data-options="field:'createDate',align:'center'" width="20%">日期</th>
                    <th data-options="field:'userName',align:'center'" width="20%">管理员账号</th>
                    <th data-options="field:'operation',align:'center',formatter:replaceType" width="8%">事件类型</th>
                    <th data-options="field:'content',align:'center'" width="51%">事件描述</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
</form>
<jsp:include page="operDetail.jsp"/>
<script>
    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }

    function replaceType(val,row,index){
        if(val=='add'){
            return '添加'
        }else if(val=='edit'){
            return '修改';
        }else if(val=='remove'){
            return '删除';
        }else if(val=='login'){
            return '登录';
        }else{
            return val;
        }
    }

    function detail(id) {
        $("#myModal").cacheApply(id);
        $("#myModal").modal('toggle');
        if($('#isSuccess').html()=='0'){
            $('#isSuccess').html('成功');
        }else{
            $('#isSuccess').html('失败') ;
        }
    }

</script>
</body>
</html>
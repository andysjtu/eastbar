<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户列表</title>
    <link href="${ctx}/static/styles/account.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">用户查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">账号<input type="text" name="loginName"/></span>
                        <span class="ipm">姓名<input type="text" name="commonName"/></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value=""/></span>
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
            <table id="rtt" class="easyui-datagrid" title="用户列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/account/userjson',method:'post',toolbar:'#tb',queryParams:$.formParams('#sform')">
                <thead>
                <tr>
                    <th data-options="field:'id',checkbox:true">序号</th>
                    <th data-options="field:'loginName',align:'center',width:80" width="15%">账号</th>
                    <th data-options="field:'commonName',align:'center',width:100" width="10%">姓名</th>
                    <th data-options="field:'_roles',width:80,align:'center',formatter:show" width="15%">角色</th>
                    <th data-options="field:'_monitor',width:80,align:'center',formatter:showMonitor" width="13%">所属域</th>
                    <th data-options="field:'createtime',width:80,align:'center'" width="13%">创建时间</th>
                    <th data-options="field:'status',width:80,align:'center',formatter:formatStatus" width="6%">状态</th>
                    <th data-options="field:'_operate',align:'center',formatter:formatOper">操作</th>
                </tr>
                </thead>
            </table>
            <div id="tb" style="padding:5px;height:auto">
                <shiro:hasPermission name="account:user:add"><a href="${ctx}/account/intoUserAdd#_1" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a></shiro:hasPermission>
                <shiro:hasPermission name="account:user:deletemany"><a href="#" onclick="delsome()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a></shiro:hasPermission>
            </div>
            ${userBO.lastlogin}
        </div>
    </div>
</form>
<jsp:include page="userDetail.jsp"/>
<!--查询结果列表 end-->
<script type="text/javascript">
    function show(val, row, index){
        var value="";
        if("undefined"!=typeof row.roles){
            for(var i=0;i<row.roles.length;i++){
                if(i==row.roles.length-1){
                    value=value+row.roles[i].commonName;
                }
                else{
                    value=value+row.roles[i].commonName+",";
                }
            }
        }
        return value;

    }

    function formatStatus(val, row, index) {
        if("undefined" == typeof val){
            return '正常';
        }else{
            return '锁定';
        }
    }

    function showMonitor(val, row, index){
        if("undefined" != typeof row.roles){
            if("undefined" == typeof row.roles[0].monitor[0]){
                    return "最高管理层";

            }else{
                return row.roles[0].monitor[0].name;
            }
        }else{
            return '';
        }
    }

    function formatOper(val, row, index) {
        $.cachePut(row.id,row);
        if(row.id===1){
            return '<a href="#" onclick="detail(' + row.id + ')" class="link_col01">详细</a>';
        }else if(row.loginName == $("#principal").html()){
            return '<a href="#" onclick="detail(' + row.id + ')" class="link_col01">详细</a> &nbsp;&nbsp; ' +
                    '<shiro:hasPermission name="account:user:edit"><a href="#" onclick="edit(' + row.id + ')" class="link_col01" >修改</a> &nbsp;&nbsp;&nbsp;&nbsp;</shiro:hasPermission>' +
                    '<shiro:hasPermission name="account:user:editRole"><a href="#" onclick="editRole(' + row.id + ')" class="link_col01" >修改角色</a> </shiro:hasPermission>';
        }else{
            return '<a href="#" onclick="detail(' + row.id + ')" class="link_col01">详细</a> &nbsp;&nbsp; ' +
                    '<shiro:hasPermission name="account:user:edit"><a href="#" onclick="edit(' + row.id + ')" class="link_col01" >修改</a> &nbsp;&nbsp;&nbsp;&nbsp;</shiro:hasPermission>' +
                    '<shiro:hasPermission name="account:user:editRole"><a href="#" onclick="editRole(' + row.id + ')" class="link_col01" >修改角色</a> &nbsp;&nbsp;&nbsp;&nbsp;</shiro:hasPermission>' +
                    '<shiro:hasPermission name="account:user:delete"><a href="#" onclick="del(' + row.id + ')" class="link_col01">删除</a></shiro:hasPermission>';
        }
    }

    function detail(id) {
        $("#myModal").cacheApply(id,handles);
        $("#myModal").modal('toggle');
        if($('#status').html()=='true'){
            $('#status').html('锁定');
        }else{
            $('#status').html('正常') ;
        }
    }

    var handles={
        roles:function(roles){
            var view = "";
            $.each(roles,function(i,role){
                view+="<span>"+role.commonName+"</span> ";
            });
            $(".roles>span").html(view);
        }
    }

    function edit(id) {
        window.location = "${ctx}/account/intoUserEdit/"+id+"#_1";
    }

    function editRole(id) {
      window.location = "${ctx}/account/intoUserRoleEdit/"+id+"#_1";
    }

    function del(id) {
        if(window.confirm("确定要删除这条记录吗？")){
            window.location = "${ctx}/account/remove/"+id;
        }

    }

    function delsome(){
        //获取表格选择行
        var rows = $('#rtt').datagrid('getSelections');
        //判断是否选择行
        if (!rows || rows.length == 0) {
            $.messager.alert('提示', '请选择要删除的数据!', 'info');
            return;
        }

        var ids=[];
        for (var i = 0; i < rows.length; i++) {
            //获取checkbox值
            var id=rows[i].id;
            ids.push(id); //然后把单个id循环放到ids的数组中
        }

        if(confirm("确定要删除选中的多个用户吗?")){
            $.ajax({
                    type:'POST',
                    url: '${ctx}/account/deleteManyUser/'+ids,
                    data: '',
                    dataType: "json",
                    success: function(data){
                        showTips(data.msg);
                        rertt();
                  }
            });
        }


    }

    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }

</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>角色列表</title>
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
                <td class="panel-title">角色查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p>
                            <span class="ipm">角色名称<input type="text" name="commonName"/></span></p>
                    </div>
                    <input type="button" class="btn btn-primary subr" style="margin-left: 25px;" onclick="rertt()" value="查询"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--查询筛选条件 end -->
<!--查询结果列表 begin-->
<div class="result mlr2 mt5">
    <div>
        <table id="rtt" class="easyui-datagrid" title="角色列表" style="width:100%;height:auto"
               data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/account/rolejson',method:'post',toolbar:'#tb',queryParams:$.formParams('#sform')">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true">序号</th>
                <th data-options="field:'commonName',align:'center',width:100" width="30%">角色名称</th>
                <th data-options="field:'monitor',width:80,align:'center',formatter:format" width="19%">所属域</th>
                <th data-options="field:'roleDesc',width:80,align:'center'" width="25%">角色描述</th>
                <th data-options="field:'_operate',align:'center',formatter:formatOper" width="23%">操作</th>
            </tr>
            </thead>
        </table>
        <div id="tb" style="padding:5px;height:auto">
            <div style="margin-bottom:5px">
                <shiro:hasPermission name="account:role:add"><a href="${ctx}/account/intoRoleAdd#_1" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a></shiro:hasPermission>
                <shiro:hasPermission name="account:role:deletemany"><a href="#" onclick="delsome()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a></shiro:hasPermission>
            </div>
        </div>
    </div>
</div>
</form>
<jsp:include page="roleDetail.jsp"/>
<!--查询结果列表 end-->
<script>
    function formatOper(val,row,index){
        $.cachePut(row.id,row);
        if(row.id==1 || row.id==2){
            return '<a href="#" onclick="detail('+row.id+')" class="link_col01" >详细</a> &nbsp;&nbsp;&nbsp;&nbsp;';
        }
        return  '<a href="#" onclick="detail('+row.id+')" class="link_col01" >详细</a> &nbsp;&nbsp;&nbsp;&nbsp;'+
                '<shiro:hasPermission name="account:role:edit"><a href="#" onclick="edit('+row.id+')" class="link_col01" >修改</a> &nbsp;&nbsp;&nbsp;&nbsp;</shiro:hasPermission>'+
                '<shiro:hasPermission name="account:role:editPermission"><a href="#" onclick="editRole('+row.id+')" class="link_col01" >修改权限</a> &nbsp;&nbsp;&nbsp;&nbsp;</shiro:hasPermission>'+
                '<shiro:hasPermission name="account:role:delete"><a href="#" onclick="del('+row.id+')" class="link_col01">删除</a></shiro:hasPermission>';
    }

    function format(val,row,index){
        if("undefined" == typeof row.monitor[0]){
            return "最高管理层";
        }else{
            var value="";
            for(i=0;i<row.monitor.length;i++){
                value=row.monitor[i].name;
            }
            return value;
        }
    }

    function detail(id) {
        $("#myModal").cacheApply(id,handles);
        $("#myModal").modal('toggle');
    }

    var handles={
        permissions:function(permissions){
            var view = "<label>&nbsp;</label>";
            $.each(permissions,function(i,per){
                view+="<label>"+(Number(i)+1)+" : <span>"+per.perDesc+"</span></label>";
            });
            $(".permissions>span").html(view);
        }
    }


    function edit(id){
        window.location="${ctx}/account/intoRoleEdit/"+id+"#_1";
    }

    function editRole(id){
        window.location="${ctx}/account/intoPermissionsEdit/"+id+"#_1";
    }

    function del(id){
        if(window.confirm("确定要删除该条记录吗？")){
         window.location="${ctx}/account/removeRole/"+id+"#_1";
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

        if(confirm("确定要删除选中的多个角色吗?")){
            $.ajax({
                type:'POST',
                url: '${ctx}/account/deleteManyRole/'+ids,
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
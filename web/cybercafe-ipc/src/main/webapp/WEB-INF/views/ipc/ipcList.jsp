<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>监管中心</title>
    <link href="${ctx}/static/styles/ipc.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">监管中心查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">编码<input type="text" name="monitorCode"/></span>
                            <span class="ipm">名称<input type="text" name="name"/></span></p>
                        <p><span class="ipm">负责人<input type="text" name="principal"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="监管中心列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/ipc/ipcjson/-1',method:'post',toolbar:'#tb',queryParams:$.formParams('#sform')">
                <thead>
                <tr>
                    <th data-options="field:'id',checkbox:true"></th>
                    <th data-options="field:'monitorCode',align:'center'" width="5%">编码</th>
                    <th data-options="field:'name',align:'center',formatter:linkMonitor"  width="18%">名称</th>
                    <th data-options="field:'type',align:'center',formatter:replace" width="5%">行政级别</th>
                    <th data-options="field:'principal',align:'center'" width="8%">负责人</th>
                    <th data-options="field:'status',align:'center',formatter:replaceStatus" width="5%">状态</th>
                    <th data-options="field:'totalSite',align:'center'" width="5%">场所总数</th>
                    <th data-options="field:'permitSite',align:'center'" width="7%">许可场所总数</th>
                    <%--<th data-options="field:'setupSite',align:'center'" width="7%">安装场所总数</th>--%>
                    <th data-options="field:'openSite',align:'center'" width="7%">营业场所总数</th>
                    <th data-options="field:'totalTerminal',align:'center'" width="6%">终端总数</th>
                    <th data-options="field:'totalAlarm',align:'center',formatter:formatAlarm" width="7%">本周报警数</th>
                    <th data-options="field:'totalPunish',align:'center',formatter:formatPunish" width="6%">处罚记录</th>
                    <th data-options="field:'_operate',align:'center',formatter:formatOper" width="10%">操作</th>
                </tr>
                </thead>
            </table>
            <div id="tb" style="padding:5px;height:auto">
                <div style="margin-bottom:5px">
                    <shiro:hasPermission name="ipc:ipcInfo:add"><a href="${ctx}/ipc/intoAdd#_0" class="easyui-linkbutton" iconCls="icon-add"  plain="true"></a></shiro:hasPermission>
                   <shiro:hasPermission name="ipc:ipcInfo:deletemany"><a href="#" onclick="delsome()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a></shiro:hasPermission>
                </div>
            </div>
        </div>
    </div>
    <!--查询结果列表 end-->
</form>
<jsp:include page="ipcDetail.jsp"/>
<script>
    function formatOper(val,row,index){
        $.cachePut(row.monitorCode,row);
        return '<a href="javascript:void();" onclick="detail('+row.monitorCode+')" class="link_col01">详细</a> &nbsp;&nbsp; '+
                '<shiro:hasPermission name="ipc:ipcInfo:edit"><a href="javascript:void();" onclick="edit('+row.monitorCode+')" class="link_col01">修改</a></shiro:hasPermission> &nbsp;&nbsp;&nbsp;&nbsp;'+
                '<shiro:hasPermission name="ipc:ipcInfo:delete"><a href="javascript:void();" onclick="del('+row.monitorCode+')" class="link_col01">删除</a></shiro:hasPermission>';
    }

    function formatAlarm(val,row,index){
        if("undefined" ==typeof val){
            return '';
        }else{
            return '<a href="${ctx}/unit/alertList/'+row.monitorCode+'#_5" class="link_col01">'+val+'</a>';
        }

    }

    function formatPunish(val,row,index){
        if(val==-999){
            return '';
        }
    }

    function replace(val,row,index){
        if(row.type==1){
            return '省/市'
        }
        else{
            return '区/县';
        }

    }

    function replaceStatus(val,row,index){
        if(row.status==0){
            return '正常'
        }
        else{
            return '故障';
        }

    }

    function linkMonitor(val,row,index){
        return '<a href="javascript:void();"  style="padding: 0 50px;" onclick="reloadList('+row.monitorCode+');">'+row.name+'</a>';
    }

    function detail(monitorCode){
        $("#myModal").cacheApply(monitorCode);
        $("#myModal").modal('toggle');
        if($('#status').html()=='0'){
            $('#status').html('正常');
        }else{
            $('#status').html('故障') ;
        }
    }

    function edit(monitorCode){
        window.location="${ctx}/ipc/intoEdit/"+monitorCode+"#_0";
    }

    function del(monitorCode){
        if(window.confirm('你确定删除这条记录吗？')){
            window.location="${ctx}/ipc/remove/"+monitorCode+"#_0";
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

        var monitorCodes=[];
        for (var i = 0; i < rows.length; i++) {
            //获取checkbox值
            var id=rows[i].monitorCode;
            monitorCodes.push(id); //然后把单个id循环放到ids的数组中
        }

        if(confirm("确定要删除多条记录吗?")){
            $.ajax({
                type:'POST',
                url: '${ctx}/ipc/deleteManyIpc/'+monitorCodes,
                data: '',
                dataType: "json",
                success: function(data){
                   showTips(data.msg);
                   rertt();
                }
            });
        }


    }

    function reloadList(monitorCode){
        if(monitorCode.toString().length>=6){
            window.location="${ctx}/ipc/placeList/"+monitorCode+"#_0";
        }
        else{
            $(function () {
                var url = "${ctx}/ipc/ipcjson/"+monitorCode+"#_0";
                $('#rtt').datagrid({
                    url: url
                });
            });
        }
    }

    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }
</script>
</body>
</html>
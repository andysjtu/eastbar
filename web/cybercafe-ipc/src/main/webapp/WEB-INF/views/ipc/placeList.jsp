<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>营业场所列表</title>
    <link href="${ctx}/static/styles/ipc.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">场所信息查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">编码<input type="text" name="siteCode"/></span>
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
            <table id="rtt" class="easyui-datagrid" title="场所信息列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
                pageSize:10,url:'${ctx}/ipc/placejson/${monitorCode}',method:'post',toolbar:'#tb',queryParams:$.formParams('#sform')">
                <thead>
                    <tr>
                        <th data-options="field:'id',checkbox:true"></th>
                        <th data-options="field:'siteCode',align:'center'" width="8%">场所编码</th>
                        <th data-options="field:'name',align:'center',formatter:linkMonitor" width="15%">场所名称</th>
                        <th data-options="field:'isActive',align:'center',formatter:replaceStatus" width="3%">状态</th>
                        <th data-options="field:'principal',align:'center'" width="8%">负责人</th>
                        <th data-options="field:'principalTel',align:'center'" width="10%">负责人电话</th>
                        <th data-options="field:'terminalNum',align:'center'" width="6%">注册终端</th>
                        <th data-options="field:'activeCustomerCount',align:'center'" width="9%">连接终端数</th>
                        <th data-options="field:'totalAlarm',align:'center',formatter:formatAlarm" width="10%">本周报警数</th>
                        <th data-options="field:'totalPunish',align:'center',formatter:formatPunish" width="8%">处罚数</th>
                        <th data-options="field:'installationRate',align:'center'" width="8%">客户端安装率</th>
                        <th data-options="field:'_operate',align:'center',formatter:formatOper" width="10%">操作</th>
                    </tr>
                    </thead>
                </table>
            <div id="tb" style="padding:5px;height:auto">
                <div style="margin-bottom:5px">
                    <shiro:hasPermission name="place:placeInfo:add"><a href="${ctx}/ipc/intoPlaceAdd#_0" class="easyui-linkbutton" iconCls="icon-add"  plain="true"></a></shiro:hasPermission>
                    <shiro:hasPermission name="place:placeInfo:deletemany"><a href="#" onclick="delsome()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a></shiro:hasPermission>
                </div>
            </div>
            </div>
        </div>
    </form>
<jsp:include page="placeDetail.jsp"/>
<script>
    function formatOper(val,row,index){
        $.cachePut(row.siteCode,row);
        return '<a href="#" onclick="detail('+row.siteCode+')" class="link_col01">详细</a> &nbsp;&nbsp; '+
                '<shiro:hasPermission name="place:placeInfo:edit"><a href="#" onclick="edit('+row.siteCode+')" class="link_col01" >修改</a></shiro:hasPermission> &nbsp;&nbsp;&nbsp;&nbsp;'+
                '<shiro:hasPermission name="place:placeInfo:delete"><a href="#" onclick="del('+row.siteCode+')" class="link_col01" >删除</a></shiro:hasPermission>';
//                '<a href="#" class="link_col01" >报修</a> &nbsp;&nbsp;&nbsp;&nbsp;'+
//                '<a href="#" class="link_col01">处罚场所</a>';
    }

    function formatAlarm(val,row,index){
        if("undefined" ==typeof val){
            return '';
        }else{
            return '<a href="${ctx}/unit/alertList/'+row.siteCode+'#_5" class="link_col01">'+val+'</a>';
        }

    }

    function formatPunish(val,row,index){
        if(val==-999){
            return '';
        }
    }

    function formatClient(val,row,index){
        if("undefined"==typeof val){
            return '';
        }
        return val+'%';
    }

    function linkMonitor(val,row,index){
        return '<a href="javascript:void(0);" onclick="reloadList('+row.siteCode+')" style="text-decoration:underline;">'+row.name+'</a>';
    }

    function replaceStatus(val,row,index){
       // alert(val);
        if(val){
            return '连接';
        }else{
            return '未连接';
        }
    }

    function detail(siteCode){
        $("#myModal").cacheApply(siteCode);
        $("#myModal").modal('toggle');
        if($('#regStatus').html()=='0'){
            $('#regStatus').html('开业');
        }else if($('#regStatus').html()=='1'){
            $('#regStatus').html('停业') ;
        }else if($('#regStatus').html()=='2'){
            $('#regStatus').html('整顿') ;
        }else if($('#regStatus').html()=='3'){
            $('#regStatus').html('装修') ;
        }else if($('#regStatus').html()=='4'){
            $('#regStatus').html('搬迁') ;
        }else{
            $('#regStatus').html('吊销') ;
        }
        if($('#runStatus').html()=='0'){
            $('#runStatus').html('正常');
        }else{
            $('#runStatus').html('故障');
        }

    }

    function edit(siteCode){
        window.location="${ctx}/ipc/placeEdit/"+siteCode+"#_0";
    }

    function del(siteCode){
        if(confirm("确定要删除这条记录吗？")){
            window.location="${ctx}/ipc/placeRemove/"+siteCode+"#_0";

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

        var siteCodes=[];
        for (var i = 0; i < rows.length; i++) {
            //获取checkbox值
            var id=rows[i].siteCode;
            siteCodes.push(id); //然后把单个id循环放到ids的数组中
        }

        if(confirm("确定要删除多条记录吗?")){
            $.ajax({
                type:'POST',
                url: '${ctx}/ipc/deleteManyPlace/'+siteCodes,
                data: '',
                dataType: "json",
                success: function(data){
                    showTips(data.msg);
                    rertt();
                }
            });
        }
    }

    function reloadList(siteCode){
        window.location="${ctx}/ipc/siteTerminal/"+siteCode+"#_0";
    }

    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }
</script>
</body>
</html>
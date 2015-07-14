<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>发布消息列表</title>
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
                <td class="panel-title">发布消息查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">标题<input type="text" name="noticeTitle"/></span>
                            <span class="ipm">类型<select name="noticeType">
                                <option></option>
                                <option value="0">普通</option>
                                <option value="1">需要回复</option>
                            </select></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${noticeBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${noticeBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="发布消息列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/msgman/publicjson',method:'get',toolbar:'#tb',queryParams:$.formParams('#sform')">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'noticeTitle',align:'center'" width="24%">消息标题</th>
                <th data-options="field:'monitorCode',align:'center'" width="10%">中心编码</th>
                <th data-options="field:'siteCode',align:'center'" width="10%">场所编码</th>
                <th data-options="field:'noticeTime',align:'center'" width="10%">发布日期</th>
                <th data-options="field:'noticeType',align:'center',formatter:replaceType" width="10%">消息类型</th>
                <th data-options="field:'status',align:'center',formatter:replaceStatus" width="10%">状态</th>
                <th data-options="field:'createTime',align:'center'" width="10%">创建日期</th>
                <th data-options="field:'_operate',align:'center',formatter:formatOper" >操作</th>
            </tr>
            </thead>
        </table>
        <div id="tb" style="padding:5px;height:auto">
            <div style="margin-bottom:5px">
                <a href="/msgman/intoAdd#_2" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
                <%--<a href="#" onclick="delsome()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>--%>
            </div>
        </div>
    </div>
    <!-- 发布列表 end-->
</div>
<!--查询结果列表 end-->
</form>
<jsp:include page="msgmanDetail.jsp"/>
<script>
    function formatOper(val,row,index){
        $.cachePut(row.id,row);
        return '<shiro:hasPermission name="msgman:public:public"><a href="#" onclick="show('+row.id+')">查看</a></shiro:hasPermission>&nbsp;&nbsp; '+
                '<shiro:hasPermission name="msgman:public:viewresponse"><a href="#" onclick="resp('+row.id+')">响应情况</a> </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;'
    }
    function resp(id){
        window.location="${ctx}/msgman/publicResponse/"+id+"#_2";
    }

    function edit(id){
        window.location="${ctx}/msgman/intoEdit/"+id+"#_2";
    }

    function del(id){
        if(window.confirm("确定要删除该条消息吗？")){
            window.location="${ctx}/msgman/remove/"+id+"#_2";
        }

    }

    function replaceStatus(val,row,index){
        if(val==0){
            return '未发布'
        }
        else{
            return '已发布';
        }
    }


    function replaceType(val,row,index){
        if(val==0){
            return '普通'
        }
        else{
            return '需要回复';
        }
    }

    function show(id){
        $("#myModal").cacheApply(id);
        $("#myModal").modal('toggle');
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

        if(confirm("确定要删除多条记录吗?")){
            $.ajax({
                type:'POST',
                url: '${ctx}/msgman/deleteMany/'+ids,
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
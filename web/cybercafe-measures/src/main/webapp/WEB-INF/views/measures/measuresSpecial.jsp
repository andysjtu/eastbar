<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>特殊人员列表</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">特殊人员查询</td>
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
                            <span class="ipm">告警等级<select name="alarmRank">
                                <option></option>
                                <option value="1">严重报警</option>
                                <option value="2">中等程度报警</option>
                                <option value="3">一般报警</option>
                            </select></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${specialCustomerBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${specialCustomerBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="特殊人员列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/measures/bannedspecialjson',method:'post',toolbar:'#tb',queryParams:$.formParams('#sform')">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'name',align:'center'" width="15%">姓名</th>
                <th data-options="field:'monitorCode',align:'center',formatter:replaceCode" width="10%">监管中心</th>
                <th data-options="field:'certType',align:'center',formatter:replaceCert"  width="10%">证件类型</th>
                <th data-options="field:'certId',align:'center'" width="10%">证件号</th>
                <th data-options="field:'nationality',align:'center'" width="10%">国籍</th>
                <th data-options="field:'alarmType',align:'center',formatter:replaceAlarmType" width="10%">告警类型</th>
                <th data-options="field:'alarmRank',align:'center',formatter:replaceRank" width="11%">告警等级</th>
                <th data-options="field:'_operate',align:'center',formatter:formatOper">操作</th>
            </tr>
            </thead>
        </table>
        <div id="tb" style="padding:5px;height:auto">
            <div style="margin-bottom:5px">
                <shiro:hasPermission name="measures:special:add"><a href="${ctx}/measures/intoSpecialAdd#_4" class="easyui-linkbutton" iconCls="icon-add"  plain="true"></a></shiro:hasPermission>
                <shiro:hasPermission name="measures:special:deletemany"><a href="#" onclick="delMany()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a></shiro:hasPermission>
                <shiro:hasPermission name="measures:special:public"><a href="#" onclick="releaseMany()" class="easyui-linkbutton" iconCls="icon-undo" plain="true"></a></shiro:hasPermission>
            </div>
        </div>
    </div>
</div>
<!--查询结果列表 end-->
</div>
</form>
<script>
    function formatOper(val,row,index){
        if(row.isPub==1){
            return  '<shiro:hasPermission name="measures:special:edit"><a href="#" onclick="edit('+row.id+')"  class="link_col01" >修改</a></shiro:hasPermission> &nbsp;&nbsp;&nbsp;&nbsp;'+
                    '已发布&nbsp;&nbsp;&nbsp;&nbsp;'+
                    '<shiro:hasPermission name="measures:special:delete"><a href="#" onclick="del('+row.id+')"  class="link_col01">删除</a></shiro:hasPermission>';
        }
        else{
            return  '<shiro:hasPermission name="measures:special:edit"><a href="#" onclick="edit('+row.id+')"  class="link_col01" >修改</a></shiro:hasPermission> &nbsp;&nbsp;&nbsp;&nbsp;'+
                    '<shiro:hasPermission name="measures:special:public"><a href="#" onclick="release('+row.id+')"  class="link_col01" >发布</a></shiro:hasPermission> &nbsp;&nbsp;&nbsp;&nbsp;'+
                    '<shiro:hasPermission name="measures:special:delete"><a href="#" onclick="del('+row.id+')"  class="link_col01">删除</a></shiro:hasPermission>';
        }
    }

    function replaceCert(val,row,index){
        if(row.certType==1){
            return '身份证'
        }else{
            return '军官证';
        }

    }

    function replaceAlarmType(val,row,index){
        if(row.alarmType==1){
            return '游戏、程序报警'
        }
        else if(row.alarmType==2){
            return '运行报警';
        }
        else if(row.alarmType==3){
            return 'URL访问报警';
        }else{
            return '特殊人员报警';
        }
    }

    function replaceRank(val,row,index){
        if(row.alarmRank==1){
            return '严重报警'
        }
        else if(row.alarmRank==2){
            return '中等程度报警';
        }else{
            return '一般报警';
        }

    }

    function replaceCode(val,row,index){
        if(typeof(val) == "undefined"){
            return "最高级";
        }else{
            return val;
        }
    }

    function edit(id){
       // alert(index);
        window.location="${ctx}/measures/intoSpecialEdit/"+id+"#_4";
    }

    function del(id){
        if(window.confirm("确定要删除该条记录吗？")){
            window.location="${ctx}/measures/specialDelete/"+id;
        }

    }

    function release(id){
        if(confirm("您确定要发布该条特殊人员信息吗？")){
            window.location="${ctx}/measures/specialRelease/"+id;
        }

    }

    function delMany(){
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
                url: '${ctx}/measures/deleteManySpecial/'+ids,
                data: '',
                dataType: "json",
                success: function(data){
                    showTips(data.msg);
                    rertt();
                }
            });

        }

    }

    function releaseMany(){
        //获取表格选择行
        var rows = $('#rtt').datagrid('getSelections');
        //判断是否选择行
        if (!rows || rows.length == 0) {
            $.messager.alert('提示', '请选择要发布的数据!', 'info');
            return;
        }

        var ids=[];
        for (var i = 0; i < rows.length; i++) {
            //获取checkbox值
            var id=rows[i].id;
            ids.push(id); //然后把单个id循环放到ids的数组中
        }

        if(confirm("确定要发布选中的多个特殊人员吗?")){
            $.ajax({
                type:'POST',
                url: '${ctx}/measures/releaseManySpecial/'+ids,
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
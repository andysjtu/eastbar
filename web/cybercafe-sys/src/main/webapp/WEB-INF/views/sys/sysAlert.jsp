<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>报警等级</title>
    <link href="${ctx}/static/styles/sys.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform">
    <!--查询结果列表 begin-->
    <div class="result mlr2 mt5">
        <div>
            <table id="rtt" class="easyui-datagrid" title="报警等级列表" style="width:100%;height:auto"
                   data-options="rownumbers:false,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/sys/alertjson',method:'post'">
                <thead>
                <tr>
                    <th data-options="field:'id',checkbox:false"></th>
                    <th data-options="field:'rankName',align:'center'" width="20%">告警等级</th>
                    <th data-options="field:'isSms',align:'center',formatter:replace"  width="20%">SMS消息</th>
                    <th data-options="field:'isEmail',align:'center',formatter:rep" width="20%">Email</th>
                    <th data-options="field:'_operate',align:'center',formatter:formatOper" width="10%">操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <!--查询结果列表 end-->
</form>

<script>

    function replace(val,row,index){
        if(row.isSms==0){
            return '否'
        }
        else{
           return '是';
        }

    }

    function rep(val,row,index){
        if(row.isEmail==0){
            return '否'
        }
        else{
            return '是';
        }

    }


    function formatOper(val,row,index){

        return '<shiro:hasPermission name="sys:alarmSys:edit"><a href="javascript:void();" onclick="edit('+row.id+')" class="link_col01">修改</a> </shiro:hasPermission>&nbsp;&nbsp; '
    }
    function edit(id){
       window.location="${ctx}/sys/intoAlertEdit/"+id+"#_3";
    }

</script>
</body>
</html>
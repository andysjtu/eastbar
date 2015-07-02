<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Email使用记录</title>
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
                <td class="panel-title">Email使用记录查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">email类型<select name="emailType">
                            <option></option>
                            <option value="1">QQ邮箱</option>
                            <option value="2">163邮箱</option>
                            <option value="3">126邮箱</option>
                            <option value="4">新浪邮箱</option>
                        </select></span>
                            <span class="ipm">场所编码<input type="text" name="siteCode"/></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${mailHistoryBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${mailHistoryBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="Email使用记录列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/unit/mailjson',method:'post',queryParams:$.formParams('#sform')">
            <thead>
            <tr>
                <th data-options="field:'siteCode',align:'center'" width="6%">场所编码</th>
                <th data-options="field:'emailType',align:'center',formatter:replaceType" width="8%">email类型</th>
                <th data-options="field:'emailAccount',align:'center'" width="12%">email账号</th>
                <th data-options="field:'receive',align:'center'" width="7%">接收者</th>
                <th data-options="field:'theme',align:'center'" width="10%">主题</th>
                <th data-options="field:'hostIp',align:'center'" width="10%">客户机地址</th>
                <th data-options="field:'startTime',align:'center'" width="9%">开始时间</th>
                <th data-options="field:'endTime',align:'center'" width="9%">结束时间</th>
                <th data-options="field:'customerName',align:'center'" width="7%">顾客姓名</th>
                <th data-options="field:'customerIdType',align:'center',formatter:replaceCert" width="7%">证件类型</th>
                <th data-options="field:'isBlock',align:'center',formatter:replaceBlock" width="7%">是否拦截</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!--查询结果列表 end-->
</div>
</form>
<script>
    function replaceBlock(val,row,index){
        if(row.isBlock==1){
            return '是';
        }
        else{
            return '否';
        }
    }

    function replaceType(val,row,index){
        if(val==1){
            return 'QQ邮箱';
        }else if(val==2){
            return '163邮箱';
        }else if(val==3){
            return '126邮箱';
        }else{
            return '新浪邮箱';
        }
    }

    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }
</script>
</body>
</html>
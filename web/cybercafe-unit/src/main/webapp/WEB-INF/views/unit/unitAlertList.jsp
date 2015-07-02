<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>报警信息列表</title>
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
                <td class="panel-title">报警信息查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">场所编码<input type="text" name="siteCode" value="${siteCode}"/></span>
                            <span class="ipm">告警类型<select name="alarmType">
                                <option></option>
                                <option value="1">url拦截告警</option>
                                <option value="2">程序监管告警</option>
                                <option value="3">非法信息告警</option>
                            </select></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${alarmHistoryBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${alarmHistoryBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="报警信息列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/unit/alertjson/',method:'post',queryParams:$.formParams('#sform')">
            <thead>
            <tr>
                <th data-options="field:'siteCode',align:'center'" width="6%">场所编码</th>
                <th data-options="field:'hostIp',align:'center'" width="14%">客户端IP</th>
                <th data-options="field:'alarmTime',align:'center'" width="10%">告警时间</th>
                <th data-options="field:'alarmType',align:'center',formatter:replaceType" width="8%">报警类型</th>
                <th data-options="field:'alarmLevel',align:'center',formatter:replaceRank" width="8%">报警等级</th>
                <th data-options="field:'alarmContent',align:'center'" width="12%">报警内容</th>
                <th data-options="field:'customerName',align:'center'" width="8%">顾客名字</th>
                <th data-options="field:'customerCertType',align:'center',formatter:replaceCert" width="7%">顾客证件类型</th>
                <th data-options="field:'customerCertId',align:'center'" width="14%">顾客证件号码</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!--查询结果列表 end-->
</div>
</form>
<script>
    function replaceType(val,row,index){
        if(row.alarmType==1){
            return 'url拦截告警'
        }else if(row.alarmType==2){
            return '程序监管告警';
        }else if(row.alarmType==3){
            return '非法信息告警';
        }
    }

    function replaceRank(val,row,index){
        if(val==1){
            return '严重报警';
        }else if(val==2){
            return '中等程度报警';
        }else{
            return '一般报警';
        }
    }

    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }
</script>
</body>
</html>
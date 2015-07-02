<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>场所维修记录查询</title>
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
                <td class="panel-title">场所维修记录查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">监管中心<select name="monitorCode" class="mc_sel_mid">
                            <c:forEach items="${monitors}" var="monitor">
                                <option value="${monitor.monitorCode}">${monitor.name}</option>
                            </c:forEach>
                            </select></span>
                            <span class="ipm">场所编码<input name="siteCode" type="text" class="mc_inp"/></span></p>
                        <p><span class="ipm">场所名称<input name="siteName" type="text" class="mc_inp"/></span>
                           <span class="ipm">服务类型<select name="type">
                               <option value="0">全部</option>
                               <option value="1">技术支持</option>
                               <option value="2">远程协作</option>
                               <option value="3">上门服务</option>
                               <option value="4">送修服务</option>
                           </select>
                           </span></p>
                        <p><span class="ipm">状态&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="status" >
                                <option value="0">全部</option>
                                <option value="1">提交</option>
                                <option value="2">处理中</option>
                                <option value="3">完毕</option>
                                <option value="4">硬件故障</option>
                                <option value="5">硬件故障已通知</option>
                                <option value="6">已安装</option>
                                <option value="7">已通知取货</option>
                                <option value="8">监控中</option>
                                </select>
                            </span>
                            <span class="ipm">处理结果<select name="result" >
                                <option value="0">全部</option>
                                <option value="1">未上门</option>
                                <option value="2">未处理</option>
                                <option value="3">成功（处理后在线）</option>
                                <option value="4">不成功（处理后不在线）</option>
                                </select></span></p>
                        <p><span class="ipm">报修起始时间<input name="btime" type="text" class="easyui-datetimebox" value="${siteRepairInfoBO.btime}"/></span>
                           <span class="ipm">报修结束时间<input name="etime"  type="text" class="easyui-datetimebox" value="${siteRepairInfoBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="场所维修记录列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/log/placeJson',method:'post',queryParams:$.formParams('#sform')">
                <thead>
                <tr>
                    <th data-options="field:'siteCode',align:'center'" width="11%">场所编码</th>
                    <th data-options="field:'siteName',align:'center'" width="10%">场所名称</th>
                    <th data-options="field:'type',align:'center',formatter:replaceType" width="6%">服务类型</th>
                    <th data-options="field:'siteReportTime',align:'center'" width="10%">场所报修时间</th>
                    <th data-options="field:'finishedTime',align:'center'" width="10%">处理完成时间</th>
                    <th data-options="field:'siteResponseTime',align:'center'" width="10%">场所响应时间</th>
                    <th data-options="field:'beforeProcess',align:'center'" width="10%">处理前情况</th>
                    <th data-options="field:'description',align:'center'" width="10%">问题描述</th>
                    <th data-options="field:'processSituation',align:'center'" width="12%">处理情况</th>
                    <th data-options="field:'result',align:'center',formatter:replaceResult" width="10%">处理结果</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <!--查询结果列表 end-->
    <%--<div>--%>
        <%--<table width="100%" class="mc_btnbox">--%>
            <%--<tr>--%>
                <%--<td align="center" style="padding-top: 4px;"><a href="${ctx}/log/submitLogPlace#_7" class="mc_btn01">重新查询</a></td>--%>

                <%--<td align="center" style="padding-top: 4px;"><a href="${ctx}/log/sys#_7" class="mc_btn01">返回</a></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</div>--%>
</div>
</form>
<script>

    function replaceType(val,row,index){
        if(row.type==1){
            return '技术支持';
        }else if(row.type==2){
            return '远程协作';
        }else if(row.type==3){
            return '上门服务';
        }else if(row.type==4){
            return '送修服务';
        }
    }

    function replaceResult(val,row,index){
        if(row.result==1){
            return '未上门';
        }else if(row.result==2){
            return '未处理';
        }else if(row.result==3){
            return '成功（处理后在线）';
        }else if(row.result==4){
            return '不成功（处理后不在线）';
        }else{
            return '不能识别';
        }
    }

    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }
</script>
</body>
</html>
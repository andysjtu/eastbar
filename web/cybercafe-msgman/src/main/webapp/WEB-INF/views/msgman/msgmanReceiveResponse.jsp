<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>收到响应情况</title>
    <link href="${ctx}/static/styles/msgman.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform" action="" method="post">
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
                        <p><span class="ipm">中心编码<input type="text" name="ipcno"/></span>
                            <span class="ipm">中心编码<input type="text" name="ipcno"/></span></p>
                        <p><span class="ipm">开始时间<input name="ipcno" class="easyui-datebox"/></span>
                            <span class="ipm">结束时间<input name="ipcno"  class="easyui-datebox"/></span></p>
                        <p><span class="ipm">中心编码<input type="text" name="ipcno"/></span></p>
                    </div>
                </td>
                <td class="mr35p"><input type="button" class="btn btn-primary" onclick="rertt()" value="查询"/></td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--查询筛选条件 end -->
    <!--查询结果列表 begin-->
    <div class="result mlr2 mt5">
        <div>
            <table id="rtt" class="easyui-datagrid" title="收到消息列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				pageSize:10,url:'${ctx}/static/json/auth/msgman/datagrid_msgman_response.json',method:'get',toolbar:'#tb'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'placename',width:480,align:'center'" width="25%">场所名称</th>
                <th data-options="field:'status',width:180,align:'center'" width="24%">响应状态</th>
                <th data-options="field:'responsecontent',width:480,align:'center'" width="24%">反馈内容</th>
                <th data-options="field:'responsetime',width:420,align:'center'" width="24%">响应时间</th>

            </tr>
            </thead>
           </table>
           <table width="100%" class="mc_btnbox">
             <tr>
                <td align="center" style="padding-top: 4px"><a href="${ctx}/msgman/receive#_2" class="mc_btn01">返&nbsp;回</a></td>
             </tr>
           </table>
    </div>
</div>
<!--查询结果列表 end-->
</form>
</body>
</html>
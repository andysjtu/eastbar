<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>>信任URL访问记录排行</title>
    <link href="${ctx}/static/styles/report.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform" action="" method="post">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">信任URL访问记录排行查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">账号<input type="text" name="loginName"/></span>
                            <span class="ipm">姓名<input type="text" name="commonName"/></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="beimt"  type="text" class="easyui-datetimebox" value="${userBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${userBO.etime}"/></span></p>
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
        <table  id="rtt" class="easyui-datagrid" title="信任URL访问记录排行榜结果（2014年9月24日----2014年9月25日 ）" style="width:100%;height:auto"
               data-options="rownumbers:true,
				singleSelect:false,
				striped:true,
				autoRowHeight:false,
				pagination:true,
				pageSize:10,url:'${ctx}/static/json/auth/report/datagrid_report_trustUrlrank.json',method:'get'">
            <thead>
            <tr>
                <th data-options="field:'url',align:'center'" width="60%">域名</th>
                <th data-options="field:'num',align:'center'" width="38%">访问次数</th>

            </tr>
            </thead>
        </table>
    </div>
</div>
<!--查询结果列表 end-->
<div>
<table width="100%" class="mc_btnbox">
    <tr>
        <td align="right" style="padding-top: 4px;"><a href="${ctx}/report/trustUrlRank#_6" class="mc_btn01">返&nbsp;回</a></td>
        <td align="center" style="padding-top: 4px;"><a href="${ctx}/measures/list#_6" class="mc_btn01">打&nbsp;印</a></td>
        <td align="left" style="padding-top: 4px;"><a href="${ctx}/measures/list#_6" class="mc_btn01">EXCEL列表</a></td>
    </tr>
</table>
</div>
</div>
</form>
<script>
    function formatOper(val,row,index){
        return  '<a href="#" onclick="edit('+index+')"  class="link_col01" >修改</a>';
    }

    function edit(){
        window.location="${ctxs}/measures/intoAlertEdit";
    }

    function del(){
        window.location="${ctxs}/measures/gameDelete";
    }
</script>
</body>
</html>
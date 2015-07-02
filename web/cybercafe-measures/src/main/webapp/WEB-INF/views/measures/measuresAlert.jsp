<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>其他违规报警等级设置列表</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform" action="" method="post">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">其他违规报警等级设置查询</td>
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
            <table id="rtt" class="easyui-datagrid" title="其他违规报警等级设置列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/static/json/auth/measures/datagrid_measures_alert.json',method:'post'">
            <thead>
            <tr>
                <th data-options="field:'alertType',align:'center'" width="48%">违规报警类别</th>
                <th data-options="field:'alertGrade',align:'center'" width="25%">报警等级</th>
                <th data-options="field:'_operate',align:'center',formatter:formatOper" width="26%">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!--查询结果列表 end-->
<div>
<table width="100%" class="mc_btnbox">
    <tr>
        <td align="center" style="padding-top: 4px;"><a href="${ctx}/measures/list#_4" class="mc_btn01">返&nbsp;回</a></td>
    </tr>
</table>
</div>
</div>
</form>
<script>
    function replace(val,row,index){
        if(row.alertType==1){
            return '否'
        }
        else if(row.alertType=2){
            return '是';
        }
        else{
            return
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
        return  '<a href="#" onclick="edit('+index+')"  class="link_col01" >修改</a>';
    }

    function edit(){
        window.location="${ctx}/measures/intoAlertEdit#_4";
    }

    function del(){
        window.location="${ctx}/measures/gameDelete#_4";
    }
</script>
</body>
</html>
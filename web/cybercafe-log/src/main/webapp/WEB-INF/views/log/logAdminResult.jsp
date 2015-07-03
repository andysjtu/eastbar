<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>管理员操作行为列表</title>
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
                <td class="panel-title">管理员操作行为查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">管理员账号<input type="text" name="userName"/></span>
                            <span class="ipm">事件类型<select name="operType">
                                <option></option>
                                <option value="0">关机</option>
                                <option value="1">重新启动</option>
                                <option value="2">锁定</option>
                                <option value="3">解锁</option>
                                <option value="4">截屏</option>
                            </select></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${operLogBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${operLogBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="管理员操作行为列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
				pageSize:10,url:'${ctx}/log/adminlogjson',method:'get',queryParams:$.formParams('#sform')">
                <thead>
                <tr>
                    <th data-options="field:'operTime',align:'center'" width="21%">日期</th>
                    <th data-options="field:'userName',align:'center'" width="20%">管理员账号</th>
                    <th data-options="field:'operType',align:'center',formatter:replaceType" width="18%">事件类型</th>
                    <th data-options="field:'operLog',align:'center'" width="20%">事件描述</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    </div>
</form>
<jsp:include page="operDetail.jsp"/>
<script>
    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }

    function replaceType(val,row,index){
            $.cachePut(row.id,row.monitorCmd);
            if(row.operType==0){
                return '<a href="javascript:void();" onclick="detail('+row.id+')" class="link_col01">关机</a>';
            }
            else if(row.operType==1){
                return '<a href="javascript:void();" onclick="detail('+row.id+')" class="link_col01">重新启动</a>';
            }
            else if(row.operType==2){
                return '<a href="javascript:void();" onclick="detail('+row.id+')" class="link_col01">锁定</a>';
            }
            else if(row.operType==3){
                return '<a href="javascript:void();" onclick="detail('+row.id+')" class="link_col01">解锁</a>';
            }
            else if(row.operType==4){
                return '<a href="javascript:void();" onclick="detail('+row.monitorCmd.id+')" class="link_col01">截屏</a>';
            }else{
                return '<a href="javascript:void();" onclick="detail('+row.id+')" class="link_col01">无法识别</a>';
            }

    }

    function detail(id) {
        $("#myModal").cacheApply(id);
        $("#myModal").modal('toggle');
        if($('#isSuccess').html()=='0'){
            $('#isSuccess').html('成功');
        }else{
            $('#isSuccess').html('失败') ;
        }
    }

</script>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
</div>
</body>
</html>
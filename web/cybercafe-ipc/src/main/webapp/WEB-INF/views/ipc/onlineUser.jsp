<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>在线用户列表</title>
    <link href="${ctx}/static/styles/ipc.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<form id="sform">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">在线用户查询</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <div class="mlr8">
                        <p><span class="ipm">场所编码<input type="text" name="siteCode"/></span>
                            <span class="ipm">监管中心编码<input type="text" name="monitorCode"/></span></p>
                        <p class="dp pt5"><span class="ipm">时间<input name="btime"  type="text" class="easyui-datetimebox" value="${terminalBO.btime}"/></span>
                            <span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox" value="${terminalBO.etime}"/></span></p>
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
            <table id="rtt" class="easyui-datagrid" title="在线用户列表" style="width:100%;height:auto"
                   data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				striped:true,
                pageSize:10,url:'${ctx}/ipc/onlineUserjson/${siteCode}',method:'get',queryParams:$.formParams('#sform')">
                <thead>
                    <tr>
                        <th data-options="field:'siteCode',align:'center'" width="10%">场所编码</th>
                        <th data-options="field:'monitorCode',align:'center'" width="10%">监管中心编码</th>
                        <th data-options="field:'accountId',align:'center'" width="10%">顾客账号</th>
                        <th data-options="field:'customerName',align:'center'" width="10%">顾客姓名</th>
                        <th data-options="field:'customerIdType',align:'center',formatter:replaceCert" width="15%">顾客证件类型</th>
                        <th data-options="field:'onlineTime',align:'center'" width="10%">上机时间</th>
                        <th data-options="field:'offlineTime',align:'center'" width="10%">下机时间</th>
                        <th data-options="field:'hostIp',align:'center'" width="19%">使用机器IP</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
    </form>
    <!--查询结果列表 end-->

<script>


    function rertt() {
        $("#rtt").datagrid("reload", $.formParams("#sform"));
    }

    /*替换顾客身份类型*/
    function replaceCert(val,row,index){
        var val=Number(val);
        switch (val){
            case 2:
                return '身份证';
                break;
            case 3:
                return '连锁会员';
                break;
            case 4:
                return '移动电话';
                break;
            case 5:
                return '电话号码';
                break;
            case 6:
                return '社保卡号码';
                break;
            case 7:
                return '学生证';
                break;
            case 8:
                return '军官证';
                break;
            case 9:
                return '警官证';
                break;
            case 10:
                return '士兵证';
                break;
            case 11:
                return '户口簿';
                break;
            case 12:
                return '护照';
                break;
            case 13:
                return '台胞证';
                break;
            case 14:
                return '回乡证';
                break;
            case 15:
                return '其他证件';
                break;
            case 19:
                return '手机';
                break;
            default :
                return '无法获取真实身份';
                break;
        }

    }
</script>
</body>
</html>
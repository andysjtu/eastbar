<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>单位资料查询</title>
    <link href="${ctx}/static/styles/unit.css" type="text/css" rel="stylesheet"/>
    <script src="${ctx}/static/js/unit.js" type="text/javascript"></script>
</head>
<body>
<!--查询筛选条件 begin -->
<div class="search">
    <div onclick="$('.searchContext').toggle();">消息查询</div>
    <table style="height: auto;width:60%; align:center;">
        <tbody class="searchContext">
        <tr>
            <!--前留白 -->
            <td width="150">&nbsp;</td>
            <!--条件输入  下面宽度可根据实际条件修改-->
            <td>
                消息标题：<input type="text" name="msgtitle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                发布日期: <input type="text" name="begindate" class="easyui-datetimebox" required style="width:200px" />
                至 <input type="text" name="enddate" class="easyui-datetimebox" required style="width:200px" />
            </td>
            <td>
                <!--   <a
                        href="javascript:void(0);" onclick="searchData(1, 10)"
                        onmouseout="MM_swapImgRestore()"
                        onmouseover="MM_swapImage('Image24','','${ctxs}/img/comm/other/c-cx-002.png',1)"><img
                        src="${ctxs}/img/comm/other/c-cx-001.png" name="Image24"
                        width="80" height="26" border="0" id="Image24"/></a> -->
                <input type="button"  value="查询" STYLE="width:60px;height: 28px;font-size: 12px;"/>
              <!--  <a href="javascript:void(0);" onclick="searchData(1, 10)"/>查询</a>-->
            </td>
            <!--后留白 -->
            <td>&nbsp;</td>
        </tr>
        </tbody>
    </table>
</div>
<!--查询筛选条件 end -->
<!--查询结果列表 begin-->
<div class="result">
    <!-- 发布列表 begin-->
    <div style="margin:2px 20px;">
        <table id="dg" class="easyui-datagrid" title="消息列表" style="width:100%;height:auto"
               data-options="rownumbers:true,
				singleSelect:false,
				autoRowHeight:false,
				pagination:true,
				pageSize:10,url:'${ctx}/static/json/auth/msgman/datagrid_msgman_public.json',method:'post',toolbar:'#tb'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'msgtitle',align:'center'" width="24%">消息标题</th>
                <th data-options="field:'msgpubdate',align:'center'" width="20%">发布日期</th>
                <th data-options="field:'msgpubperson',align:'center'" width="13%">发布人员</th>
                <th data-options="field:'msgstatus',align:'center'" width="10%">状态</th>
                <th data-options="field:'_operate',align:'center',formatter:formatOper" width="30%">操作</th>
            </tr>
            </thead>
        </table>
        <div id="tb" style="padding:5px;height:auto">
            <div style="margin-bottom:5px">
                <a href="/msgman/intoAdd" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
                <a href="/msgman/remove" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
            </div>
        </div>
    </div>
    <!-- 发布列表 end-->
</div>
<!--查询结果列表 end-->
<!--弹出对话框 begin-->
<div id="dlg" class="easyui-dialog" style="width:500px;height:360px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="fitem">
        <label>消息接收者:</label>
        <input id="msgreceperson" type="text" class="easyui-textbox" readonly="true" style="width: 300px">
    </div>
    <div class="fitem">
        <label>截止日期:</label>
        <input id="msgmisdate"  type="text"class="easyui-textbox" readonly="true" style="width: 300px">
    </div>
    <div class="fitem">
        <label>消息标题:</label>
        <input id="msgtitle" type="text" class="easyui-textbox" readonly="true" style="width: 300px">
    </div>
    <div class="fitem">
        <label>消息内容:</label>
        <textarea id="msgcontent" class="easyui-validatebox" readonly="true" required="true" style=" width:300px;height:100px;"></textarea>
    </div>
</div>
<!--弹出对话框 end-->
<script>
    function formatOper(val,row,index){
        return '<a href="#" onclick="show('+index+')">查看</a> &nbsp;&nbsp; '+
                '<a href="#" onclick="resp('+index+')">响应情况</a> &nbsp;&nbsp;&nbsp;&nbsp;'+
                '<a href="#" onclick="edit('+index+')" >修改</a> &nbsp;&nbsp;&nbsp;&nbsp;'+
                '<a href="#" onclick="del('+index+')">删除</a>';
    }

</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="version" value="<%=new Date().getTime() %>"/>
<html>
<head>
    <title>系统截屏</title>
    <link href="${ctx}/static/styles/ipc.css" type="text/css" rel="stylesheet"/>
</head>
<body>
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">终端信息</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <table border="1" width="100%">
                        <tr>
                            <td width="20%" height="25" align="center" class="new1">&nbsp;　场所编码</td>
                            <td width="20%" height="25" align="center" class="new1">${tb.siteCode}</td>
                            <td width="20%" height="25" align="center" class="new1">&nbsp;　场所名称</td>
                            <td width="20%" height="25" colspan="2" align="center" class="new1">${tb.siteName}</td>
                        </tr>
                        <tr>
                            <td width="20%" height="25" align="center" class="new1">&nbsp;　客户端名称</td>
                            <td width="20%" height="25" align="center" class="new1">${tb.hostIp}</td>
                            <td width="20%" height="25" align="center" class="new1">&nbsp;　客户端IP地址</td>
                            <td width="20%" height="25" colspan="2" align="center" class="new1">${tb.hostIp}</td>
                        </tr>
                    </table>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--查询筛选条件 end -->
    <!--查询结果列表 begin-->
    <div class="search mlr2 mt5">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr class="panel-header">
                <!--title -->
                <td class="panel-title">屏幕截图</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="capture panel-body">
                <td>
                    <div class="imgbox small">
                        <img class="small" src="${ctx}/ipc/${tb.siteCode}/jpdata/${tb.hostIp}/">
                    </div>
                    <div class="tools">
                        <button class="btn btn-info" onclick="back(${tb.siteCode});">返回</button><br/>
                        <%--<button class="btn btn-info">保存</button><br/>--%>
                        <button class="btn btn-info"onclick="bigPic();">放大</button><br/>
                        <button class="btn btn-info"onclick="smallPic();">缩小</button><br/>
                        <button class="btn btn-warning" onclick="capture(${tb.siteCode},'${tb.hostIp}');">截取屏幕</button>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
<script type="text/javascript">
    function bigPic(){
        $(".imgbox").addClass("big").removeClass("small");
        $(".imgbox>img").addClass("big").removeClass("small");
    }
    function smallPic(){
        $(".imgbox").addClass("small").removeClass("big");
        $(".imgbox>img").addClass("small").removeClass("big");
    }
    function capture(siteCode,ip){
        <%--$.ajax({--%>
            <%--type:"GET",--%>
            <%--url:"${ctx}/ipc/"+siteCode+"/jpdata/"+ip,--%>
            <%--success:function(data){--%>
                <%--$(".imgbox>img").attr("src","data:image/jpeg;base64,"+data);--%>
                <%--alert("A");--%>
            <%--}--%>
        <%--});--%>
        window.location.href = "${ctx}/ipc/"+siteCode+"/jp/"+ip+"/?.${version}#_0";
    }

    function back(siteCode){
        window.location="${ctx}/ipc/siteTerminal/"+siteCode+"#_0";
    }
</script>
</body>
</html>
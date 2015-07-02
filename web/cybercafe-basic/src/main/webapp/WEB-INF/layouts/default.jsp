<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="version" value="<%=new Date().getTime() %>"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><sitemesh:title/> - 上海市互联网上网服务营业场所计算机经营管理系统</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>

    <link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
    <link href="${ctx}/static/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/jquery-easyui/1.4/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/jquery-easyui/1.4/themes/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/default.css" type="text/css" rel="stylesheet"/>

    <script src="${ctx}/static/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery-easyui/1.4/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery-easyui/1.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

    <sitemesh:head/>

</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:auto;">
    <%@ include file="/WEB-INF/layouts/header.jsp" %>
</div>
<div data-options="region:'west',split:true,title:'Guide'" style="width:198px;">
    <%@ include file="/WEB-INF/layouts/left.jsp" %>
</div>
<div data-options="region:'south',border:false" style="height:20px;background:#FFFFFF;text-align: center;">
    <%@ include file="/WEB-INF/layouts/footer.jsp" %>
</div>
<div id="center" data-options="region:'center',title:''">
    <div class="right_top ">
        <i class="icon-home" style="margin-left: -15px;"></i> 您的当前位置 >> <sitemesh:title/>
        <div style="float: right;">
            <span>尊敬的用户</span>
            <span id="principal"><shiro:principal/></span>
            <span></span>
            <span> 欢迎您登录本系统</span>
            <span id="date">[ 2014-10-10 星期五  ]</span>
        </div>
    </div>
    <div><sitemesh:body/></div>
</div>

<script src="${ctx}/static/bootstrap/2.3.2/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
    function showTips(tipmsg) {
        if (tipmsg) {
            jQuery.messager.show({
                title: '操作提示',
                msg: tipmsg,
                showSpeed: 1000,
                timeout: 3000,
                showType: 'show'
            });
        }
    }

    $(function () {
        var tmpDate = new Date();
        var date = (tmpDate.getDate()<10)?"0"+tmpDate.getDate():tmpDate.getDate();
        var month= (tmpDate.getMonth()+1<10)?"0"+(tmpDate.getMonth()+1):tmpDate.getMonth()+1;
        var year= tmpDate.getFullYear();

        var weekday=tmpDate.getDay();
        myArray=new Array(6);
        myArray[0]="星期日"
        myArray[1]="星期一"
        myArray[2]="星期二"
        myArray[3]="星期三"
        myArray[4]="星期四"
        myArray[5]="星期五"
        myArray[6]="星期六"

        var data = "[ "+year+"-"+month+"-"+date+" "+myArray[weekday]+" ]";

        $("#date").html(data);
        showTips('${loadmsg}');
    });
    $(function(){
        $(".datebox :text").attr("readonly","readonly");
    })
</script>
<%--<script src="${ctx}/static/js/search.js" type="text/javascript"></script>--%>
<script src="${ctx}/static/js/jquery.customUtils.js" type="text/javascript"></script>
<script src="${ctx}/static/js/op.menu.js" type="text/javascript"></script>
</body>
</html>
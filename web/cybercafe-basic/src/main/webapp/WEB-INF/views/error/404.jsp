<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.setStatus(200);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>404 - 上海市互联网上网服务营业场所计算机经营管理系统</title>
    <style type="text/css">
        body{
            background: url(${ctx}/static/images/58.jpg) #EEFFFE;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: right bottom;
            text-align: center;
        }
        .vv{
            border: 1px dashed #D4D4D4 ;
            min-height: 220px;
            margin-top: 100px;
            background:rgba(255, 255, 255, 0.5) none repeat scroll 0 0 !important;/*实现FF背景透明，文字不透明*/
            filter:Alpha(opacity=80); background:#fff;/*实现IE背景透明*/
        }
    </style>
</head>

<body>
<div class="vv">
	<h2 style="margin-top: 50px;">404 - 此部分内容正在建设中......</h2>
	<p><a href="javascript:history.back(-1);">返回</a></p>
</div>
</body>
</html>
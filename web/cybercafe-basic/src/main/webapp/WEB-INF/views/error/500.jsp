<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%	
	//设置返回码200，避免浏览器自带的错误页面
	response.setStatus(200);
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(exception.getMessage(), exception);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>500</title>
    <style type="text/css">

        .vv{
            text-align: center;
            border: 1px dashed #D4D4D4 ;
            min-height: 220px;
            margin: 100px 10px;
        }
    </style>
</head>

<body>
<div class="vv">
    <h2 style="margin-top: 50px;">500 - 系统发生内部错误......</h2>
    <p style="margin-top: 20px;"><a href="javascript:history.back(-1);">返回</a></p>
</div>
</body>
</html>

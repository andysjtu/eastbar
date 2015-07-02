<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>更改电子邮件发送者</title>
    <!--  <link href="${ctx}/static/styles/sys.css" type="text/css" rel="stylesheet"/>-->
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/sys/submitEmailSenderEdit#_3" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <input type="hidden" name="notifierType" value="2"/>
                <input type="hidden" name="id" value="2"/>
                <hr/><blockquote> <small>电子邮件通知设置</small></blockquote>
                <div class="control-group">
                    <label class="control-label">发送方(合法Email)：</label>
                    <div class="controls">
                        <input type="text" name="sender" id="sender" value="${alarmNotify.sender}" placeholder="电子邮件">
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div style="margin: 0 auto 20px 150px;">
                    <input type="button" class="btn btn-primary" onclick="save()" value="   确定   "/>
                    <input type="button" class="btn btn-primary" onclick="returnList()" value="   返回   "/>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function save(){
        if(validateEmail()){
            $('#f1').submit();
        }
    }

    function returnList(){
        window.location="/sys/email#_3";
    }
</script>
<script src="${ctx}/static/js/sys.js"></script>
</body>
</html>

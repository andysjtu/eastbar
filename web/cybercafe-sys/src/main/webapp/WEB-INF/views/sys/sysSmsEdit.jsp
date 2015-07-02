<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改短消息付费者</title>
    <!--  <link href="${ctx}/static/styles/sys.css" type="text/css" rel="stylesheet"/>-->
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/sys/submitSmsEdit" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>付费者信息</small></blockquote>
                <input type="hidden" value="${alarmNotify.notifierType}" name="notifierType"/>
                <input type="hidden"  value="${alarmNotify.id}" name="id">
                <div class="control-group">
                    <label class="control-label">付费者(合法手机号码)</label>
                    <div class="controls">
                        <input type="text" name="sender" id="sender" value="${alarmNotify.sender}" placeholder="手机号码">
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
        if(validateSendPhone()){
            $('#f1').submit();
        }

    }

    function returnList(){
        window.location="/sys/sMs#_3";
    }
</script>
<script src="${ctx}/static/js/sys.js"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加短消息接收者</title>
  <!--  <link href="${ctx}/static/styles/sys.css" type="text/css" rel="stylesheet"/>-->
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/sys/submitSmsAdd#_3" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>接收者信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">接收者(合法手机号码)</label>
                    <div class="controls">
                        <input type="text" name="receiver" id="receiver" placeholder="手机号码">
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
        if(validateReceivePhone()){
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
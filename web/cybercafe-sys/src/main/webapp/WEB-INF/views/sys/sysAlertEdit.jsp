<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改等级设置</title>
    <%--<link href="${ctx}/static/styles/account.css"  type="text/css" rel="stylesheet"/>--%>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/sys/submitAlertEdit" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>报警等级基本信息</small></blockquote>
                <input type="hidden" value="${rankBO.id}" name="id"/>
                <div class="control-group">
                    <label class="control-label" for="rankName">等级名称</label>

                    <div class="controls">
                        <input type="text" id="rankName" value="${rankBO.rankName}" name="rankName" placeholder="等级名称" readonly="readonly">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">SMS短信</label>
                    <div class="controls">
                        <c:if test="${rankBO.isSms==0}">
                        <input type="radio" name="isSms" value="1">是
                        <input type="radio" name="isSms" value="0" checked="checked">否
                        </c:if>
                        <c:if test="${rankBO.isSms==1}">
                        <input type="radio" name="isSms" value="1" checked="checked">是
                        <input type="radio" name="isSms" value="0">否
                        </c:if>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >Email</label>
                    <div class="controls">
                        <c:if test="${rankBO.isEmail==0}">
                            <input type="radio" name="isEmail" value="1">是
                            <input type="radio" name="isEmail" value="0" checked="checked">否
                        </c:if>
                        <c:if test="${rankBO.isEmail==1}">
                            <input type="radio" name="isEmail" value="1" checked="checked">是
                            <input type="radio" name="isEmail" value="0">否
                        </c:if>
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
         $('#f1').submit();
    }

    function returnList(){
        window.location="/sys/alert#_3";
    }

</script>
<script src="${ctx}/static/js/sys.js"></script>
</body>
</html>
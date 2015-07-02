<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改监管中心</title>
    <link href="${ctx}/static/styles/ipc.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/ipc/submitEdit#_0" method="post" class="form-horizontal">
            <input type="hidden" value="${monitorBO.parentCode}" id="parent"/>
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>监管中心基本信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">监管中心编码</label>
                    <div class="controls">
                        <input type="text" name="monitorCode" value="${monitorBO.monitorCode}" placeholder="监管中心编码" readonly="readonly">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">监管中心名称</label>
                    <div class="controls">
                        <input type="text" name="name" id="name" value="${monitorBO.name}" placeholder="监管中心名称" onblur="validateMonitorName()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">邮政编码</label>
                    <div class="controls">
                        <input type="text" name="zip" id="zip" value="${monitorBO.zip}" placeholder="邮政编码" onblur="validateCode()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">中心地址</label>
                    <div class="controls">
                        <input type="text" name="address" value="${monitorBO.address}" placeholder="中心地址">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">许可场所总数</label>
                    <div class="controls">
                        <input type="text" name="permitSite" id="permitSite" value="${monitorBO.permitSite}" placeholder="许可场所总数" onblur="validateNum()">
                    </div>
                </div>
                <hr/><blockquote> <small>管理信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">负责人</label>
                    <div class="controls">
                        <input type="text" name="principal" value="${monitorBO.principal}" placeholder="负责人">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">负责人电话</label>
                    <div class="controls">
                        <input type="text" name="principalTel" id="principalTel" value="${monitorBO.principalTel}" placeholder="负责人电话" onblur="validatePrincipalTel()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">联系人</label>
                    <div class="controls">
                        <input type="text"  name="contactPerson" value="${monitorBO.contactPerson}" placeholder="联系人">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">联系电话</label>
                    <div class="controls">
                        <input type="text" name="contactTel" id="contactTel" value="${monitorBO.contactTel}" placeholder="联系电话" onblur="validateContactTel()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">E-mail</label>
                    <div class="controls">
                        <input type="text"  name="email" id="email" value="${monitorBO.email}" placeholder="e-mail" onblur="validateEmail()">
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
        window.location="/ipc/list#_0";
    }
</script>
<script src="${ctx}/static/js/ipc_validate.js" type="text/javascript"></script>
</body>
</html>
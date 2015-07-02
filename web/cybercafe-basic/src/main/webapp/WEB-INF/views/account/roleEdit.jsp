<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改角色</title>
    <link href="${ctx}/static/styles/account.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/account/submitRoleEdit#_1" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <input type="hidden" value="${roleBO.id}" name="id"/>
                <hr/><blockquote> <small>角色信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">监管中心：</label>
                    <div class="controls">
                        <select id="monitorCode" name="monitorCode">
                            <c:forEach items="${monitors}" var="monitor">
                                <option value="${monitor.monitorCode}">${monitor.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">显示名称：</label>
                    <div class="controls">
                        <input type="text" id="commonName" value="${roleBO.commonName}" name="commonName" placeholder="名称" onblur="validateCommonName()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">数据库名称：</label>
                    <div class="controls">
                        <input type="text" id="roleName" value="${roleBO.roleName}" name="roleName" placeholder="名称" onblur="validateRoleName()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">角色描述：</label>
                    <div class="controls">
                        <input type="text" value="${roleBO.roleDesc}" name="roleDesc" placeholder="修改描述">
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
        if(validateCommonName() && validateRoleName()){
            $('#f1').submit();
        }
    }

    function returnList(){
        window.location="/account/role#_1";
    }

    $(function() {
        var monitorCodes=document.getElementById("monitorCode");
        for(i=0;i<monitorCodes.length;i++){
            if(monitorCodes.options[i].value==${roleBO.monitor[0].monitorCode}){
                monitorCodes.options[i].selected=true;
            }
        }

    });
</script>
<script src="${ctx}/static/js/user.js" type="text/javascript"></script>
</body>
</html>
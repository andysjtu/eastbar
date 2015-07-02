<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加角色</title>
    <link href="${ctx}/static/styles/account.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
       <form id="f1" action="${ctx}/account/submitRoleAdd#_1" method="post" class="form-horizontal">
           <div class="mlr8" style="border: 1px solid #d4d4d4;">
               <hr/><blockquote> <small>角色信息</small></blockquote>
               <div class="control-group">
                   <label class="control-label">监管中心：</label>
                   <div class="controls">
                       <select name="monitorCode">
                           <c:forEach items="${monitors}" var="monitor">
                               <option value="${monitor.monitorCode}">${monitor.name}</option>
                           </c:forEach>
                       </select>
                   </div>
               </div>
               <div class="control-group">
                   <label class="control-label">显示名称：</label>
                   <div class="controls">
                       <input type="text" id="commonName" name="commonName" placeholder="显示名称" onblur="validateCommonName()">
                       *显示名称由2-6位汉字组成
                   </div>
               </div>
               <div class="control-group">
                   <label class="control-label">数据库名称：</label>
                   <div class="controls">
                       <input type="text" id="roleName" name="roleName" placeholder="数据库名称" onblur="validateRoleName()">
                       *数据库名称由2-12位字母组成
                   </div>
               </div>
               <div class="control-group">
                   <label class="control-label">角色描述：</label>
                   <div class="controls">
                       <input type="text" name="roleDesc" placeholder="添加描述">
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
</script>
<script src="${ctx}/static/js/user.js" type="text/javascript"></script>
</body>
</html>
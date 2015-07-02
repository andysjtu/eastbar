<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改用户角色</title>
    <link href="${ctx}/static/styles/account.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/account/submitUserRoleEdit#_1" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>修改用户角色</small></blockquote>
                <input type="hidden" value="${userBO.id}" name="userId"/>
                <div class="control-group">
                    <label class="control-label">账号：</label>
                    <div class="controls">
                        <input type="text" id="account" value="${userBO.loginName}" placeholder="账号" onblur="validateAccount()" readonly="readonly">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">当前角色：</label>
                    <div class="controls">
                        <c:forEach items="${userBO.roles}" var="roles">
                           <<span>${roles.commonName}</span>>
                        </c:forEach>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">监管中心</label>

                    <div class="controls">
                        <select id="monitor" onclick="showRole()">
                            <option value="-1">--请选择--</option>
                            <c:forEach items="${monitors}" var="monitor">
                                <option  value="${monitor.monitorCode}">${monitor.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">角色：</label>
                    <div class="controls">
                        <select id="domain" name="roleId">
                            <option value="-1">--请选择--</option>
                        </select>
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
        window.location="/account/user#_1";
    }

    function showRole(){
        var monitor=$('#monitor');
        $("#domain").html("");
        if(monitor.val()==-1){
            document.getElementById("domain").options[0]=new Option("--请选择--");
        }else{
            $.ajax({
                type:'POST',
                url: '${ctx}/account/getRoles/'+monitor.val(),
                data: '',
                dataType: "json",
                success: function(data){
                    var obj = eval(data);
                    for(var i=0;i<obj.length;i++){
                        document.getElementById("domain").options[i] = new Option(obj[i].commonName,obj[i].id);
                    }
                }
            });
        }
    }
</script>
<script src="${ctx}/static/js/user.js" type="text/javascript"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改用户</title>
    <%--<link href="${ctx}/static/styles/account.css"  type="text/css" rel="stylesheet"/>--%>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/account/submitUserEdit#_1" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>账号信息</small></blockquote>
                <input type="hidden" value="${userBO.id}" name="id"/>
                <div class="control-group">
                    <label class="control-label" for="account">账号</label>

                    <div class="controls">
                        <input type="text" id="account" name="loginName" value="${userBO.loginName}" placeholder="账号" onblur="checkName()">
                        *请输入由字母组成的3-20位的用户名
                        <input type="hidden" id="oldName" value="${userBO.loginName}">
                    </div>
                </div>
                <%--<div class="control-group">--%>
                    <%--<label class="control-label" for="oldPassword">旧密码</label>--%>
                    <%--<div class="controls">--%>
                        <%--<input type="password" id="oldPassword" value="${userBO.password}" placeholder="oldPassword">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="control-group">--%>
                    <%--<label class="control-label" for="newPassword">新密码</label>--%>
                    <%--<div class="controls">--%>
                        <%--<input type="text" id="newPassword" name="password" placeholder="newPassword">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <hr/><blockquote> <small>用户信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label" for="commonName">姓名</label>

                    <div class="controls">
                        <input type="text" id="commonName" name="commonName" value="${userBO.commonName}" placeholder="commonName" onblur="validateName()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="idCard">身份证</label>

                    <div class="controls">
                        <input type="text" id="idCard" name="idCard" value="${userBO.idCard}" placeholder="idCard" onblur="validateCard()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="mobile">手机</label>

                    <div class="controls">
                        <input type="text" id="mobile" name="mobile" value="${userBO.mobile}" placeholder="mobile" onblur="validatePhone()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="email">邮箱</label>

                    <div class="controls">
                        <input type="text" id="email" name="email" value="${userBO.email}" placeholder="Email" onblur="validateEmail()">
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
        if(checkName()){
            $('#f1').submit();
        }

    }

    function returnList(){
        window.location="/account/user#_1";
    }

    function checkName(){
        var oldName=$("#oldName").val().trim();
        var account=$("#account").val().trim();
        if(oldName==account){
            return true;
        }else{
            if(inputAccount()){
                var flag=false;
                $.ajax({
                    type:'POST',
                    url: '${ctx}/account/getByName/'+account,
                    data: '',
                    dataType: "json",
                    async: false,
                    success: function(data){
                        if(!data){
                            flag= validateAccount();
                        }else{
                            alert("用户名已存在");
                            flag=false;
                        }
                    }
                });
                return flag;
            }else{
                return false;
            }
        }

    }
</script>
<script src="${ctx}/static/js/user.js" type="text/javascript"></script>
</body>
</html>
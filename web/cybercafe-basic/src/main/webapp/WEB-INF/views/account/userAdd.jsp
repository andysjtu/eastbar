<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加用户</title>
    <%--<link href="${ctx}/static/styles/account.css"  type="text/css" rel="stylesheet"/>--%>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="submitUserAdd#_1" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>账号信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label" for="account">账号</label>

                    <div class="controls">
                        <input type="text" name="loginName" id="account" placeholder="账号" onblur="checkName()">
                         *请输入由字母组成的3-20位的用户名
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="password">密码</label>

                    <div class="controls">
                        <input type="password" id="password" name="password" onblur="pwReview();" placeholder="Password"> *密码复杂度要求必须8位以上，同时包含字母和数字，字母至少其中一个为大写<label for="ck"><input type="checkbox" name="ck" id="ck" onclick="defaultPw();">使用默认密码</label>
                    </div>
                </div>
                <hr/><blockquote> <small>用户信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label" for="commonName">姓名</label>

                    <div class="controls">
                        <input type="text" id="commonName" name="commonName" placeholder="commonName" onblur="validateName()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="idCard">身份证</label>

                    <div class="controls">
                        <input type="text" id="idCard" name="idCard" placeholder="idCard" onblur="validateCard()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="mobile">手机</label>

                    <div class="controls">
                        <input type="text" id="mobile" name="mobile" placeholder="mobile" onblur="validatePhone()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="email">邮箱</label>

                    <div class="controls">
                        <input type="text" id="email" name="email" placeholder="Email" onblur="validateEmail()">
                    </div>
                </div>
                <hr/><blockquote> <small>角色信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label" for="domain">监管中心</label>

                    <div class="controls">
                        <select id="monitor"  onclick="showRole()">
                            <option value="-1">--请选择--</option>
                            <c:forEach items="${monitors}" var="monitor">
                                <option  value="${monitor.monitorCode}">${monitor.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="domain">角色</label>

                    <div class="controls">
                        <select id="domain" name="roleId">
                            <option value="-1">--请选择--</option>
                        </select>*角色为必选项
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
            if(pwReview()){
                if(checkRole()){
                    $('#f1').submit();
                }
            }
        }

    }

    function pwReview(){
        var pw = $("#password").val();
        var reg1 = /[A-Z]+/;
        var reg2 = /[0-9]+/;
        if(pw.length>=8){
            if(reg1.test(pw) && reg2.test(pw)){
                return true;
            }else{
                alert("密码包含字母和数字，字母至少其中一个为大写.");
                return false;
            }
        }else{
            alert("密码长度必须8位以上.");
            return false;
        }
    }

    function defaultPw(){
        var pw = $("#password");
        if($("input[name='ck']").is(":checked")){
            pw.attr("readonly","readonly");
            pw.val("Pengyue403");
        }else{
            pw.removeAttr("readonly","readonly");
            pw.val("");
        }
    }

    function returnList(){
        window.location="/account/user#_1";
    }

    function showRole(){
        var monitor=$('#monitor').val();
        $("#domain").html("");
        if(monitor==-1){
            document.getElementById("domain").options[0]=new Option("--请选择--");
        }else{
                    $.ajax({
                        type:'POST',
                        url: '${ctx}/account/getRoles/'+monitor,
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

    function checkName(){
        var account=$("#account").val().trim();
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
                       flag=validateAccount();
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
</script>
<script src="${ctx}/static/js/user.js" type="text/javascript"></script>
</body>
</html>
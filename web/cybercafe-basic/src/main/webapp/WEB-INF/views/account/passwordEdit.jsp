<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>更改密码</title>
    <link href="${ctx}/static/styles/account.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
       <form id="f1" action="${ctx}/account/submitPasswordEdit" method="post" class="form-horizontal">
           <div class="mlr8" style="border: 1px solid #d4d4d4;">
               <hr/><blockquote> <small>更改密码</small></blockquote>
               <input type="hidden" value="${userBO.id}" name="id"/>
               <%--<div class="control-group">--%>
                   <%--<label class="control-label" for="newPassword">原密码</label>--%>

                   <%--<div class="controls">--%>
                       <%--<input type="password" id="oldPassword" name="password" placeholder="旧密码">--%>
                   <%--</div>--%>
               <%--</div>--%>
               <div class="control-group">
                   <label class="control-label" for="newPassword">新密码</label>

                   <div class="controls">
                       <input type="password" id="newPassword" name="password" placeholder="新密码" onblur="pwReview($('#newPassword').val());"> *密码复杂度要求必须8位以上，同时包含字母和数字，字母至少其中一个为大写.
                   </div>
               </div>
               <div class="control-group">
                   <label class="control-label" for="newPasswordRe">新密码确认</label>

                   <div class="controls">
                       <input type="password" id="newPasswordRe" placeholder="新密码确认" onblur="pwReview($('#newPasswordRe').val());">
                   </div>
               </div>
               <label for="ck" style="margin-left:180px; "><input type="checkbox" name="ck" id="ck" onclick="defaultPw();">使用默认密码</label>
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
        if($('#newPassword').val()==$('#newPasswordRe').val() && pwReview($('#newPassword').val())){
           $('#f1').submit();
        }else{
          window.alert("两次输入的密码不一致！")
        }
    }

    function defaultPw(){
        var npw = $("#newPassword");
        var npwre = $("#newPasswordRe");
        if($("input[name='ck']").is(":checked")){
            npw.attr("readonly","readonly");
            npwre.attr("readonly","readonly");

            npw.val("Pengyue403");
            npwre.val("Pengyue403");
        }else{
            npw.removeAttr("readonly","readonly");
            npwre.removeAttr("readonly","readonly");
            npw.val("");
            npwre.val("");
        }
    }

    function pwReview(pw){
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

    function returnList(){
        window.location="/account/user#_1";
    }
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>新建监管中心</title>
    <link href="${ctx}/static/styles/ipc.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/ipc/submitAdd#_0" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <input type="hidden" id="grade" value="${grade}"/>
                <hr/><blockquote> <small>监管中心基本信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">监管中心等级</label>
                    <div class="controls">
                        <select name="type" id="type" onclick="changeParent()">
                            <option value="1">省/市</option>
                            <option value="2">县/区</option>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">上级监管中心</label>
                    <div class="controls">
                        <select name="parentCode" id="parentCode">
                            <option value="-1">--无上级监管中心--</option>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">监管中心编码</label>
                    <div class="controls">
                        <input type="text" name="monitorCode" id="monitorCode" placeholder="监管中心编码" onblur="validateMonitor()">
                        *必填（省/市级监管中心编码由4位数字组成，区/县级监管中心编码由6位数字组成）
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">监管中心名称</label>
                    <div class="controls">
                        <input type="text" name="name" id="name" placeholder="监管中心名称" onblur="validateMonitorName()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">邮政编码</label>
                    <div class="controls">
                        <input type="text" name="zip" id="zip" placeholder="邮政编码" onblur="validateCode()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">中心地址</label>
                    <div class="controls">
                        <input type="text" name="address" placeholder="中心地址">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">许可场所总数</label>
                    <div class="controls">
                        <input type="text" name="permitSite" id="permitSite" placeholder="许可场所总数" onblur="validateNum()">
                    </div>
                </div>
                <hr/><blockquote> <small>管理信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">负责人</label>
                    <div class="controls">
                        <input type="text" name="principal" placeholder="负责人">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">负责人电话</label>
                    <div class="controls">
                        <input type="text" name="principalTel" id="principalTel" placeholder="负责人电话" onblur="validatePrincipalTel()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">联系人</label>
                    <div class="controls">
                        <input type="text"  name="contactPerson" placeholder="联系人">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">联系电话</label>
                    <div class="controls">
                        <input type="text" name="contactTel" id="contactTel" placeholder="联系电话" onblur="validateContactTel()">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">E-mail</label>
                    <div class="controls">
                        <input type="text"  name="email" id="email" placeholder="e-mail" onblur="validateEmail()">
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
        if(validateMonitor()){
            $('#f1').submit();
        }
    }

    function returnList(){
        window.location="/ipc/list#_0";
    }

   function changeParent(){
        var type=$("#type").val();
       if(type==1){
           $("#parentCode").empty();
           document.getElementById("parentCode").options[0]=new Option("--无上级监管中心--","-1");
       }
        if(type==2){
            $.ajax({
                type:'POST',
                url: '${ctx}/ipc/getParent/'+2,
                data: '',
                dataType: "json",
                success: function(data){
                    var obj = eval(data);
                    for(var i=0;i<obj.length;i++){
                        document.getElementById("parentCode").options[i] = new Option(obj[i].name,obj[i].monitorCode);
                    }
                }
            });
        }
   }

    $(function(){
       var grade=$("#grade").val();
       if(grade==1){
           $("#type").empty();
           document.getElementById("type").options[0]=new Option("县/区","2");
           changeParent();
       }
    });

</script>
<script src="${ctx}/static/js/ipc_validate.js" type="text/javascript"></script>
</body>
</html>
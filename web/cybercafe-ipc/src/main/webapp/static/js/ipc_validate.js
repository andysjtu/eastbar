function validateMonitor(){
    var monitorCode=$("#monitorCode").val().trim();
    var type=$("#type").val();
    var reg=/^[0-9]{4}$/;
    var reg2=/^[0-9]{6}$/;
    if(type==1){
        if(reg.test(monitorCode)){
            return true;
        }else{
            alert("省/市级监管中心编码由4位数字组成!");
            return false;
        }
    }else{
        if(reg2.test(monitorCode)){
            return true;
        }else{
            alert("区/县级监管中心编码由6位数字组成!");
            return false;
        }
    }
}

function validateMonitorName(){
    var name=$("#name").val().trim();
    if(name.length>=3 && name.length<=20){
        return true;
    }else{
        alert("监管中心名称由3-20位字符组成!");
        return false;
    }
}

function validateCode(){
    var code=$("#zip").val().trim();
    var reg=/^[0-9]{6}$/;
    if(reg.test(code)){
        return true;
    }else{
        alert("请输入合法的邮政编码!");
        return false;
    }
}

function validateNum(){
    var permitSite=$("#permitSite").val().trim();
    var reg=/[0-9]/
    if(reg.test(permitSite)){
        return true;
    }else{
        alert("许可场所总数必须是数字");
        return false;
    }
}

function validatePrincipalTel(){
    var principalTel=$("#principalTel").val().trim();
    var reg=/^[0-9]{8,11}$/;
    if(reg.test(principalTel)){
        return true;
    }else{
        alert("请输入负责人的合法联系电话！");
        return false;
    }
}

function validateContactTel(){
    var principalTel=$("#principalTel").val().trim();
    var reg=/^[0-9]{8,11}$/;
    if(reg.test(principalTel)){
        return true;
    }else{
        alert("请输入合法的联系电话！");
        return false;
    }
}

function validateEmail(){
    var email=$("#email").val().trim();
    var reg=/^[_a-z0-9]+(\.?[a-z0-9]+)@([_a-z0-9]+\.)[a-z0-9]{2,3}$/;
    if(reg.test(email)){
        return true;
    }else{
        alert("请输入合法的email!");
        return false;
    }
}

function validateSite(){
    var siteCode=$("#siteCode").val().trim();
    var reg=/^[0-9]{10}$/;
    if(reg.test(siteCode)){
       return true;
    }else{
       alert("场所编码由10位数字组成!");
       return false;
    }

}

function validateTerminalNum(){
    var permitSite=$("#terminalNum").val().trim();
    var reg=/[0-9]/
    if(reg.test(permitSite)){
        return true;
    }else{
        alert("终端总数必须是数字");
        return false;
    }
}

function validateSiteName(){
    var name=$("#name").val().trim();
    if(name.length>=3 && name.length<=20){
        return true;
    }else{
        alert("场所名称由3-20位字符组成!");
        return false;
    }
}


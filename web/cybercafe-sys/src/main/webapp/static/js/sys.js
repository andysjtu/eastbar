function validateSendPhone(){
    var sendPhone=$("#sender").val().trim();
    var reg=/^[1-9][0-9]{10}$/
    if(sendPhone.length!=11 && sendPhone.length!=0 ){
        alert("输入的手机位数不对");
        return false;
    }else if(sendPhone.length==0){
        alert("手机号码不能为空");
        return false;
    }else{
        if(reg.test(sendPhone)){
            return true;
        }else{
            alert("请输入合法的手机号码!");
            return false;
        }
    }
}


function validateReceivePhone(){
    var receiverPhone=$("#receiver").val().trim();
    var reg=/^[1-9][0-9]{10}$/
    if(receiverPhone.length!=11 && receiverPhone.length!=0){
        alert("输入的手机位数不对");
        return false;
    }else if(receiverPhone.length==0){
        alert("手机号码不能为空");
        return false;
    }else{
        if(reg.test(receiverPhone)){
            return true;
        }else{
            alert("请输入合法的手机号码!");
            return false;
        }
    }
}

function validateIp(){
    var smtpAddress=$("#smtpAddress").val().trim();
    var reg=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
    if(smtpAddress.length==0){
        alert("IP地址不为空");
    }
    if(reg.test(smtpAddress)){
        if(RegExp.$1<256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256){
            return true;
        }else{
            alert("请输入合法的IP地址");
            return false;
        }
    }else{
        return false;
    }
}

function validatePort(){
    var port=$("#smtpPort").val().trim();
    var reg=/^\d+$/;
    if(reg.test(port)){
        if(port<=65535){
            return true;
        }else{
            alert("请输入合法的端口号!")
            return false;
        }
    }else{
        return false;
    }
}

function validateEmail(){
    var email=$("#sender").val().trim();
    var reg=/^[_a-z0-9]+(\.?[a-z0-9]+)@([_a-z0-9]+\.)[a-z0-9]{2,3}$/;
    if(reg.test(email)){
        return true;
    }
    else{
        alert("需要填入合法邮箱地址!")
        return false;
    }
}

function validateReceiverEmail(){
    var email=$("#receiver").val().trim();
    var reg=/^[_a-z0-9]+(\.?[a-z0-9]+)@([_a-z0-9]+\.)[a-z0-9]{2,3}$/;
    if(reg.test(email)){
        return true;
    }
    else{
        alert("需要填入合法邮箱地址!")
        return false;
    }
}

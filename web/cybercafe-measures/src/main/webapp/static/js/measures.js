function validateUrl(){
    var type=$("#urlType").val();
    var url=$("#urlValue").val().trim();
    if(type==1){
        var reg=/^([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.([a-z]{2,6})$/;
        if(reg.test(url)){
            return true;
        }else{
            alert("请输入合法的域名!");
            return false;
        }
    }else if(type==3){
        var reg =/^((https|http|ftp|rtsp|mms)?:?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|[0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)$/;
        if (reg.test(url)){
            return true;
        }else{
            alert("请输入合法的URL")
            return false;
        }
    }else{
        var reg=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
        if(reg.test(url)){
            if(RegExp.$1<256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256){
                return true;
            }else{
                alert("请输入合法的IP地址");
                return false;
            }
        }else{
            alert("请输入合法的IP地址");
            return false;
        }
    }
}

function validateUrls(type,url){
    if(type==1){
        var reg=/^([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.([a-z]{2,6})$/;
        if(reg.test(url.value)){
            return true;
        }else{
            alert("请输入合法的域名!");
            return false;
        }
    }else if(type==3){
        var reg =/^((https|http|ftp|rtsp|mms)?:?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|[0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)$/;
        if (reg.test(url.value)){
            return true;
        }else{
            alert("请输入合法的URL")
            return false;
        }
    }else{
        var reg=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
        if(reg.test(url.value)){
            if(RegExp.$1<256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256){
                return true;
            }else{
                alert("请输入合法的IP地址");
                return false;
            }
        }else{
            alert("请输入合法的IP地址");
            return false;
        }
    }
}

function validateGames(progName){
    if(progName.length!=0){
        return true;
    }else{
        alert("程序名称为必填项!");
        return false;
    }
}

function validateGame(){
    var progName=$("#progName").val();
    if(progName.length==0){
        alert("关键字为必填项");
    }else{
        return true;
    }
}

function validateFeatureCodes(featureCode){
     if(!isNaN(featureCode)){
         return true;
     }else{
         alert("特征码必须是数字!");
         return false;
     }
}
function validateSpecial(){
    var name=$("#name").val().trim();
    var reg=/^[a-z,\u4e00-\u9fa5]{2,20}$/
    if(name.length!=0){
        if(reg.test(name)){
            return true;
        }else{
            alert("特殊人员姓名只包含字母或者汉字!");
            return false;
        }
    }else{
        alert("特殊人员姓名为必填项!");
        return false;
    }
}

function validateSpecials(sname){
    var name=sname.value.trim();
    var reg=/^[a-z,\u4e00-\u9fa5]{2,20}$/
    if(name.length!=0){
        if(reg.test(name)){
            return true;
        }else{
            alert("特殊人员姓名只包含字母或者汉字!");
            return false;
        }
    }else{
        alert("特殊人员姓名为必填项!");
        return false;
    }
}

function validateCert(){
    var certId=$("#certId").val().trim();
    var certType=$("#certType").val();
    if(certType==1){
        var reg=/^[1-9][0-9]{17,19}$/;
        if(reg.test(certId)){
           return true;
        }else{
            alert("请输入合法的证件号码!");
            return false;
        }
    }
}

function validateCerts(certId){
        var cert=certId.value.trim();
        var reg=/^[1-9][0-9]{17,19}$/;
        if(reg.test(cert)){
            return true;
        }else{
            alert("请输入合法的证件号码!");
            return false;
        }
}

function validateKeyword(){
     var keyword=$("#keyword").val();
    if(keyword.length==0){
        alert("关键字为必填项");
    }else{
        return true;
    }
}

function validateKeywords(keyword){
    var value=keyword.value;
    if(value.length==0){
        alert("关键字为必填项");
    }else{
        return true;
    }
}
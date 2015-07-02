function inputAccount(){
    var account=$("#account").val();
    if(account ==null || account==""){
        alert("账号不能为空!");
        return false;
    }else{
        return true;
    }
}

function validateAccount(){
    var account=$("#account").val().trim();
    var reg=/[a-z,A-Z]/;
    if(account.length>=3 && account.length<=20){
        if(reg.test(account)){
            return true;
        }else{
            alert("账号必须是由字母组成!");
            return false;
        }
    }else{
        alert("账号长度不能少于3位,且不能大于20位!");
        return false;
    }
}

function validateName(){
    var name=$("#commonName").val().trim();
    if(name.length<=12 && name.length>=2){
        return true;
    }else{
        alert("姓名需要填入2-12位字符");
        return false;
    }
}

function validateCard(){
    var card=$("#idCard").val().trim();
    var reg=/^[1-9][0-9]{17,19}$/;
    if(reg.test(card)){
        return true;
    }else{
        alert("需要填入18-20位有效身份证件号码!");
        return false;
    }
}

function validatePhone(){
    var mobile=$("#mobile").val().trim();
    var reg=/^[1-9][0-9]{10}$/;
    if(reg.test(mobile)){
        return true;
    }
    else{
        alert("需要填入11位合法手机号码!")
        return false;
    }
}

function validateEmail(){
    var email=$("#email").val().trim();
    var reg=/^[_a-z0-9]+(\.?[a-z0-9]+)@([_a-z0-9]+\.)[a-z0-9]{2,3}$/;
    if(reg.test(email)){
        return true;
    }
    else{
        alert("需要填入合法邮箱地址!")
        return false;
    }
}

function validateCommonName(){
    var name=$("#commonName").val().trim();
    var reg=/^[\u4e00-\u9fa5]{2,20}$/
    if(reg.test(name)){
        return true;
    }else{
        alert("显示名称由2-20位汉字组成");
        return false;
    }
}

function validateRoleName(){
    var name=$("#roleName").val().trim();
    var reg=/^[a-z,A-Z]{2,12}/
    if(reg.test(name)){
        return true;
    }else{
        alert("数据库名称由2-12位字母组成");
        return false;
    }
}

function checkRole(){
    var roles=$("#domain option:selected").val();
    if(typeof(roles) != "undefined"){
        var role=roles.trim();
        if(role=="-1"){
            alert("角色为必选项!");
            return false;
        }else{
            return true;
        }
    }else{
        return false;
    }
}

/*替换顾客身份类型*/
function replaceCert(val,row,index){
    var val=Number(val);
    switch (val){
        case 2:
            return '身份证';
            break;
        case 3:
            return '连锁会员';
            break;
        case 4:
            return '移动电话';
            break;
        case 5:
            return '电话号码';
            break;
        case 6:
            return '社保卡号码';
            break;
        case 7:
            return '学生证';
            break;
        case 8:
            return '军官证';
            break;
        case 9:
            return '警官证';
            break;
        case 10:
            return '士兵证';
            break;
        case 11:
            return '户口簿';
            break;
        case 12:
            return '护照';
            break;
        case 13:
            return '台胞证';
            break;
        case 14:
            return '回乡证';
            break;
        case 15:
            return '其他证件';
            break;
        case 19:
            return '手机';
            break;
        default :
            return '无法获取真实身份';
            break;
    }
}
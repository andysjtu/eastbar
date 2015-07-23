/*替换顾客身份类型*/
function replaceCert(val,row,index){
    var val=Number(val);
    switch (val){
        case 1:
            return '身份证';
            break;
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

function replaceReason(val,row,index){
    var val=Number(val);
    switch (val){
        case 1:
            return '涂改、出租、出借等转让"经营许可证';
            break;
        case 2:
            return '"营许可证" 在规定的营业时间外营业';
            break;
        case 3:
            return '接纳未成年人进入营业场所';
            break;
        case 4:
            return '经营非网络游戏及违法游戏';
            break;
        case 5:
            return '擅自停止实施经营管理技术措施';
            break;
        case 6:
            return '未悬挂网络文化经营许可证';
            break;
        case 7:
            return '未通过局域网的方式接入互联网';
            break;
        case 8:
            return '未建立场内巡查制度';
            break;
        case 9:
            return '发现消费者违法行为未予制止并举报';
            break;
        case 10:
            return '未按规定核对、登记上网者的有效证件';
            break;
        case 11:
            return '未记录有关上网信息';
            break;
        case 12:
            return '未按规定保存登记内容、记录备份';
            break;
        case 13:
            return '变更名称、住所、法人或负责人等';
            break;
        case 14:
            return '其他';
            break;
        default :
            return '无法识别';
            break;
    }
}

function replaceResult(val,row,index){
    if(val==1){
        return '已处理完';
    }
    if(val==0){
        return '处理中'
    }
    if(val==-1){
        return "未开始处理";
    }
}

function replaceStatus(val,row,index){
    if(val==3){
        return '在线';
    }
    else if(val==0){
        return '离线';
    }
    else{
        return '无法识别';
    }
}
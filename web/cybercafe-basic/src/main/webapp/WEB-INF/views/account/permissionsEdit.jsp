<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>权限设置</title>
    <link href="${ctx}/static/styles/account.css"  type="text/css" rel="stylesheet"/>
    <script>
	
	    function LTrim(str) { //去掉字符串 的头空格
		var i;
		for (i = 0; i < str.length; i++)
			if (str.charAt(i) != " " && str.charAt(i) != " ")
				break;
		    str = str.substring(i, str.length);
		    return str;
	    }
	    function RTrim(str) {
		var i;
			for (i = str.length - 1; i >= 0; i--) {
				if (str.charAt(i) != " " && str.charAt(i) != " ")
					break;
			}
			str = str.substring(0, i + 1);
			return str;
		}
        function Trim(str) {

            return LTrim(RTrim(str));

        }
        function checkModel(id, myself) {
            var checkboxs = $("." + id + " INPUT");
            //var subCheckboxs = $("#" + subId + " INPUT");
            if (myself.checked) {
                for ( var i = 0; i < checkboxs.length; i++) {
                    checkboxs[i].checked = true;
                }
            } else {
                for ( var i = 0; i < checkboxs.length; i++) {
                    checkboxs[i].checked = false;
                }
            }
        }
		
		function checkForm() {
                var checked = false;
                var ids = document.getElementsByName("permission");
                for ( var i = 0; i < ids.length; i++) {
                    if (ids[i].checked) {
                        checked = true;
                    }
                }
                    return true;
                if (!checked) {
                    alert("请至少选择其中一个权限！");
                    return false;
                }

	    }
		
		$(function() {
		var permissions = document.getElementsByName("permission");
		var p = document.getElementById("perm").value;
		var permissionList = p.substring(1, p.length - 1).split(",");
		for ( var i = 0; i < permissionList.length; i++) {
			{
				for ( var j = 0; j < permissions.length; j++)
					if (permissions[j].value == Trim(permissionList[i])) {
						permissions[j].checked = true;
					}
			}
		}

	});
    </script>
	
</head>
<body>
<div class="result">
    <div style="margin:10px">
        <form id="f1" action="${ctx}/account/submitPermissionsEdit#_1" onsubmit="return checkForm();" method="post">
			<input type="hidden" value="${roleBO.id }" name="id" />
			<input type="hidden" value="${roleBO.shrioPermissions }" id="perm" />
            <table width="80%" class="tab_line">
                <tr>
                    <td align="left"  class="tab_top_title02" colspan="4">用户权限管理</td>
                </tr>
                <tr class="tab_line">
                    <td>所属模块</td>
                    <td>所属子模块</td>
                    <td>角色权限</td>
                </tr>
                <tr id="account" class="account">
                    <td rowspan="2">
                        <input type="checkbox" value="sysm:leftMenu:account" name="permission" onclick="checkModel('account',this);"/>用户与角色管理
                    </td>
                    <td class="accountInfo">
                        <input type="checkbox" value="account:user:all" name="permission" onclick="checkModel('accountInfo',this);" id="user"/>用户及管理员
                    </td>
                    <td class="accountInfo">
                        <input type="checkbox" name="permission" value="account:user:view"/>查看用户
                        <input type="checkbox" name="permission" value="account:user:select"/>查询用户
                        <input type="checkbox" name="permission" value="account:user:add"/>添加用户
                        <input type="checkbox" name="permission" value="account:user:edit"/>修改用户
                        <input type="checkbox" name="permission" value="account:user:delete"/>删除用户
                        <input type="checkbox" name="permission" value="account:user:deletemany"/>批量删除用户
                        <input type="checkbox" name="permission" value="account:user:editRole"/>修改用户角色
                        <input type="checkbox" name="permission" value="account:user:editPassword"/>修改密码
                    </td>
                </tr>
                <tr class="account">
                    <td class="role">
                        <input type="checkbox" id="role" name="permission" value="account:role:all" onclick="checkModel('role',this);" />角色
                    </td>
                    <td class="role">
                        <input type="checkbox" name="permission" value="account:role:view"/>查看角色
                        <input type="checkbox" name="permission" value="account:role:add"/>添加角色
                        <input type="checkbox" name="permission" value="account:role:edit"/>修改角色
                        <input type="checkbox" name="permission" value="account:role:delete"/>删除角色
                        <input type="checkbox" name="permission" value="account:role:deletemany"/>批量删除角色
                        <input type="checkbox" name="permission" value="account:role:editPermission" />修改角色权限
                    </td>
                </tr>
                <tr id="measures" class="measures">
                    <td rowspan="6">
                        <input type="checkbox" value="sysm:leftMenu:measures" name="permission" onclick="checkModel('measures',this);"/>监管措施
                    </td>
                    <td class="shopHour">
                        <input type="checkbox" id="shopHour" name="permission" value="measures:shopHour:all" onclick="checkModel('shopHour',this);"/>场所营业时间</td>
                    <td class="shopHour">
                        <input type="checkbox" name="permission" value="measures:shopHour:view" />查看营业场所时间
                        <input type="checkbox" name="permission" value="measures:shopHour:add" />添加营业场所时间
                        <input type="checkbox" name="permission" value="measures:shopHour:edit" />修改营业场所时间
                        <input type="checkbox" name="permission" value="measures:shopHour:delete" />删除营业场所时间
                        <input type="checkbox" name="permission" value="measures:shopHour:deletemany" />批量删除营业场所时间
                        <input type="checkbox" name="permission" value="measures:shopHour:public" />发布营业场所时间
                    </td>
                </tr>
                <tr class="measures">
                    <td class="url">
                        <input type="checkbox" id="url" name="permission" value="measures:url:all" onclick="checkModel('url',this);"/>禁止URL库</td>
                    <td class="url">
                        <input type="checkbox" name="permission" value="measures:url:view"/>查看禁止URL
                        <input type="checkbox" name="permission" value="measures:url:select" />查询禁止URL
                        <input type="checkbox" name="permission" value="measures:url:add" />添加禁止URL
                        <input type="checkbox" name="permission" value="measures:url:edit" />修改禁止URL
                        <input type="checkbox" name="permission" value="measures:url:delete" />删除禁止URL
                        <input type="checkbox" name="permission" value="measures:url:deletemany" />批量删除禁止URL
                        <input type="checkbox" name="permission" value="measures:url:public" />发布禁止URL
                    </td>
                </tr>
                <tr class="measures">
                    <td class="game">
                        <input type="checkbox" id="game" name="permission" value="measures:game:all" onclick="checkModel('game',this);"/>禁止游戏库</td>
                    <td class="game">
                        <input type="checkbox" name="permission" value="measures:game:view"/>查看禁止游戏
                        <input type="checkbox" name="permission" value="measures:game:select"/>查询禁止游戏
                        <input type="checkbox" name="permission" value="measures:game:add"/>添加禁止游戏
                        <input type="checkbox" name="permission" value="measures:game:edit"/>修改禁止游戏
                        <input type="checkbox" name="permission" value="measures:game:delete"/>删除禁止游戏
                        <input type="checkbox" name="permission" value="measures:game:deletemany"/>批量删除禁止游戏
                        <input type="checkbox" name="permission" value="measures:game:public" />发布禁止游戏
                    </td>
                </tr>
                <tr class="measures">
                    <td class="ip">
                        <input type="checkbox" id="ip"  name="permission" value="measures:ip:all" onclick="checkModel('ip',this);"/>IP屏蔽库</td>
                    <td class="ip">
                        <input type="checkbox" name="permission" value="measures:ip:view" />查看禁止IP
                        <input type="checkbox" name="permission" value="measures:ip:select" />查询禁止IP
                        <input type="checkbox" name="permission" value="measures:ip:add" />添加禁止IP
                        <input type="checkbox" name="permission" value="measures:ip:edit" />修改禁止IP
                        <input type="checkbox" name="permission" value="measures:ip:delete"/>删除禁止IP
                        <input type="checkbox" name="permission" value="measures:ip:deletemany"/>批量删除禁止IP
                        <input type="checkbox" name="permission" value="measures:ip:public" />发布禁止IP
                    </td>
                </tr>
                <tr class="measures">
                    <td class="special">
                        <input type="checkbox" id="special" name="permission" value="measures:special:all" onclick="checkModel('special',this);"/>特殊人员库</td>
                    <td class="special">
                        <input type="checkbox" name="permission" value="measures:special:view" />查看特殊人员
                        <input type="checkbox" name="permission" value="measures:special:select" />查询特殊人员
                        <input type="checkbox" name="permission" value="measures:special:add" />添加特殊人员
                        <input type="checkbox" name="permission" value="measures:special:edit" />修改特殊人员
                        <input type="checkbox" name="permission" value="measures:special:delete" />删除特殊人员
                        <input type="checkbox" name="permission" value="measures:special:deletemany" />批量删除特殊人员
                        <input type="checkbox" name="permission" value="measures:special:public" />发布特殊人员
                    </td>
                </tr>
                <tr class="measures">
                    <td class="keyword">
                        <input type="checkbox" id="keyword" name="permission" value="measures:keyword:all" onclick="checkModel('keyword',this);"/>关键字库</td>
                    <td class="keyword">
                        <input type="checkbox" name="permission" value="measures:keyword:view" />查看关键字
                        <input type="checkbox" name="permission" value="measures:keyword:select" />查询关键字
                        <input type="checkbox" name="permission" value="measures:keyword:add" />添加关键字
                        <input type="checkbox" name="permission" value="measures:keyword:edit" />修改关键字
                        <input type="checkbox" name="permission" value="measures:keyword:delete" />删除关键字
                        <input type="checkbox" name="permission" value="measures:keyword:deletemany" />批量删除关键字
                        <input type="checkbox" name="permission" value="measures:keyword:public" />发布关键字
                    </td>
                </tr>
                <tr  id="ipc" class="ipc">
                    <td><input type="checkbox" value="sysm:leftMenu:ipc" name="permission" onclick="checkModel('ipc',this);"/>监管中心管理</td>
                    <td class="ipcInfo">
                        <input type="checkbox" id="ipcInfo" name="permission" value="ipc:ipcInfo:all" onclick="checkModel('ipcInfo',this);"/>监管中心</td>
                    <td class="ipcInfo">
                        <input type="checkbox" name="permission" value="ipc:ipcInfo:view"/>查看监管中心信息
                        <input type="checkbox" name="permission" value="ipc:ipcInfo:select" />查询监管中心信息
                        <input type="checkbox" name="permission" value="ipc:ipcInfo:add" />添加监管中心信息
                        <input type="checkbox" name="permission" value="ipc:ipcInfo:edit"/>修改监管中心信息
                        <input type="checkbox" name="permission" value="ipc:ipcInfo:delete" />删除监管中心信息
                        <input type="checkbox" name="permission" value="ipc:ipcInfo:deletemany" />批量删除监管中心信息
                    <!--    <input type="checkbox" name="permission" value="ipc:ipcInfo:report" />导出数字证书-->
                    </td>
                </tr>
                <tr id="place" class="place">
                    <td rowspan="2"><input type="checkbox" name="permission" value="sysm:place:all" onclick="checkModel('place',this);"/>营业场所信息管理</td>
                    <td class="placeInfo">
                        <input type="checkbox" id="placeInfo" name="permission" value="place:placeInfo:all" onclick="checkModel('placeInfo',this);"/>营业场所信息</td>
                    <td class="placeInfo">
                        <input type="checkbox" name="permission" value="place:placeInfo:editStatus" />场所状态修改
                        <input type="checkbox" name="permission" value="place:placeInfo:view" />查看场所信息
                        <input type="checkbox" name="permission" value="place:placeInfo:select" />查询场所信息
                        <input type="checkbox" name="permission" value="place:placeInfo:add" />注册新场所
                        <input type="checkbox" name="permission" value="place:placeInfo:edit" />修改场所信息
                        <input type="checkbox" name="permission" value="place:placeInfo:delete" />删除场所
                        <input type="checkbox" name="permission" value="place:placeInfo:deletemany" />批量删除场所
                    </td>
                </tr>
                <tr class="place">
                    <td class="placesyn">
                        <input type="checkbox" id="placesyn" name="permission" value="place:placesyn:all" onclick="checkModel('placesyn',this);"/>场所信息同步</td>
                    <td class="placesyn">
                        <input type="checkbox"  name="permission" value="place:placesyn:terminalStatus" />场所终端状态同步
                        <input type="checkbox"  name="permission" value="place:placesyn:measures" />监管措施同步
                        <input type="checkbox"  name="permission" value="place:placesyn:dataPacket" />发布场所数据包大小
                        <input type="checkbox" name="permission" value="place:placesyn:placeInfo" />场所信息同步
                    </td>
                </tr>
                <tr id="terminal" class="terminal">
                    <td rowspan="2"><input type="checkbox" name="permission" value="sysm:leftMenu:terminal" onclick="checkModel('terminal',this);"/>终端信息管理与控制</td>
                    <td class="clientInfo">
                        <input type="checkbox" id="clientInfo" name="permission" value="terminal:clientInfo:all" onclick="checkModel('clientInfo',this);"/>客户端信息管理</td>
                    <td class="clientInfo">
                        <input type="checkbox" name="permission" value="terminal:clientInfo:view" />查看客户端信息
                    </td>
                </tr>
                <tr class="terminal">
                    <td class="clientControl">
                        <input type="checkbox" id="clientControl" name="permission" value="terminal:clientControl:all" onclick="checkModel('clientControl',this);"/>客户端控制</td>
                    <td class="clientControl">
                        <input type="checkbox" name="permission" value="terminal:clientControl:turnOff" />关机
                        <input type="checkbox" name="permission" value="terminal:clientControl:restart" />重新启动
                        <input type="checkbox" name="permission" value="terminal:clientControl:screenShots" />截取屏幕
                        <input type="checkbox" name="permission" value="terminal:clientControl:lock" />锁定
                        <input type="checkbox" name="permission" value="terminal:clientControl:unlock" />解锁
                        <input type="checkbox" name="permission" value="terminal:clientControl:delTerminal" />删除终端
                    </td>
                </tr>
                <tr id="operating" class="operating">
                    <td rowspan="8" ><input type="checkbox" name="permission" value="sysm:leftMenu:operating" onclick="checkModel('operating',this);"/>单位资料查询</td>
                    <td class="alarm">
                        <input type="checkbox" id="alarm" name="permission" value="operating:alarm:all" onclick="checkModel('alarm',this);"/>报警信息</td>
                    <td class="alarm">
                        <input type="checkbox" name="permission" value="operating:alarm:view"/>查看报警信息
                        <input type="checkbox" name="permission" value="operating:alarm:select" />查询报警信息
                    </td>
                </tr>
                <tr class="operating">
                    <td class="customer">
                        <input type="checkbox" id="customer" name="permission" value="operating:customer:all" onclick="checkModel('customer',this);"/>顾客信息</td>
                    <td class="customer">
                        <input type="checkbox" name="permission" value="operating:customer:view" />查看顾客信息
                        <input type="checkbox" name="permission" value="operating:customer:select"/>查询顾客信息
                        <input type="checkbox" name="permission" value="operating:customer:online" />在线顾客信息
                    </td>
                </tr>
                <tr class="operating">
                    <td class="customerLog">
                        <input type="checkbox" id="cutomerLog" name="permission" value="operating:customerLog:all" onclick="checkModel('customerLog',this);"/>顾客上网日志</td>
                    <td class="customerLog">
                        <input type="checkbox" name="permission" value="operating:customerLog:view" />查看顾客上网日志
                        <input type="checkbox" name="permission" value="operating:customerLog:select" />查询顾客上网日志
                    </td>
                </tr>
                <tr class="operating">
                    <td class="placePunish">
                        <input type="checkbox" id="placePunish" name="permission" value="operating:placePunish:all" onclick="checkModel('placePunish',this);"/>场所处罚</td>
                    <td class="placePunish">
                        <input type="checkbox" name="permission" value="operating:placePunish:view" />查看场所处罚信息
                        <input type="checkbox" name="permission" value="operating:placePunish:select" />查询场所处罚信息
                        <input type="checkbox" name="permission" value="operating:placePunish:submit" />提交场所处罚
                        <input type="checkbox" name="permission" value="operating:placePunish:delete" />撤销场所处罚
                    </td>
                </tr>
                <tr class="operating">
                    <td class="urlLog">
                        <input type="checkbox" id="urlLog" name="permission" value="operating:urlLog:all" onclick="checkModel('urlLog',this);"/>URL访问记录</td>
                    <td class="urlLog">
                        <input type="checkbox" name="permission" value="operating:urlLog:view"/>查看URL历史记录
                        <input type="checkbox" name="permission" value="operating:urlLog:select" />查询URL历史记录
                    </td>
                </tr>
                <tr class="operating">
                    <td class="progLog">
                        <input type="checkbox" id="progLog" name="permission" value="operating:progLog:all" onclick="checkModel('progLog',this);"/>应用程序使用记录</td>
                    <td class="progLog">
                        <input type="checkbox" name="permission" value="operating:progLog:view" />查看程序使用记录
                        <input type="checkbox" name="permission" value="operating:progLog:select" />查询程序使用记录
                    </td>
                </tr>
                <tr class="operating">
                    <td class="email">
                        <input type="checkbox" id="email" name="permission" value="operating:email:all" onclick="checkModel('email',this);"/>Email使用记录</td>
                    <td class="email">
                        <input type="checkbox" name="permission" value="operating:email:view" />查看email使用记录
                        <input type="checkbox" name="permission" value="operating:email:select" />查询email使用记录
                    </td>
                </tr>
                <tr class="operating">
                    <td class="instant">
                        <input type="checkbox" id="instant" name="permission" value="operating:instant:all" onclick="checkModel('instant',this);"/>即时通信程序使用记录</td>
                    <td class="instant">
                        <input type="checkbox" name="permission" value="operating:instant:view" />查看即时程序使用记录
                        <input type="checkbox" name="permission" value="operating:instant:select" />查询即时程序使用记录
                    </td>
                </tr>
                <tr id="log" class="log">
                    <td rowspan="5">
                        <input type="checkbox"  name="permission" value="sysm:leftMenu:log" onclick="checkModel('log',this);"/>日志管理</td>
                    <td class="adminLog">
                        <input type="checkbox" id="adminLog" name="permission" value="log:adminLog:all"  onclick="checkModel('adminLog',this);"/>管理员日志</td>
                    <td class="adminLog">
                        <input type="checkbox" name="permission" value="log:adminLog:select" />查询管理员日志
                    </td>
                </tr>
                <tr class="log">
                    <td class="monitorLine">
                        <input type="checkbox" id="monitorLine" name="permission" value="log:monitorLine:all"  onclick="checkModel('monitorLine',this);"/>监管系统历史在线</td>
                    <td class="monitorLine">
                        <input type="checkbox" name="permission" value="log:monitorLine:select"/>查询监管系统历史在线信息
                    </td>
                </tr>
                <tr class="log">
                    <td class="outLine">
                        <input type="checkbox" id="outLine" name="permission" value="log:outLine:all"  onclick="checkModel('outLine',this);"/>离线统计</td>
                    <td class="outLine">
                        <input type="checkbox" name="permission" value="log:outLine:select"/>查询离线统计信息
                    </td>
                </tr>
                <tr class="log">
                    <td class="sysLog">
                        <input type="checkbox" id="sysLog" name="permission" value="log:sysLog:all"  onclick="checkModel('sysLog',this);"/>系统日志</td>
                    <td class="sysLog">
                        <input type="checkbox" name="permission" value="log:sysLog:select"/>查询系统日志
                    </td>
                </tr>
                <tr class="log">
                    <td class="compairLog">
                        <input type="checkbox" id="compairLog" name="permission" value="log:compairLog:all" onclick="checkModel('compairLog',this);"/>场所维修日志</td>
                    <td class="compairLog">
                        <input type="checkbox" name="permission" value="log:compairLog:select"/>查询场所维修日志
                    </td>
                </tr>
             <!--   <tr class="log">
                    <td class="history">
                        <input type="checkbox" id="history" onclick="checkModel('history',this);"/>监管系统历史在线信息</td>
                    <td class="history">
                        <input type="checkbox" name="" value=""/>监管系统历史在线信息
                    </td>
                </tr>-->
                <tr id="report" class="report">
                    <td rowspan="2">
                        <input type="checkbox" name="permission" value="sysm:leftMenu:report" onclick="checkModel('report',this);"/>统计分析</td>
                    <td class="dataReport">
                        <input type="checkbox" id="dataReport" name="permission" value="report:dataReport:all" onclick="checkModel('dataReport',this);"/>数据统计报表</td>
                    <td class="dataReport">
                        <input type="checkbox" name="permission" value="report:dataReport:history" />运营状况统计
                        <input type="checkbox" name="permission" value="report:dataReport:period" />运营时段分析
                     <!--   <input type="checkbox" name="" value="" />使用时间时段统计-->
                        <input type="checkbox" name="permission" value="report:dataReport:alert"/>报警统计
						<input type="checkbox" name="permission" value="report:dataReport:placeOnline" />场所在线率统计
                    </td>
                </tr>
                <tr class="report">
                    <td class="rank">
                        <input type="checkbox" id="rank" name="permission" value="report:rank:all" onclick="checkModel('rank',this);"/>排行榜</td>
                    <td class="rank">
                        <input type="checkbox" name="permission" value="report:rank:operate" />营业状况排行榜
                        <input type="checkbox" name="permission" value="report:rank:url" />URL访问排行榜
                        <input type="checkbox" name="permission" value="report:rank:prog" />应用程序使用排行榜                     
                    </td>
                </tr>
                <tr id="sys" class="sys">
                    <td rowspan="5">
                        <input type="checkbox"  name="permission" value="sysm:leftMenu:sys" onclick="checkModel('sys',this);"/>系统设置</td>
                <!--    <td class="parent">
                        <input type="checkbox" id="parent" onclick="checkModel('parent',this);"/>上级监管中心管理</td>
                    <td class="parent">
                        <input type="checkbox" name="" value="" />添加上级监管中心
                        <input type="checkbox" name="" value="" />修改上级监管中心
                        <input type="checkbox" name="" value="" />删除上级监管中心
                        <input type="checkbox" name="" value="" />安装数字证书
                    </td>-->
                </tr>
                <tr class="sys">
                    <td class="alarmSys">
                        <input type="checkbox" id="alarmSys" name="permission" value="sys:alarmSys:all" onclick="checkModel('alarmSys',this);"/>报警等级设置</td>
                    <td class="alarmSys">
                        <input type="checkbox" name="permission" value="sys:alarmSys:view" />显示报警等级设置
                        <input type="checkbox" name="permission" value="sys:alarmSys:edit"/>修改报警等级设置
                    </td>
                </tr>
                <tr class="sys">
                    <td class="smsSys">
                        <input type="checkbox" id="smsSys" name="permission" value="sys:smsSys:all" onclick="checkModel('smsSys',this);"/>SMS报警通知设置</td>
                    <td class="smsSys">
                        <input type="checkbox" name="permission" value="sys:smsSys:view"/>显示SMS报警设置
                        <input type="checkbox" name="permission" value="sys:smsSys:edit"/>修改SMS报警设置
                        <input type="checkbox" name="permission" value="sys:smsSys:add"/>添加SMS报警设置
                        <input type="checkbox" name="permission" value="sys:smsSys:remove"/>删除SMS报警设置
                    </td>
                </tr>
                <tr class="sys">
                    <td class="emailSys">
                        <input type="checkbox" id="emailSys" name="permission" value="sys:emailSys:all" onclick="checkModel('emailSys',this);"/>Email报警通知设置</td>
                    <td class="emailSys">
                        <input type="checkbox" name="permission" value="sys:emailSys:view"/>显示Email报警设置
                        <input type="checkbox" name="permission" value="sys:emailSys:edit"/>修改Email报警设置
                        <input type="checkbox" name="permission" value="sys:emailSys:add"/>添加Email报警设置
                        <input type="checkbox" name="permission" value="sys:emailSys:remove"/>删除Email报警设置
                    </td>
                </tr>
           <!--     <tr class="sys">
                    <td class="otherSys">
                        <input type="checkbox" id="otherSys" onclick="checkModel('otherSys',this);"/>其他报警通知设置</td>
                    <td class="otherSys">
                        <input type="checkbox" name="" value="" />显示其他报警设置
                        <input type="checkbox" name="" value="" />修改其他报警设置
                    </td>
                </tr>-->
                <tr class="sys">
                    <td class="timeoutAlertSys">
                        <input type="checkbox" id="timeoutAlertSys" name="permission" value="sys:timeoutAlertSys:all" onclick="checkModel('timeoutAlertSys',this);"/>场所超时营业报警设置</td>
                    <td class="timeoutAlertSys">
                        <input type="checkbox" name="permission" value="sys:timeoutAlertSys:view" />显示场所超时营业报警设置
                        <input type="checkbox" name="permission" value="sys:timeoutAlertSys:edit" />修改场所超时营业报警设置
                    </td>
                </tr>
                <tr id="data" class="data">
                    <td rowspan="5">
                        <input type="checkbox" name="permission" value="sysm:leftMenu:data" onclick="checkModel('data',this);"/>数据备份与恢复</td>
                    <td class="autoData">
                        <input type="checkbox" id="autoData" name="permission" value="data:autoData:all" onclick="checkModel('autoData',this);"/>数据自动备份</td>
                    <td class="autoData">
                        <input type="checkbox" name="permission" value="data:autoData:view"/>显示自动备份设置
                        <input type="checkbox" name="permission" value="data:autoData:edit"/>修改自动备份设置
                    </td>
                </tr>
                <tr class="data">
                    <td class="manualData">
                        <input type="checkbox" id="manualData" name="permission" value="data:manualData:all" onclick="checkModel('manualData',this);"/>数据手工备份</td>
                    <td class="manualData">
                        <input type="checkbox" name="permission" value="data:manualData:view" />显示手工备份
                        <input type="checkbox" name="permission" value="data:manualData:edit" />修改手工备份
                        <input type="checkbox" name="permission" value="data:manualData:manual" />手工备份
                    </td>
                </tr>
                <tr class="data">
                    <td class="recoveryData">
                        <input type="checkbox" id="recoveryData" name="permission" value="data:recoveryData:all" onclick="checkModel('recoveryData',this);"/>数据恢复</td>
                    <td class="recoveryData">
                        <input type="checkbox" name="permission" value="data:recoveryData:recovery" />数据恢复
                    </td>
                </tr>
                <tr class="data">
                    <td class="backupData">
                        <input type="checkbox" id="backupData" name="permission" value="data:backupData:all" onclick="checkModel('backupData',this);"/>备份日志</td>
                    <td class="backupData">
                        <input type="checkbox" name="permission" value="data:backupData:view" />备份日志查看
                        <input type="checkbox" name="permission" value="data:backupData:select" />备份日志查询
                    </td>
                </tr>
                <tr class="data">
                    <td class="restoreData">
                        <input type="checkbox" id="restoreData" name="permission" value="data:restoreData:all" onclick="checkModel('restoreData',this);"/>恢复日志</td>
                    <td class="restoreData">
                        <input type="checkbox" name="permission" value="data:restoreData:view" />恢复日志查看
                        <input type="checkbox" name="permission" value="data:restoreData:select" />恢复日志查询
                    </td>
                </tr>
                <tr id="msgman" class="msgman">
                    <td rowspan="3" class="msgman">
                        <input type="checkbox" name="permission" value="sysm:leftMenu:msgman" onclick="checkModel('msgman',this);"/>消息通知管理</td>
                    <tr class="msgman">
                        <td class="public">
                            <input type="checkbox" id="public" name="permission" value="msgman:public:all" onclick="checkModel('public',this);"/>发布消息管理</td>
                        <td class="public">
                            <input type="checkbox" name="permission" value="msgman:public:add"/>撰写发布消息
                            <input type="checkbox" name="permission" value="msgman:public:edit"/>修改发布消息
                            <input type="checkbox" name="permission" value="msgman:public:delete"/>删除发布消息
                            <input type="checkbox" name="permission" value="msgman:public:deletemany"/>批量删除发布消息
                            <input type="checkbox" name="permission" value="msgman:public:release"/>发布消息
                            <input type="checkbox" name="permission" value="msgman:public:public" />查看发布消息
                            <input type="checkbox" name="permission" value="msgman:public:viewresponse" />查看响应消息
                        </td>
                    </tr>
                    <tr class="msgman">
                        <td class="receive">
                            <input type="checkbox" id="receive" name="permission" value="msgman:receive:all" onclick="checkModel('receive',this);"/>收到消息管理</td>
                        <td class="receive">
                            <input type="checkbox" name="permission" value="msgman:receive:public" />查看收到消息
                            <input type="checkbox" name="permission" value="msgman:receive:viewresponse" />查看收到响应消息
                        </td>
                    </tr>
                </tr>
            </table>
            <table width="80%">
                <tr>
                    <td align="center" style="padding-top: 4px;"><input type="submit" class="btn btn-primary" value="确定"></td>
                    <td align="center" style="padding-top: 4px;"><input type="button" onclick="returnList()" class="btn btn-primary" value="返回"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script>
    function submit(){
        $('#f1').submit();
    }

    function returnList(){
        window.location="/account/role#_1"
    }
</script>
</body>
</html>
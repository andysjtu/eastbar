<%@ page language="java" pageEncoding="UTF-8" %>
<table cellpadding="0" cellspacing="0" height="100%" width="191">
    <tr>
        <td height="66">
            <div class="left_top"></div>
        </td>
    </tr>
    <tr>
        <td valign="top" class="left_bg">
            <div id="levelmenu">
                <div class="_1 unit current">
                    <shiro:hasPermission name="sysm:leftMenu:account"><h5>用户与角色管理</h5></shiro:hasPermission>
                    <ul>
                        <shiro:hasPermission name="account:user:all"><li><a href="/account/user?.${version}#_1">用户列表</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="account:role:all"><li><a href="/account/role?.${version}#_1">角色列表</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="account:user:add"><li><a href="/account/intoUserAdd?.${version}#_1">新建用户</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="account:role:add"><li><a href="/account/intoRoleAdd?.${version}#_1">新建角色</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="account:user:editPassword"><li><a href="/account/intoPasswordEdit?.${version}#_1">更改密码</a></li></shiro:hasPermission>
                    </ul>
                </div>
                <div class="_0 unit">
                    <shiro:hasPermission name="sysm:leftMenu:ipc"><h5>监管中心</h5></shiro:hasPermission>
                    <ul>
                        <shiro:hasPermission name="ipc:ipcInfo:all"><li><a href="/ipc/list?.${version}#_0">监管中心列表</a></li></shiro:hasPermission>
                     <!--   <li><a href="/ipc/ipcSearch#_0">查询监管中心</a></li>-->
                        <shiro:hasPermission name="place:placeInfo:all"><li><a href="/ipc/placeList/-1?.${version}#_0">营业场所列表</a></li></shiro:hasPermission>
                     <!--   <li><a href="/ipc/placeSearch#_0">营业场所查询</a></li>-->
                        <shiro:hasPermission name="place:placeInfo:add"><li><a href="/ipc/intoPlaceAdd?.${version}#_0">新建营业场所</a></li></shiro:hasPermission>
                        <li><a href="/ipc/onlineUser/-1?.${version}#_0">历史客户查询</a></li>
                    </ul>
                </div>
                <div class="_2 unit">
                    <shiro:hasPermission name="sysm:leftMenu:msgman"><h5>消息通知管理</h5></shiro:hasPermission>
                    <ul>
                        <shiro:hasPermission name="msgman:public:all"><li><a href="/msgman/public?.${version}#_2">撰写发布消息</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="msgman:receive:all"><li><a href="/msgman/receive?.${version}#_2">收到的消息管理</a></li></shiro:hasPermission>
                    </ul>
                </div>
                <div class="_3 unit">
                    <shiro:hasPermission name="sysm:leftMenu:sys"><h5>系统设置</h5></shiro:hasPermission>
                    <ul>
                        <shiro:hasPermission name="sys:alarmSys:all"><li><a href="/sys/alert?.${version}#_3">报警等级设置</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="sys:smsSys:all"><li><a href="/sys/sMs?.${version}#_3">短消息报警通知设置</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="sys:emailSys:all"><li><a href="/sys/email?.${version}#_3">邮件报警通知设置</a></li></shiro:hasPermission>
                    </ul>
                </div>
                <div class="_4 unit">
                    <shiro:hasPermission name="sysm:leftMenu:measures"><h5>监管措施</h5></shiro:hasPermission>
                    <ul>
                        <shiro:hasPermission name="measures:shopHour:all"><li><a href="/measures/place?.${version}#_4">场所营业时间</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="measures:url:all"><li><a href="/measures/URL?.${version}#_4">URL屏敝库</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="measures:game:all"><li><a href="/measures/game?.${version}#_4">游戏屏敝库</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="measures:ip:all"><li><a href="/measures/IP?.${version}#_4">IP屏蔽库</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="measures:special:all"><li><a href="/measures/special?.${version}#_4">特殊人员库</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="measures:keyword:all"><li><a href="/measures/keyword?.${version}#_4">关键字库</a></li></shiro:hasPermission>
                    </ul>
                </div>
                <div class="_5 unit">
                    <shiro:hasPermission name="sysm:leftMenu:operating"><h5>单位资料查询</h5></shiro:hasPermission>
                    <ul>
                        <shiro:hasPermission name="sysm:leftMenu:operating"><li><a href="/unit/alertList/-1?.${version}#_5">报警信息查询</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="operating:placePunish:select"><li><a href="/unit/sitePunish?.${version}#_5">场所处罚查询</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="operating:customerLog:all"><li><a href="/unit/customerList?.${version}#_5">客户上网记录查询</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="operating:customer:select"><li><a href="/unit/logList?.${version}#_5">顾客信息查询</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="operating:urlLog:all"><li><a href="/unit/urlList?.${version}#_5">URL历史记录</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="operating:progLog:all"><li><a href="/unit/programList?.${version}#_5">程序使用记录</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="operating:email:all"><li><a href="/unit/mailList?.${version}#_5">Email使用记录</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="operating:instant:all"><li><a href="/unit/instantList?.${version}#_5">及时通信程序记录</a></li></shiro:hasPermission>
                    </ul>
                </div>
                <div class="_6 unit">
                    <shiro:hasPermission name="sysm:leftMenu:report"><h5>统计分析</h5></shiro:hasPermission>
                    <ul>
                        <shiro:hasPermission name="report:dataReport:history"><li><a href="/report/history?.${version}#_6">运营状况统计</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="report:dataReport:period"><li><a href="/report/period?.${version}#_6">运营时段分析</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="report:dataReport:alert"><li><a href="/report/alert?.${version}#_6">报警统计</a></li></shiro:hasPermission>
                        <li><a href="/report/punishSearch?.${version}#_6">处罚统计</a></li>
                        <shiro:hasPermission name="report:rank:operate"><li><a href="/report/operateRank?.${version}#_6">运营状况排行</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="report:rank:url"><li><a href="/report/urlRank?.${version}#_6">URL访问排行</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="report:rank:prog"><li><a href="/report/programRank?.${version}#_6">程序使用排行</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="report:dataReport:placeOnline"><li><a href="/report/statePlace?.${version}#_6">场所在线率统计</a></li></shiro:hasPermission>
                    </ul>
                </div>
                <div class="_7 unit">
                    <shiro:hasPermission name="sysm:leftMenu:log"><h5>日志管理</h5></shiro:hasPermission>
                    <ul>
                        <li><a href="/log/sysLog?.${version}#_7">系统运行维护日志</a></li>
                        <shiro:hasPermission name="log:adminLog:all"><li><a href="/log/adminLog?.${version}#_7">管理员操作行为日志</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="log:monitorLine:select"><li><a href="/log/submitLogMonitor?.${version}#_7">监管系统历史在线信息</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="log:compairLog:all"><li><a href="/log/place?.${version}#_7">场所维修记录查询</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="log:outLine:all"><li><a href="/log/submitLogOutLine?.${version}#_7">离线统计</a></li></shiro:hasPermission>
                        <li><a href="/log/depart?.${version}#_7">部级日志</a></li>
                    </ul>
                </div>
            </div>
        </td>
    </tr>
</table>
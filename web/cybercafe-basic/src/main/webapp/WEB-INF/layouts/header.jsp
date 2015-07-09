<%@ page language="java" pageEncoding="UTF-8" %>

<div id="header">
    <table width="100%" height="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td class="slogan" align="right">
                <a href="javascript:void(0)" id="close" onclick="logout()"></a>
            </td>
        </tr>
        <tr>
            <td height="34" class="tit_bg">
                <div class="title_bg">
                    <ul>
                        <shiro:hasPermission name="sysm:leftMenu:account"><li><a href="/account/user?.${version}#_1" class="tit_btn" id="_1"><span class="tit_bg01"></span>用户与角色管理</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="sysm:leftMenu:ipc"><li><a href="/ipc/list?.${version}#_0" class="tit_btn02" id="_0"><span class="tit_bg01"></span>监管中心</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="sysm:leftMenu:msgman"><li><a href="/msgman/public?.${version}#_2" class="tit_btn02" id="_2"><span class="tit_bg01"></span>消息通知管理</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="sysm:leftMenu:sys"><li><a href="/sys/alert?.${version}#_3" class="tit_btn02" id="_3"><span class="tit_bg01"></span>系统设置</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="sysm:leftMenu:measures"><li><a href="/measures/place?.${version}#_4" class="tit_btn02" id="_4"><span class="tit_bg01"></span>监管措施</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="sysm:leftMenu:operating"><li><a href="/unit/alertList/-1?.${version}#_5" class="tit_btn02" id="_5"><span class="tit_bg01"></span>单位资料查询</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="sysm:leftMenu:report"><li><a href="/report/history?.${version}#_6" class="tit_btn02" id="_6"><span class="tit_bg01"></span>统计分析</a></li></shiro:hasPermission>
                        <shiro:hasPermission name="sysm:leftMenu:log"><li><a href="/log/sysLog?.${version}#_7" class="tit_btn02" id="_7"><span class="tit_bg01"></span>日志管理</a></li></shiro:hasPermission>
                        <li><a href="/help/h?.${version}#_8" class="tit_btn02" id="_8"><span class="tit_bg01"></span>帮助</a></li>
                    </ul>
                    <div class="clear"></div>
                </div>
            </td>
        </tr>
        <tr>
            <td height="2.5px" class="tit_bg"></td>
        </tr>
        </table>
</div>
<script>
    function logout(){
        if(window.confirm('你确定退出吗？')){
            window.location="${ctx}/auth/logout";
        }
    }
</script>
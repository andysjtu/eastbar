<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>上海市互联网上网服务营业场所计算机经营管理系统</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <link href="${ctx}/static/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/jquery-easyui/1.4/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/jquery-easyui/1.4/themes/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/default.css" rel="stylesheet" type="text/css"/>

    <script src="${ctx}/static/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery-easyui/1.4/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery-easyui/1.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootstrap/2.3.2/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>
<div id="login">
<form action="${ctx}/auth/login" method="post" id="loginForm" name="loginForm">
<table id="outline" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td valign="middle" class="lg_mid"><img src="${ctx}/static/images/login_img_01.png" id="adimg" width="649" height="477"></td>
        <td align="center" valign="middle">
            <div class="log_bg">
                <table class="lg_tit">
                    <tr>
                        <td width="100" align="right">用&nbsp;户&nbsp;名</td>
                        <td class="lg_txet">
                            <input id="username" name="username" type="text" width="150" autofocus="" value="${username}" class="input-medium required"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="100" align="right">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</td>
                        <td class="lg_txet">
                            <input id="password" name="password" type="password" width="150" class="input-medium required"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table width="100%">
                                <tr>
                                    <td width="120">
                                        <a href="javascript:submit()" class="lg_btn">登&nbsp;&nbsp;录</a>
                                    </td>
                                    <td style="padding-right:25px;">
                                        <a href="#" class="lg_btn">帮&nbsp;&nbsp;助</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
</table>
</form>
    <div class="ind_footer">
        <table width="100%">
            <tr>
                <td height="80" colspan="3" align="center" class="css1">Copyright©2014 上海市文化广播影视管理局<br>
                    Version Number：1.0.1</td>
            </tr>
        </table>
    </div>

</div>

<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">警告</h3>
    </div>
    <div class="modal-body">
        <p>您当前已经使用 <strong><shiro:principal/></strong> 账户登录至系统中</p>
        <p class="text-error">确定注销，切换账户重新登录？</p>
    </div>
    <div class="modal-footer">
        <a class="btn" href="/auth/logout">注销</a>
        <a class="btn btn-primary" href="/account/user">继续使用</a>
    </div>
</div>
<script type="text/javascript">
    //按下回车登录系统
    document.onkeydown = function(event){
        event = event?event:window.event;
        if (event.keyCode == 13) {
            submit();
        }
    };
    function submit() {
        $("#loginForm").submit();
    }

    function showTips(tipmsg) {
        if (tipmsg) {
            switch (tipmsg){
                case "ERROR:L0001":msg = "用户名或密码错误！";break
                case "ERROR:L0002":msg = "您当前已经使用 <shiro:principal/> 账户。";$("#myModal").modal('toggle');break
                default :tipmsg;
            }
            jQuery.messager.show({
                title : '操作提示',
                msg : msg,
                showSpeed : 1000,
                timeout : 3000,
                showType : 'show'
            });
        }
    }

    $(function() {
        showTips('${loadmsg}');
    });

</script>
</body>
</html>

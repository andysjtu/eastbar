<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>在线用户查询</title>
    <link href="${ctx}/static/styles/ipc.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="mc_tit">
    <form id="f1" action="onlineUserSearchResult#_0" method="post">
    <table width="100%" class="tab_line mc_line" cellpadding="0" cellspacing="1">
        <tr>
            <td class="mc_tab_top02" align="center">
                在线用户信息查询
            </td>
        </tr>
        <tr>
            <td style="border:0px solid;">
                <table cellpadding="0" cellspacing="0" width="100%" class="mc_tab_line">
                    <tr class="tab_cont01">
                        <td colspan="4">在线用户信息查询</td>
                    </tr>
                    <tr class="mc_cont02">
                        <td width="180">监管中心编码</td>
                        <td width="350" align="left">
                            <select name="">
                                <option>上海网吧监管中心</option>
                            </select>
                        </td>
                        <td width="180">场所编码</td>
                        <td align="left"><input name="" type="text" class="mc_inp"></td>

                    </tr>
                    <tr class="mc_cont02">
                        <td width="180">顾客姓名</td>
                        <td width="350" align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                        <td width="180">顾客帐号</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                    </tr>
                    <tr class="mc_cont02">
                        <td width="180">证件类型</td>
                        <td width="350" align="left">
                            <select name="" style="width:120px;">
                                <option>--全部--</option>
                            </select>
                        </td>
                        <td width="180">证件号码</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                    </tr>
                    <tr class="mc_cont02">
                        <td width="180">使用机器IP</td>
                        <td align="left" colspan="3">
                            <input name="" type="text" class="mc_inp" style="width:250px;">
                        </td>
                </table>
            </td>
        </tr>
        <tr>
            <td height="30" bgcolor="#f5f5f5">
                <table width="100%" class="mc_btnbox">
                    <tr>
                        <td align="right"><a href="#" onclick="save()" class="mc_btn01">确&nbsp;定</a></td>
                        <td align="center" width="250"><a href="#" class="mc_btn01">清&nbsp;空</a></td>
                        <td align="left"><a href="${ctx}/ipc/list#_0" class="mc_btn01">返&nbsp;回</a></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    </form>
</div>
<script>
    function save(){
        $('#f1').submit();
    }
</script>
</body>
</html>
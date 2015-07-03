<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>监管中心</title>
    <link href="${ctx}/static/styles/account.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div style="margin:40px 400px;" class="mc_tit um_box">
        <form id="f1" action="submitUserAdd" method="post">
            <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                <tr>
                    <td class="mc_tab_top02" align="center">
                        管理员查询
                    </td>
                </tr>
            </table>
            <table width="100%" class="mc_tab_line" border="1">
                <tr>
                    <td align="left" class="tab_top_title02" colspan="4">管理员查询</td>
                </tr>
                <tr class="tab_cont03 sc_lineh">
                    <td>账号：</td>
                    <td style="text-align: center">
                        <input type="text" value="" style="height: 15px;margin-bottom:2px ">*
                    </td>
                    <td>所在域：</td>
                    <td style="text-align: center">
                        <select>
                            <option>--ALL--</option>
                        </select>
                    </td>
                </tr>
                <tr class="tab_cont03 sc_lineh">
                    <td>角色：</td>
                    <td style="text-align: center">
                        <select>
                            <option>省级管理员</option>
                        </select>
                    </td>
                    <td>所在中心：</td>
                    <td style="text-align: center">
                        <select>
                            <option>上海网吧监管中心</option>
                        </select>
                    </td>
                </tr>
                <tr class="tab_cont03 sc_lineh">
                    <td>密码：</td>
                    <td style="text-align: center">
                        <input type="text" value="" style="height: 15px;margin-bottom:2px ">*
                    </td>
                    <td>密码确认：</td>
                    <td style="text-align: center">
                        <input type="text" value="" style="height: 15px;margin-bottom:2px ">*
                    </td>
                </tr>
                <tr class="tab_cont03 sc_lineh">
                    <td>姓名：</td>
                    <td style="text-align: center">
                        <input type="text" value="" style="height: 15px;margin-bottom:2px ">*
                    </td>
                    <td>身份证号码：</td>
                    <td style="text-align: center">
                        <input type="text" value="" style="height: 15px;margin-bottom:2px ">
                    </td>
                </tr>
                <tr class="tab_cont03 sc_lineh">
                    <td>办公电话：</td>
                    <td style="text-align: center">
                        <input type="text" value="" style="height: 15px;margin-bottom:2px ">*
                    </td>
                    <td>家庭电话：</td>
                    <td style="text-align: center">
                        <input type="text" value="" style="height: 15px;margin-bottom:2px ">
                    </td>
                </tr>
                <tr class="tab_cont03 sc_lineh">
                    <td>手机号码：</td>
                    <td style="text-align: center">
                        <input type="text" value="" style="height: 15px;margin-bottom:2px ">*
                    </td>
                    <td>Email：</td>
                    <td style="text-align: center">
                        <input type="text" value="" style="height: 15px;margin-bottom:2px ">
                    </td>
                </tr>
            </table>
            <table width="100%" class="mc_btnbox">
                <tr>
                    <td align="center" style="padding-top: 4px;"><a href="#" onclick="submit()" class="mc_btn01">保&nbsp;存</a></td>
                    <td align="center" style="padding-top: 4px;"><a href="${ctx}/user/list#_2" class="mc_btn01">返&nbsp;回</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script>
    function submit(){
        $('f1').submit();
    }
</script>
</body>
</html>
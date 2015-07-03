<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>电子邮件通知设置</title>
    <link href="${ctx}/static/styles/sys.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="mc_tit um_box" style="margin-top: 10px;">
    <table width="60%" class="tab_line">
        <tr>
            <td align="left" class="tab_top_title02">电子邮件通知设置</td>
        </tr>
    </table>
    <table width="60%"  class="tab_line">
        <tr class="tab_line">
             <td class="tab_line">SMTP服务器</td>
             <td colspan="2">
                 地址:${alarmNotify.smtpAddress}，端口: ${alarmNotify.smtpPort}
             </td>
             <td class="tab_line">
                  <a href="${ctx}/sys/intoEmailServerEdit#_3" class="link_col01">修改</a>
             </td>
              </tr>
                    <tr>
                        <td class="tab_line">发送方</td>
                        <td colspan="2">
                            ${alarmNotify.sender}
                        </td>
                        <td class="tab_line">
                            <shiro:hasPermission name="sys:emailSys:edit"><a href="${ctx}/sys/intoEmailSenderEdit#_3" class="link_col01">修改</a></shiro:hasPermission>
                        </td>
                    </tr>
                    <tr class="tab_line">
                        <td class="tab_line">接收者</td>
                        <td colspan="2" class="tab_line">
                            <table width="100%">
                             <tr><td class="tab_line">
                                邮箱地址
                            </td>
                                <td class="tab_line">
                                    操作
                                </td>
                            </tr>
                            <c:forEach items="${alarmNotifiers}" var="alarmNotify">
                                <tr>
                                    <td class="tab_line">
                                        ${alarmNotify.receiver}
                                    </td>
                                    <td class="tab_line">
                                        <shiro:hasPermission name="sys:emailSys:remove"><a href="#" onclick="deleteReceiver(${alarmNotify.id})" class="link_col01">删除</a></shiro:hasPermission>
                                    </td>
                                </tr>
                            </c:forEach>
                            </table>
                        </td>
                        <td class="tab_line">
                            <shiro:hasPermission name="sys:emailSys:add"><a href="${ctx}/sys/intoEmailReceiverAdd#_3" class="link_col01">添加</a></shiro:hasPermission>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<script>
    function deleteReceiver(id){
        if(window.confirm("确定要删除这条记录吗？")){
             window.location="${ctx}/sys/emailRemove/"+id+"#_3";
        }
    }
</script>
</body>
</html>
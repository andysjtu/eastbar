<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>SMS短消息通知设置</title>
    <link href="${ctx}/static/styles/sys.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div style="margin-top:10px">
    <table width="60%">
        <tr>
            <td align="left" class="tab_top_title02">SMS短消息通知设置</td>
        </tr>
        <tr>
            <td>
                <table  width="100%" class="tab_line">
                    <tr class="tab_line">
                        <td style="line-height: 3" class="tab_line">付费方</td>
                        <td colspan="2" class="tab_line">
                            <span>${alarmNotify.sender}</span>
                        </td>
                        <td>
                          <shiro:hasPermission name="sys:smsSys:edit"><a href="${ctx}/sys/intoSmsEdit/${alarmNotify.id}#_3">修改</a></shiro:hasPermission>
                        </td>
                    </tr>
                    <tr class="tab_line">
                        <td class="tab_line">接收者</td>
                        <td colspan="2">
                          <table width="100%">
                            <tr class="tab_line"><td>
                                手机号
                            </td>
                                <td class="tab_line">
                                    操作
                                </td>
                            </tr>
                                <c:forEach items="${alarmNotifiers}" var="alarmNotify">
                                    <tr>
                                        <td class="tab_line"><span>${alarmNotify.receiver}</span></td>
                                        <td class="tab_line">
                                            <shiro:hasPermission name="sys:smsSys:edit"><a href="${ctx}/sys/intoSmsReceiverEdit/${alarmNotify.id}#_3" class="link_col01">修改</a></shiro:hasPermission>
                                            <shiro:hasPermission name="sys:smsSys:remove"><a href="#" class="link_col01" onclick="del(${alarmNotify.id})">删除</a></shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                        <td class="tab_line">
                            <shiro:hasPermission name="sys:smsSys:add"><a href="${ctx}/sys/intoSmsAdd#_3">添加</a></shiro:hasPermission>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>

<script>
    function del(id){
       if(window.confirm("确定要删除这条记录吗?")){
           window.location="${ctx}/sys/removeSms/"+id+"#_3";
       }
    }
</script>
</body>
</html>
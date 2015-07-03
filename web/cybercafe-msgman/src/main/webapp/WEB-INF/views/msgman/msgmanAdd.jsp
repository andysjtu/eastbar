<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>新增消息</title>
    <link href="${ctx}/static/styles/auth.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/styles/msgman.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/msgman/submitAdd#_2" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>消息信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label" >消息接受者</label>

                    <%--<div class="controls">--%>
                        <%--<input type="text" name="noteRangeType" placeholder="消息接收者">--%>
                    <%--</div>--%>
                    <%--<br>--%>
                    <div class="controls">
                        <select id="cc" class="easyui-combotree" multiple="true" name="monitorCode" data-options="url:'${ctx}/msgman/tree',method:'get'"></select>
                    </div>

                </div>
                <div class="control-group">
                    <label class="control-label" >消息标题</label>

                    <div class="controls">
                        <input type="text" name="noticeTitle" placeholder="消息标题">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >消息类型</label>

                    <div class="controls">
                        <select name="noticeType">
                            <option value="0">普通</option>
                            <option value="1">需要回复</option>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >通知时间</label>

                    <div class="controls">
                        <input name="noticeTime"  type="text" class="easyui-datetimebox" required style="width:200px" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >消息内容</label>

                    <div class="controls">
                        <textarea name="noticeContent" cols="60" rows="5"></textarea>
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div style="margin: 0 auto 20px 150px;">
                    <input type="button" class="btn btn-primary" onclick="save()" value="   确定   "/>
                    <input type="button" class="btn btn-primary" onclick="returnList()" value="   返回   "/>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function save(){
        $('#f1').submit();
    }
    function returnList(){
        window.location="/msgman/public#_2"
    }
</script>
</body>
</html>
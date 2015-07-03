<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>场所处罚信息统计</title>
    <link href="${ctx}/static/styles/report.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="/report/punishSearchJson#_6" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>场所处罚信息统计</small></blockquote>
                <div class="control-group">
                    <label class="control-label">统计范围：</label>
                    <div class="controls">
                        <select name="siteCode">
                            <c:forEach items="${siteList}" var="site">
                                <option value="${site.siteCode}">${site.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">统计时间：</label>
                    <div class="controls">
                        <input type="text" name="btime" class="easyui-datetimebox" required style="width:200px" />&nbsp;
                        到<input type="text" name="etime" class="easyui-datetimebox" required style="width:200px" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">统计类型：</label>
                    <div class="controls">
                        <select name="punishReasonOrType">
                            <option value="处罚原因">处罚原因</option>
                            <option value="处罚类型">处罚类型</option>
                        </select>
                    </div>
                </div>
                <div style="margin: 0 auto 20px 150px;">
                    <input type="button" class="btn btn-primary" onclick="save()" value="   确定   "/>
                    <input type="reset" class="btn btn-primary"  value="   清空   "/>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function save(){
        $('#f1').submit();
    }
</script>
</body>
</html>
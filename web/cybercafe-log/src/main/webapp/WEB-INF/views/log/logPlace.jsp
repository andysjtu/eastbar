<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>场所维修信息查询</title>
    <link href="${ctx}/static/styles/log.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/log/submitLogPlace#_7" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>场所维修信息查询</small></blockquote>
                <div class="control-group">
                    <label class="control-label">监管中心：</label>
                    <div class="controls">
                        <select name="monitorCode" class="mc_sel_mid">
                            <c:forEach items="${monitors}" var="monitor">
                                <option value="310101">${monitor.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <br>
                    <label class="control-label">场所编码：</label>
                    <div class="controls">
                        <input name="siteCode" type="text" class="mc_inp"/>
                    </div>
                    <br>
                    <label class="control-label">场所名称：</label>
                    <div class="controls">
                        <input name="siteName" type="text" class="mc_inp"/>
                    </div>
                    <br>
                    <label class="control-label">服务类型：</label>
                    <div class="controls">
                        <select name="type">
                            <option value="0">全部</option>
                            <option value="1">技术支持</option>
                            <option value="2">远程协作</option>
                            <option value="3">上门服务</option>
                            <option value="4">送修服务</option>
                        </select>
                    </div>
                    <br>
                    <label class="control-label">维修状态：</label>
                    <div class="controls">
                        <select name="status" >
                            <option value="0">全部</option>
                            <option value="1">提交</option>
                            <option value="2">处理中</option>
                            <option value="3">完毕</option>
                            <option value="4">硬件故障</option>
                            <option value="5">硬件故障已通知</option>
                            <option value="6">已安装</option>
                            <option value="7">已通知取货</option>
                            <option value="8">监控中</option>
                        </select>
                    </div>
                    <br>
                    <label class="control-label">处理结果：</label>
                    <div class="controls">
                        <select name="result" >
                            <option value="0">全部</option>
                            <option value="1">未上门</option>
                            <option value="2">未处理</option>
                            <option value="3">成功（处理后在线）</option>
                            <option value="4">不成功（处理后不在线）</option>
                        </select>
                    </div>
                    <br>
                    <label class="control-label">报修起始时间：</label>
                    <div class="controls">
                        <input name="btime" type="text" class="easyui-datetimebox" />
                    </div>
                    <br>
                    <label class="control-label">报修结束时间：</label>
                    <div class="controls">
                        <input name="etime"  type="text" class="easyui-datetimebox" />
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div style="margin: 0 auto 20px 150px;">
                    <input type="button" class="btn btn-primary" onclick="save()" value="   确定   "/>
                    <input type="button" class="btn btn-primary" onclick="returnList()" value="   清空   "/>
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
        window.location="/log/place#_7"
    }
</script>
</body>
</html>
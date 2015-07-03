<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改禁止游戏</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/measures/submitGameEdit" method="post"  class="form-horizontal">
            <input type="hidden" value="${bannedProg.id}" name="id">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>禁止游戏信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">程序名称</label>

                    <div class="controls">
                        <input type="text" name="progName" id="progName" value="${bannedProg.progName}">*必填项
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">程序类型</label>

                    <div class="controls">
                        <select name="progType" id="progType">
                            <option value="1">进程名</option>
                            <option value="2">特征码</option>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >执行文件名称</label>

                    <div class="controls">
                        <input type="text" name="progFileName" value="${bannedProg.progFileName}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">进程名称</label>

                    <div class="controls">
                        <input type="text" name="progressName" value="${bannedProg.progressName}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">制造商</label>

                    <div class="controls">
                        <input type="text" name="progMF" value="${bannedProg.progMF}">
                    </div>
                </div>
               <div class="control-group">
                    <label class="control-label">游戏特征码</label>

                    <div class="controls">
                        <input type="text" name="featureCode" value="${bannedProg.featureCode}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >告警类型</label>
                    <c:if test="${bannedProg.alarmType==1}">
                    <div class="controls">
                        <select name="alarmType">
                            <option value="1" selected="selected">游戏、程序报警</option>
                        </select>
                    </div>
                    </c:if>
                    <c:if test="${bannedProg.alarmType!=1}">
                        <div class="controls">
                            <select name="alarmType">
                                <option value="1">告警类型有误</option>
                            </select>
                        </div>
                    </c:if>
                </div>
                <div class="control-group">
                   <label class="control-label">报警等级</label>
                    <div class="controls">
                        <select name="alarmRank" id="alarmRank">
                            <option value="1">严重报警</option>
                            <option value="2">中等程度报警</option>
                            <option value="3">一般报警</option>
                        </select>
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
        if(validateGame()){
            $('#f1').submit();
        }
    }

    function returnList(){
        window.location="/measures/game#_4";
    }

    $(function(){
        $("#alarmRank").val(${bannedProg.alarmRank});
        $("#progType").val(${bannedProg.progType});
    })
</script>
<script src="${ctx}/static/js/measures.js"></script>
</body>
</html>
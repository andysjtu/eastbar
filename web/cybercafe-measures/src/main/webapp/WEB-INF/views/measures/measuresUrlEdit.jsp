<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改禁止URL</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/measures/submitUrlEdit" method="post" class="form-horizontal">
            <input type="hidden" value="${bannedUrlBO.id}" name="id"/>
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>禁止URL信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">URL类型:</label>
                    <div class="controls">
                            <select name="urlType" id="urlType">
                                <option value="1" >域名</option>
                                <option value="3" >URL地址</option>
                            </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">URL:</label>

                    <div class="controls">
                        <input type="text"  name="urlValue" id="urlValue" value="${bannedUrlBO.urlValue}"/>
                        *必填（填入合法的域名）
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >告警类型:</label>
                    <div class="controls">
                        <c:if test="${bannedUrlBO.alarmType==3}">
                            <select name="alarmType">
                                <option value="3">URL访问报警</option>
                            </select>
                        </c:if>
                        <c:if test="${bannedUrlBO.alarmType!=3}">
                            <select name="alarmType">
                                <option value="">报警类型有误</option>
                                <option value="3">URL访问报警</option>
                            </select>
                        </c:if>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >报警等级:</label>

                    <div class="controls">
                            <select name="alarmRank" id="alarmRank">
                                <option value="1">严重报警</option>
                                <option value="2">中等程度报警</option>
                                <option value="3">一般报警</option>
                            </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >是否拦截:</label>
                    <c:if test="${bannedUrlBO.isBlock==1}">
                        <div class="controls">
                            <input type="radio" name="isBlock" checked="checked" value="1">是
                            <input type="radio" name="isBlock" value="0">否
                        </div>
                    </c:if>
                    <c:if test="${bannedUrlBO.isBlock==0}">
                        <div class="controls">
                            <input type="radio" name="isBlock" checked="checked" value="1">是
                            <input type="radio" name="isBlock" value="0" checked="checked">否
                        </div>
                    </c:if>
                    <c:if test="${bannedUrlBO.isBlock!=0 and bannedUrlBO.isBlock!=1 }">
                        <div class="controls">
                            <input type="radio" name="isBlock"  value="0">是
                            <input type="radio" name="isBlock" value="1">否
                        </div>
                    </c:if>


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
        //if(validateUrl()){
            $('#f1').submit();
       // }

    }
    function returnList(){
        window.location="/measures/URL#_4";
    }

    $(function() {
        $("#monitorCode").val(${bannedUrlBO.monitorCode});
        $("#alarmRank").val(${bannedUrlBO.alarmRank});
        $("#urlType").val(${bannedUrlBO.urlType});

    });

</script>
<script src="${ctx}/static/js/measures.js"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改关键字信息</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/measures/submitKeyWordEdit" method="post" class="form-horizontal">
            <input type="hidden" value="${keyWordBO.id}" name="id"/>
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>关键字信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">关键字</label>

                    <div class="controls">
                        <input type="text"  name="keyword" id="keyword" value="${keyWordBO.keyword}">*必填项
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">告警类型</label>
                    <div class="controls">

                        <c:if test="${keyWordBO.alarmType==5}">
                            <select name="alarmType">
                                <option value="5">关键字报警</option>
                            </select>
                        </c:if>
                        <c:if test="${keyWordBO.alarmType!=5}">
                            <select name="alarmType">
                                <option value="" selected="selected">报警类型有误</option>
                                <option value="5">关键字报警</option>
                            </select>
                        </c:if>

                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >报警等级</label>

                    <div class="controls">
                            <select name="alarmRank" id="alarmRank">
                                <option value="1">严重报警</option>
                                <option value="2">中等程度报警</option>
                                <option value="3">一般报警</option>
                            </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >是否拦截</label>
                    <c:if test="${keyWordBO.isBlock==1}">
                        <div class="controls">
                            <input type="radio" name="isBlock" checked="checked" value="1">是
                            <input type="radio" name="isBlock" value="0">否
                        </div>
                    </c:if>
                    <c:if test="${keyWordBO.isBlock==0}">
                        <div class="controls">
                            <input type="radio" name="isBlock"  value="1">是
                            <input type="radio" name="isBlock" value="0" checked="checked">否
                        </div>
                    </c:if>
                    <c:if test="${keyWordBO.isBlock!=0 and keyWordBO.isBlock!=1 }">
                        <div class="controls">
                            <input type="radio" name="isBlock"  value="1">是
                            <input type="radio" name="isBlock" value="0">否
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
    $(function(){
        $("#alarmRank").val(${keyWordBO.alarmRank});
    });
    function save(){
        if(validateKeyword()){
            $('#f1').submit();
        }
    }
    function returnList(){
        window.location="/measures/keyword#_4";
    }

</script>
<script src="${ctx}/static/js/measures.js"></script>
</body>
</html>
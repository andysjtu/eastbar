<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改特殊人员</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
   <div>
        <form id="f1" action="${ctx}/measures/submitSpecialEdit" method="post" class="form-horizontal">
            <input type="hidden" value="${specialCustomer.id}" name="id"/>
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>特殊人员信息</small></blockquote>
            <div class="control-group">
                    <label class="control-label">姓名</label>

                    <div class="controls">
                        <input type="text"  name="name" id="name" value="${specialCustomer.name}">*必填项
                    </div>
                </div>
              <div class="control-group">
                    <label class="control-label">证件类型</label>
                    <div class="controls">
                        <select name="certType" id="certType">
                              <option value="1">身份证</option>
                              <option value="2">军官证</option>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">身份证号</label>

                    <div class="controls">
                        <input type="text"  name="certId" id="certId" value="${specialCustomer.certId}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">国籍</label>

                    <div class="controls">
                        <input type="text"  name="nationality" value="${specialCustomer.nationality}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >告警类型:</label>
                    <div class="controls">
                        <c:if test="${specialCustomer.alarmType==4}">
                            <select name="alarmType">
                                <option value="4">特殊人员报警</option>
                            </select>
                        </c:if>
                        <c:if test="${specialCustomer.alarmType!=4}">
                            <select name="alarmType">
                                <option value="">报警类型有误</option>
                                <option value="4">特殊人员报警</option>
                            </select>
                        </c:if>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >报警等级</label>
                    <div class="controls">
                        <select name="alarmRank" id="alarmRank">
                            <option value="1" >严重报警</option>
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
    $(function(){
       $("#certType").val(${specialCustomer.certType});
       $("#alarmRank").val(${specialCustomer.alarmRank});
    });
    function save(){
       if(validateSpecial()){
           $('#f1').submit();
       }
    }
    function returnList(){
        window.location="/measures/special#_4";
    }
</script>
<script src="${ctx}/static/js/measures.js"></script>
</body>
</html>
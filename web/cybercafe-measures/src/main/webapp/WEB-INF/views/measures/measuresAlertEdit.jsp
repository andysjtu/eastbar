<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>违规报警等级设置</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div >
      <form id="f1" action="${ctx}/measures/submitAlertEdit#_4" method="post" class="form-horizontal">
          <div class="mlr8" style="border: 1px solid #d4d4d4;">
              <hr/><blockquote> <small>其他违规报警等级设置</small></blockquote>
              <div class="control-group">
                  <label class="control-label" >违规报警类别</label>

                  <div class="controls">
                      <span>在规定的营业时间外营业</span>
                  </div>
              </div>
              <div class="control-group">
                  <label class="control-label" >报警等级</label>

                  <div class="controls">
                      <select>
                          <option>一般报警</option>
                          <option>中等程度报警</option>
                          <option>严重报警</option>
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
        $('#f1').submit();
    }

    function returnList(){
        window.location="/measures/alert#_4";
    }
</script>
</body>
</html>
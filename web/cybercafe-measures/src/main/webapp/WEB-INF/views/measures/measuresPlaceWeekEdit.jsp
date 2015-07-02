<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改场所营业时间措施</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/measures/submitPlaceWeekEdit#_4" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <input type="hidden" value="${shopHourBO.id}" name="id"/>
                <hr/><blockquote> <small>营业场所时间信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label" >日期类型：</label>
                    <div class="controls">
                        <select name="dayOfWeek" id="dayOfWeek">
                            <option value="0">星期日</option>
                            <option value="1">星期一</option>
                            <option value="2">星期二</option>
                            <option value="3">星期三</option>
                            <option value="4">星期四</option>
                            <option value="5">星期五</option>
                            <option value="6">星期六</option>
                        </select>
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div class="control-group">
                    <label class="control-label" >营业时间：</label>
                    <div class="controls">
                        <select class="mc_sel_short" name="startTime1"><option value="0">0</option></select>点&lt;-
                        营业
                        <span>-&gt;</span>
                        <select class="mc_sel_short" name="endTime1"><option value="7">7</option><option value="8">8</option></select>点
                        <br><br>
                        <select class="mc_sel_short" name="startTime2"><option value="0">7</option><option value="8">8</option></select>点&lt;-
                        打烊
                        <span>-&gt;</span>
                        <select class="mc_sel_short" name="endTime2"><option value="23">23</option></select>点
                        <br><br>
                        <select class="mc_sel_short" name="startTime3"><option value="23">23</option></select>点&lt;-
                        营业
                        <span>-&gt;</span>
                        <select class="mc_sel_short" name="endTime3"><option value="24">24</option></select>点

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
        window.location="/measures/place#_4";
    }
    $(function(){
        $("#dayOfWeek").val(${shopHourBO.dayOfWeek});

    });
</script>
</body>
</html>



<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>运营状况时段统计分析</title>
    <link href="${ctx}/static/styles/report.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>运营状况时段统计分析输入</small></blockquote>
                <div class="control-group">
                    <label class="control-label">选择时间：</label>
                    <div class="controls">
                        <input type="text" class="easyui-datetimebox" name="startDate" required style="width:200px">
                        --至--&nbsp;&nbsp;<input type="text" class="easyui-datetimebox" name="endDate" required style="width:200px">
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div class="control-group">
                    <label class="control-label">统计对象：</label>
                    <div class="controls">
                        <input type="radio" name="shopHourType" value="2">所有
                        &nbsp;&nbsp;&nbsp;<span>对所有的场所进行顾客使用时间时段统计分析</span>
                    </div><br/>
                    <div class="controls">
                        <input type="radio" name="shopHourType" value="2">按省/市级
                        &nbsp;&nbsp;&nbsp;<select><option>111111</option></select>
                    </div><br/>
                    <div class="controls">
                        <input type="radio" name="shopHourType" value="2">按区/县级
                        &nbsp;&nbsp;&nbsp;<select><option>111111</option></select>
                    </div><br/>
                    <div class="controls">
                        <input type="radio" name="shopHourType" value="2">按场所
                        &nbsp;&nbsp;&nbsp;<input type="text"/>
                    </div><br/>
                    <div class="controls">
                        <input type="radio" name="shopHourType" value="2">按编码
                        &nbsp;&nbsp;&nbsp;<input type="text"/>
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div class="control-group">
                    <label class="control-label">时段类型：</label>
                    <div class="controls">
                        <input name="" type="radio" value=""> 分时段日使用时间统计
                        <input name="" type="radio" value=""> 分时段周使用时间统计
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div class="control-group">
                    <label class="control-label">分析指标：</label>
                    <div class="controls">
                        <input name="" type="radio" value=""> 按上座人次分析
                        <input name="" type="radio" value=""> 按使用时间分析
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div style="margin: 0 auto 20px 150px;">
                    <input type="button" class="btn btn-primary search" onclick="save()" value="   确定   "/>
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
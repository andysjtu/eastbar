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
        <form id="f1" action="/report/periodSearch#_6" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>运营状况时段统计分析输入</small></blockquote>
                <div class="control-group">
                    <label class="control-label">选择时间：</label>
                    <div class="controls">
                        <input type="text" class="easyui-datetimebox" name="btime" id="btime" required style="width:200px">
                        --至--&nbsp;&nbsp;<input type="text" class="easyui-datetimebox" name="etime" id="etime" required style="width:200px">
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div class="control-group">
                    <label class="control-label">统计对象：</label>
                    <div class="controls">
                        <input type="radio" name="objectType" value="d" checked="checked">所有
                        &nbsp;&nbsp;&nbsp;<span>对当前系统所有的场所进行顾客使用时间时段统计分析</span>
                    </div><br/>
                    <div class="controls">
                        <input type="radio" name="objectType" value="s">按省/市级
                        &nbsp;&nbsp;&nbsp;<select name="fmonitorCode" id="monitor" onclick="showMonitors()">
                        <c:forEach items="${monitorList}" var="monitor">
                        <option value="${monitor.monitorCode}">${monitor.name}</option>
                        </c:forEach>
                        </select>
                    </div><br/>
                    <div class="controls">
                        <input type="radio" name="objectType" value="q">按区/县级
                        &nbsp;&nbsp;&nbsp;<select id="domain" name="smonitorCode">
                        <%--<c:forEach items="${areaList}" var="area">--%>
                            <%--<option value="${area.monitorCode}">${area.name}</option>--%>
                        <%--</c:forEach>--%>
                        </select>
                    </div><br/>
                    <div class="controls">
                        <input type="radio" name="objectType" value="c">按场所
                        &nbsp;&nbsp;&nbsp;<input type="text" id="siteCode" name="siteCode"/>
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div class="control-group">
                    <label class="control-label">时段类型：</label>
                    <div class="controls">
                        <input name="timeType" type="radio" value="day" checked="checked"> 分时段日使用时间统计
                        <input name="timeType" type="radio" value="week"> 分时段周使用时间统计
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div class="control-group">
                    <label class="control-label">分析指标：</label>
                    <div class="controls">
                        <input name="gogalType" type="radio" value="customer_count" checked="checked"> 按上座人次分析
                        <input name="gogalType" type="radio" value="online_time"> 按使用时间分析
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
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
        var btime = $("#btime").datebox('getValue');
        var etime=$("#etime").datebox('getValue');
        if(btime=='' || etime==''){
            alert("请选择起止日期！");
        }else{
            $('#f1').submit();
        }

    }

    $(function(){
        showMonitors();
    });

    function showMonitors(){
        var monitor=$('#monitor');
        $("#domain").html("");
        $.ajax({
            type:'POST',
            url: '${ctx}/ipc/getMonitors/'+monitor.val(),
            data: '',
            dataType: "json",
            success: function(data){
                var obj = eval(data);
                for(var i=0;i<obj.length;i++){
                    document.getElementById("domain").options[i] = new Option(obj[i].name,obj[i].monitorCode);
                }
            }
        });
    }

</script>
</body>
</html>
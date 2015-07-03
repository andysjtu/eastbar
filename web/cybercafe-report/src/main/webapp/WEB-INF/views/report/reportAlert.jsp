<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>报警信息统计</title>
    <link href="${ctx}/static/styles/report.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/report/alertSearch#_6" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>报警信息查询</small></blockquote>
                <div class="control-group">
                    <label class="control-label">选择时间：</label>
                    <div class="controls">
                        <input type="radio" name="type" value="y">按年统计
                        &nbsp;&nbsp;&nbsp;
                        <select name="byear" class="mc_sel_mid" id="byear">
                            <c:forEach items="${years}" var="year">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                        --至--&nbsp;&nbsp;<select name="eyear" class="mc_sel_mid" id="eyear">
                        <c:forEach items="${years}" var="year">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>年
                    </div>
                    <br/>
                    <div class="controls">
                        <input type="radio" name="type" value="m" checked="checked">按月统计
                        &nbsp;&nbsp;&nbsp;
                        <select name="bmyear" class="mc_sel_mid" id="bmyear">
                            <c:forEach items="${years}" var="year">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>年
                        <select name="bmonth" class="mc_sel_short" id="bmonth">
                            <c:forEach items="${months}" var="month">
                                <option value="${month.value}">${month.showValue}</option>
                            </c:forEach>
                        </select>月
                        --至--&nbsp;&nbsp;<select name="emyear" class="mc_sel_mid" id="emyear">
                        <c:forEach items="${years}" var="year">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>年
                        <select name="emonth" class="mc_sel_short" id="emonth">
                            <c:forEach items="${months}" var="month">
                                <option value="${month.value}">${month.showValue}</option>
                            </c:forEach>
                        </select>月
                    </div>
                    <br/>
                    <div class="controls">
                        <input type="radio" name="type" value="d">按日统计
                        &nbsp;&nbsp;&nbsp;
                        <input type="text" class="easyui-datetimebox" id="btime" name="btime"  required style="width:200px">
                        --至--&nbsp;&nbsp;<input type="text" class="easyui-datetimebox" id="etime" name="etime"  required style="width:200px">
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div class="control-group">
                    <label class="control-label">统计对象：</label>
                    <div class="controls">
                        <input type="radio" name="objectType" checked="checked" value="d">当前系统
                        &nbsp;&nbsp;&nbsp;<span>以当前系统作为整体进行报警信息统计</span>
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
                        &nbsp;&nbsp;&nbsp;<select id="domain" name="smonitorCode"></select>
                    </div><br/>
                    <div class="controls">
                        <input type="radio" name="objectType" value="c">按场所
                        &nbsp;&nbsp;&nbsp;<input type="text" id="siteCode" name="siteCode"/>
                    </div><br/>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div style="margin: 0 auto 20px 150px;">
                    <input type="button" class="btn btn-primary" onclick="save()" value="   确定   "/>
                    <input type="reset" class="btn btn-primary" value="   清空   "/>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function save(){
        var type=$("input:checked");
        var valradio =$(":radio[name='objectType']:checked").val();
        var btime = $("#btime").datebox('getValue');
        var etime=$("#etime").datebox('getValue');
        var flag=true;
        if($(type).val()=='d'){
            if(btime=='' || etime==''){
                alert("请选择起止日期！");
                flag=false;
            }
        }
        if(valradio=='c'){
            if($("#siteCode").val()==''){
                alert("请填写具体场所编码");
                flag=false;
            }
        }
        if(flag){
            $('#f1').submit();
        }
    }

    $(function(){
        showMonitors();
        var date=new Date();
        var year=date.getFullYear();
        var month=date.getMonth();
        $("#byear").val(year);
        $("#eyear").val(year);
        $("#bmyear").val(year);
        $("#emyear").val(year);
        if(month<10){
            $("#bmonth").val('0'+month);
            $("#emonth").val('0'+month);
        }else{
            $("#bmonth").val(month);
            $("#emonth").val(month);
        }

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
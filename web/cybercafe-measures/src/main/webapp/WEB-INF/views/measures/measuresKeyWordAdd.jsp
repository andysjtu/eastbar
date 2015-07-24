<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>新增关键字</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
   <div>
        <form id="f1" action="${ctx}/measures/submitKeyWordAdd" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <div id="add">
                 <hr/><blockquote> <small>统一下发对象</small></blockquote>
                    <div class="control-group">
                        <label class="control-label">监管中心</label>

                        <div class="controls">
                            <select id="cc" class="easyui-combotree" multiple="true" name="monitorCodes" data-options="url:'${ctx}/measures/tree',method:'get'"></select>&nbsp;&nbsp;
                            <small><a href="javascript:void(0)" onclick="selectSite()" style="color: darkgreen" >[下发到场所]</a></small>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">场所名称</label>

                        <div class="controls">
                            <select id="sitelist" name="siteCodes">
                                <option value="-1">-请选择-</option>
                            </select>
                        </div>
                </div>
                <hr/><blockquote> <small>关键字信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">关键字</label>

                    <div class="controls">
                        <input type="text"  name="keywords" id="keyword">
                        *必填项
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >报警类型</label>

                    <div class="controls">
                        <select name="alarmTypes">
                            <option value="5">关键字报警</option>
                        </select>

                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >报警等级</label>

                    <div class="controls">
                        <select name="alarmRanks">
                            <option value="1">严重报警</option>
                            <option value="2">中等程度报警</option>
                            <option value="3">一般报警</option>
                        </select>

                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >是否拦截</label>

                    <div class="controls">
                        <select name="isBlocks">
                            <option value="0">是</option>
                            <option value="1">否</option>
                        </select>
                    </div>
                </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div style="margin: 0 auto 20px 150px;">
                    <input type="button" class="btn btn-primary" onclick="add()" value="   继续添加   "/>
                    <input type="button" class="btn btn-primary" onclick="save()" value="   确定   "/>
                    <input type="button" class="btn btn-primary" onclick="returnList()" value="   返回   "/>
                </div>
            </div>
     </form>
    </div>
</div>
<script>
    function save(){
        var i=0;
        var types=$(":input[name='keywords']");
        $.each($(":input[name='keywords']"),function(k,v){
            if(!validateKeywords(v)){
                i=i+1;
            }
        });
        if(i==0){
            var tree=$('#cc').combo('getValues');
            if(tree.length>0){
                $('#f1').submit();
            }else{
                alert("统一下发对象为必选项！");
            }
        }

    }

    function add(){
        var html="<div><hr/><blockquote><small>关键字信息<a href='javascript:void(0)' onclick='closeAdd(this)' name='close' style='margin-left: 40px;color: #0000ff'>[关闭]</a></small></blockquote><div class='control-group'><label class='control-label'>关键字</label> <div class='controls'><input type='text'  name='keywords' id='keyword'>*必填项</div></div><div class='control-group'><label class='control-label' >报警类型</label><div class='controls'><select name='alarmTypes'><option value='5'>关键字报警</option></select></div></div><div class='control-group'><label class='control-label' >报警等级</label><div class='controls'><select name='alarmRanks'><option value='1'>严重报警</option><option value='2'>中等程度报警</option><option value='3'>一般报警</option></select></div></div><div class='control-group'><label class='control-label' >是否拦截</label>" +
                "<div class='controls'><select name='isBlocks'><option value='1'>是</option><option value='0'>否</option></select></div></div></div>";
        $("#add").append($(html));
    }

    function closeAdd(a){
        $(a).parent().parent().parent().remove();
    }

    function returnList(){
        window.location="/measures/keyword#_4";
    }

    function selectSite(){
        $("#sitelist").empty();
        document.getElementById("sitelist").options[0]=new Option("-请选择-","-1");
        var tree =$('#cc').combo('getValues');
        if(tree.length!=1){
            alert("选择下发场所时，必须只能选择一个监管中心");
        }
        if(tree.length==1){
            $.ajax({
                type:'POST',
                url: '${ctx}/measures/sitelist/'+tree,
                data: '',
                dataType: "json",
                success: function(data){
                    var obj = eval(data);
                    for(var i=0;i<obj.length;i++){
                        document.getElementById("sitelist").options[i] = new Option(obj[i].name,obj[i].siteCode);
                    }
                }
            });
        }

    }
</script>
<script src="${ctx}/static/js/measures.js"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>新增禁止游戏</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
       <form id="f1" action="${ctx}/measures/submitGameAdd" method="post"  class="form-horizontal">
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
               <hr/><blockquote> <small>禁止游戏信息</small></blockquote>
               <div class="control-group">
                   <label class="control-label">程序名称</label>

                   <div class="controls">
                       <input type="text" name="progNames" id="progName" placeholder="程序名称">
                       *必填
                   </div>
               </div>
               <div class="control-group">
                      <label class="control-label">程序类型</label>

                      <div class="controls">
                          <select name="progTypes">
                              <option value="1">进程名</option>
                              <option value="2">特征码</option>
                          </select>
                      </div>
               </div>
               <div class="control-group">
                   <label class="control-label" >执行文件名称</label>

                   <div class="controls">
                       <input type="text" name="progFileNames" placeholder="执行文件名称">
                   </div>
               </div>
               <div class="control-group">
                   <label class="control-label">进程名称</label>

                   <div class="controls">
                       <input type="text" name="progressNames" placeholder="进程名称">
                   </div>
               </div>
               <div class="control-group">
                   <label class="control-label">制造商</label>

                   <div class="controls">
                       <input type="text" name="progMFs" placeholder="制造商">
                   </div>
               </div>
               <div class="control-group">
                   <label class="control-label">游戏特征码</label>

                   <div class="controls">
                       <input type="text" name="featureCodes" placeholder="游戏特征码">
                   </div>
               </div>
               <div class="control-group">
                   <label class="control-label" >告警类型</label>

                   <div class="controls">
                       <select name="alarmTypes">
                           <option value="1">游戏、程序报警</option>
                           <%--<option value="2">运行报警</option>--%>
                           <%--<option value="3">URL访问报警</option>--%>
                           <%--<option value="4">特殊人员报警</option>--%>
                       </select>
                   </div>
               </div>
               <div class="control-group">
                   <label class="control-label">报警等级</label>

                   <div class="controls">
                       <select name="alarmRanks">
                           <option value="1">严重报警</option>
                           <option value="2">中等程度报警</option>
                           <option value="3">一般报警</option>
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
        $.each($(":input[name='progNames']"),function(k,v){
            if(!validateGames(v.value)){
                i=i+1;
            }
        });

        var j=0;
        $.each($(":input[name='featureCodes']"),function(k,v){
            if(!validateFeatureCodes(v.value)){
                j=j+1;
            }
        });

        if(i==0 && j==0){
          var tree=$('#cc').combo('getValues');
          if(tree.length>0){
              $('#f1').submit();
          }else{
              alert("统一下发对象为必选项！");
          }
        }
    }

    function add(){
        var html="<div><hr/><blockquote> <small>禁止游戏信息<a href='javascript:void(0)' name='close' onclick='closeAdd(this)' style='margin-left: 40px;color: #0000ff'>[关闭]</a></small></blockquote><div class='control-group'><label class='control-label'>程序名称</label><div class='controls'><input type='text' name='progNames' id='progName' placeholder='程序名称'>*必填</div></div><div class='control-group'><label class='control-label'>程序类型</label><div class='controls'><select name='progTypes'><option value='1'>进程名</option><option value='2'>特征码</option></select></div></div><div class='control-group'><label class='control-label'>执行文件名称</label><div class='controls'><input type='text' name='progFileNames' placeholder='执行文件名称'></div></div><div class='control-group'><label class='control-label'>进程名称</label><div class='controls'><input type='text' name='progressNames' placeholder='进程名称'></div></div><div class='control-group'><label class='control-label'>制造商</label><div class='controls'><input type='text' name='progMFs' placeholder='制造商'></div></div><div class='control-group'><label class='control-label'>游戏特征码</label><div class='controls'><input type='text' name='featureCodes' placeholder='游戏特征码'></div></div><div class='control-group'><label class='control-label' >告警类型</label><div class='controls'><select name='alarmTypes'><option value='1'>游戏、程序报警</option></select></div></div><div class='control-group'><label class='control-label'>报警等级</label><div class='controls'><select name='alarmRanks'><option value='1'>严重报警</option><option value='2'>中等程度报警</option><option value='3'>一般报警</option></select></div></div></div>";
        $(html).appendTo($("#add"));
    }

    function closeAdd(a){
        $(a).parent().parent().parent().remove();
    }

    function returnList(){
        window.location="/measures/game#_4";
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
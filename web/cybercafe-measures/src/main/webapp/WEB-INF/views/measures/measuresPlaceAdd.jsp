<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加场所营业时间措施</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
        <form id="f1" action="${ctx}/measures/submitPlaceAdd" method="post" class="form-horizontal">
            <div class="mlr8" style="border: 1px solid #d4d4d4;">
                <hr/><blockquote> <small>营业场所时间信息</small></blockquote>
                <div class="control-group">
                    <label class="control-label">选择类型：</label>

                    <div class="controls">
                        <input type="radio" id="weekraido" name="shopHourBOList[0].shopHourType" value="1" checked="checked">按星期
                          &nbsp;&nbsp;&nbsp;
                        <select name="shopHourBOList[0].dayOfWeek" id="weekselect">
                            <option value="0">星期日</option>
                            <option value="1">星期一</option>
                            <option value="2">星期二</option>
                            <option value="3">星期三</option>
                            <option value="4">星期四</option>
                            <option value="5">星期五</option>
                            <option value="6">星期六</option>
                        </select>
                    </div>
                    <br>
                    <div class="controls">
                        <input type="radio" name="shopHourBOList[0].shopHourType" value="2">按日期
                            &nbsp;&nbsp;&nbsp;
                            从<input type="text" class="easyui-datetimebox" name="shopHourBOList[0].startDate" required style="width:200px"><br><br>&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            到<input type="text" class="easyui-datetimebox" name="shopHourBOList[0].endDate" required style="width:200px">
                    </div>
                </div>
                <hr style="margin: 20px 10px;"/>
                <div class="control-group">
                    <label class="control-label" >营业时间：</label>

                    <div class="controls">
                        <select class="mc_sel_short" name="shopHourBOList[0].startTime1">
                            <option value="0">0</option><option value="1">1</option>
                            <option value="2">2</option><option value="3">3</option>
                            <option value="4">4</option><option value="5">5</option><option value="6">6</option></select>点&lt;-
                        打烊
                        <span>-&gt;</span>
                        <select class="mc_sel_short" name="shopHourBOList[0].endTime1">
                            <option value="7">7</option><option value="8">8</option>
                            <option value="9">9</option><option value="10">10</option>
                            <option value="11">11</option><option value="12">12</option></select>点
                        <br><br>
                        <select class="mc_sel_short" name="shopHourBOList[0].startTime2">
                            <option value="7">7</option><option value="8">8</option>
                            <option value="9">9</option><option value="10">10</option>
                            <option value="11">11</option><option value="12">12</option>
                            <option value="13">13</option><option value="14">14</option>
                            <option value="15">15</option><option value="16">16</option></select>点&lt;-
                        营业
                        <span>-&gt;</span>
                        <select class="mc_sel_short" name="shopHourBOList[0].endTime2">
                            <option value="17">17</option><option value="18">18</option>
                            <option value="19">19</option><option value="20">20</option>
                            <option value="21">21</option>
                            <option value="22">22</option><option value="23" selected="selected">23</option></select>点
                        <br><br>
                        <select class="mc_sel_short" name="shopHourBOList[0].startTime3">
                            <option value="18">18</option><option value="19">19</option>
                            <option value="20">20</option><option value="21">21</option>
                            <option value="22">22</option><option value="23" selected="selected">23</option>
                        </select>点&lt;-
                        打烊
                        <span>-&gt;</span>
                        <select class="mc_sel_short" name="shopHourBOList[0].endTime3">
                            <option value="23">23</option><option value="24" selected="selected">24</option>
                            <option value="1">1</option>
                            <option value="2">2</option><option value="3">3</option>
                            <option value="4">4</option><option value="5">5</option><option value="6">6</option>
                        </select>点
                    </div>
                 </div>
                    <div id="place1" style="display: none">
                    <hr/><blockquote> <small>营业场所时间信息<a href="javascript:void(0)" style="margin-left: 40px; color: #0000ff" onclick="closePlace(1)">[关闭]</a></small></blockquote>
                        <div class="control-group">
                            <label class="control-label">选择类型：</label>

                            <div class="controls">
                                <input type="radio" id="weekraido1" name="shopHourBOList[1].shopHourType" value="1" checked="checked">按星期
                                &nbsp;&nbsp;&nbsp;
                                <select name="shopHourBOList[1].dayOfWeek" id="weekselect1">
                                    <option value="0">星期日</option>
                                    <option value="1">星期一</option>
                                    <option value="2">星期二</option>
                                    <option value="3">星期三</option>
                                    <option value="4">星期四</option>
                                    <option value="5">星期五</option>
                                    <option value="6">星期六</option>
                                </select>
                            </div>
                            <br>
                            <div class="controls">
                                <input type="radio" name="shopHourBOList[1].shopHourType" value="2">按日期
                                &nbsp;&nbsp;&nbsp;
                                从<input type="text" class="easyui-datetimebox" name="shopHourBOList[1].startDate" required style="width:200px"><br><br>&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                到<input type="text" class="easyui-datetimebox" name="shopHourBOList[1].endDate" required style="width:200px">
                            </div>
                        </div>
                        <hr style="margin: 20px 10px;"/>
                        <div class="control-group">
                            <label class="control-label" >营业时间：</label>

                            <div class="controls">
                                <select class="mc_sel_short" name="shopHourBOList[1].startTime1">
                                    <option value="0">0</option><option value="1">1</option>
                                    <option value="2">2</option><option value="3">3</option>
                                    <option value="4">4</option><option value="5">5</option><option value="6">6</option></select>点&lt;-
                                打烊
                                <span>-&gt;</span>
                                <select class="mc_sel_short" name="shopHourBOList[1].endTime1">
                                    <option value="7">7</option><option value="8">8</option>
                                    <option value="9">9</option><option value="10">10</option>
                                    <option value="11">11</option><option value="12">12</option></select>点
                                <br><br>
                                <select class="mc_sel_short" name="shopHourBOList[1].startTime2">
                                    <option value="7">7</option><option value="8">8</option>
                                    <option value="9">9</option><option value="10">10</option>
                                    <option value="11">11</option><option value="12">12</option>
                                    <option value="13">13</option><option value="14">14</option>
                                    <option value="15">15</option><option value="16">16</option></select>点&lt;-
                                营业
                                <span>-&gt;</span>
                                <select class="mc_sel_short" name="shopHourBOList[1].endTime2">
                                    <option value="17">17</option><option value="18">18</option>
                                    <option value="19">19</option><option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option><option value="23" selected="selected">23</option></select>点
                                <br><br>
                                <select class="mc_sel_short" name="shopHourBOList[1].startTime3">
                                    <option value="18">18</option><option value="19">19</option>
                                    <option value="20">20</option><option value="21">21</option>
                                    <option value="22">22</option><option value="23">23</option>
                                </select>点&lt;-
                                打烊
                                <span>-&gt;</span>
                                <select class="mc_sel_short" name="shopHourBOList[1].endTime3">
                                    <option value="23">23</option><option value="24">24</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option><option value="3">3</option>
                                    <option value="4">4</option><option value="5">5</option><option value="6">6</option>
                                </select>点
                          </div>
                    </div>
                    </div>
                    <div id="place2" style="display: none">
                        <hr/><blockquote> <small>营业场所时间信息<a href="javascript:void(0)" style="margin-left: 40px;color: #0000ff" onclick="closePlace(2)">[关闭]</a></small></blockquote>
                        <div class="control-group">
                            <label class="control-label">选择类型：</label>

                            <div class="controls">
                                <input type="radio" id="weekraido2" name="shopHourBOList[2].shopHourType" value="1" checked="checked">按星期
                                &nbsp;&nbsp;&nbsp;
                                <select name="shopHourBOList[2].dayOfWeek" id="weekselect2">
                                    <option value="0">星期日</option>
                                    <option value="1">星期一</option>
                                    <option value="2">星期二</option>
                                    <option value="3">星期三</option>
                                    <option value="4">星期四</option>
                                    <option value="5">星期五</option>
                                    <option value="6">星期六</option>
                                </select>
                            </div>
                            <br>
                            <div class="controls">
                                <input type="radio" name="shopHourBOList[2].shopHourType" value="2">按日期
                                &nbsp;&nbsp;&nbsp;
                                从<input type="text" class="easyui-datetimebox" name="shopHourBOList[2].startDate" required style="width:200px"><br><br>&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                到<input type="text" class="easyui-datetimebox" name="shopHourBOList[2].endDate" required style="width:200px">
                            </div>
                        </div>
                        <hr style="margin: 20px 10px;"/>
                        <div class="control-group">
                            <label class="control-label" >营业时间：</label>

                            <div class="controls">
                                <select class="mc_sel_short" name="shopHourBOList[2].startTime1">
                                    <option value="0">0</option><option value="1">1</option>
                                    <option value="2">2</option><option value="3">3</option>
                                    <option value="4">4</option><option value="5">5</option><option value="6">6</option></select>点&lt;-
                                打烊
                                <span>-&gt;</span>
                                <select class="mc_sel_short" name="shopHourBOList[2].endTime1">
                                    <option value="7">7</option><option value="8">8</option>
                                    <option value="9">9</option><option value="10">10</option>
                                    <option value="11">11</option><option value="12">12</option></select>点
                                <br><br>
                                <select class="mc_sel_short" name="shopHourBOList[2].startTime2">
                                    <option value="7">7</option><option value="8">8</option>
                                    <option value="9">9</option><option value="10">10</option>
                                    <option value="11">11</option><option value="12">12</option>
                                    <option value="13">13</option><option value="14">14</option>
                                    <option value="15">15</option><option value="16">16</option></select>点&lt;-
                                营业
                                <span>-&gt;</span>
                                <select class="mc_sel_short" name="shopHourBOList[2].endTime2">
                                    <option value="17">17</option><option value="18">18</option>
                                    <option value="19">19</option><option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option><option value="23" selected="selected">23</option></select>点
                                <br><br>
                                <select class="mc_sel_short" name="shopHourBOList[2].startTime3">
                                    <option value="18">18</option><option value="19">19</option>
                                    <option value="20">20</option><option value="21">21</option>
                                    <option value="22">22</option><option value="23">23</option>
                                </select>点&lt;-
                                打烊
                                <span>-&gt;</span>
                                <select class="mc_sel_short" name="shopHourBOList[2].endTime3">
                                    <option value="23">23</option><option value="24">24</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option><option value="3">3</option>
                                    <option value="4">4</option><option value="5">5</option><option value="6">6</option>
                                </select>点
                            </div>
                        </div>
                    </div>
                <hr style="margin: 20px 10px;"/>
                <div style="margin: 0 auto 20px 150px;">
                    <input type="button" class="btn btn-primary" onclick="intoAdd()" value="   继续添加   "/>
                    <input type="button" class="btn btn-primary" onclick="publish()" value="   保存并发布   "/>
                    <input type="button" class="btn btn-primary" onclick="save()" value="   保存但不发布   "/>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    var num=0;
    function save(){
        var place1=$("#place1").css("display")=="block";
        var place2=$("#place2").css("display")=="block";
        //根据place1和place2的显示与否，来给后台传参，帮助确定list的个数
        if(confirm("确定要保存吗？")){
            if(place1 && !place2){
                $('#f1').attr("action","${ctx}/measures/submitPlaceAdd/0/1");
            }else if(!place1 && place2){
                $('#f1').attr("action","${ctx}/measures/submitPlaceAdd/0/2");
            }else if(place1 && place2){
                $('#f1').attr("action","${ctx}/measures/submitPlaceAdd/0/3");
            }else{
                $('#f1').attr("action","${ctx}/measures/submitPlaceAdd/0/0");
            }
            $("#f1").submit();
        }
    }

    function publish(){
        var place1=$("#place1").css("display")=="block";
        var place2=$("#place2").css("display")=="block";
        //根据place1和place2的显示与否，来给后台传参，帮助确定list的个数
        if(confirm("确定要保存并发布吗？")){
            if(place1 && !place2){
                $('#f1').attr("action","${ctx}/measures/submitPlaceAdd/1/1");
            }else if(!place1 && place2){
                $('#f1').attr("action","${ctx}/measures/submitPlaceAdd/1/2");
            }else if(place1 && place2){
                $('#f1').attr("action","${ctx}/measures/submitPlaceAdd/1/3");
            }else{
                $('#f1').attr("action","${ctx}/measures/submitPlaceAdd/1/0");
            }
            $("#f1").submit();
        }
    }

    function returnList(){
        window.location="/measures/place#_4";
    }

    function intoAdd(){
        num=num+1;
       if($("#place1").css("display")=="none"){
            $("#place1").css("display","block");
       }else{
           $("#place2").css("display","block");
       }

       if(num>2){
           alert("营业时间规范最多可同时添加三条记录");
           num=num-1;
       }

    }

    function closePlace(placeNum){
        if(placeNum==1){
            $("#place1").css("display","none");
            num=num-1;
        }
        if(placeNum==2){
            $("#place2").css("display","none");
            num=num-1;
        }
    }

</script>
</body>
</html>



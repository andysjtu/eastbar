<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>历史运营状况</title>
    <link href="${ctx}/static/styles/report.css"  type="text/css" rel="stylesheet"/>
    <script src="${ctx}/static/js/esl.js"></script>
</head>
<body>
<script type="text/javascript">

    function testdata(){
        $.ajax({
            type:'post',
            url: '${ctx}/report/gridjson',
            data: '',
            dataType: 'json',
            success: function(data){
                var staTime="";
                var customerCount="";
                var terminaAveage="";
                var terminaTime="";
                $.each(data,function(index,val){
                    if(index === "staTime"){
                        staTime =val;
                    }else if (index === "customerCount") {
                        customerCount = val;
                    } else if (index === "terminaAveage") {
                        terminaAveage =  val;
                    } else if (index === "terminaTime") {
                        terminaTime = val;
                    }

                });
                require.config({
                    paths:{
                        echarts:'${ctx}/static/js/echarts-map',
                        'echarts/chart/bar':'${ctx}/static/js/echarts-map',
                        'echarts/chart/line':'${ctx}/static/js/echarts-map',
                        'echarts/chart/map':'${ctx}/static/js/echarts-map'
                    }
                });
                require(
                        [
                            'echarts',
                            'echarts/chart/bar',
                            'echarts/chart/line',
                            'echarts/chart/map'
                        ],
                        function (ec){
                            var myChart=ec.init(document.getElementById('main'));
                            myChart.setOption({
                                tooltip:{
                                    trigger:'axis'
                                },
                                legend:{
                                    data:['实际统计网吧数','实际平均终端总数','终端平均上座人次（次/每天)','终端平均使用时间（小时/每天）']
                                },
                                toolbox:{
                                    show:true,
                                    feature:{
                                        mark:{show:true},
                                        dataView:{show:true,readonly:false},
                                        magicType:{show:true,type:['line','bar']},
                                        restore:{show:true},
                                        saveAsImage:{show:true}
                                    }
                                },
                                calculable:true,
                                xAxis:[
                                    {
                                        type:'category',
                                        data:staTime
                                    }
                                ],
                                yAxis:[
                                    {
                                        type:'value',
                                        splitArea:{show:true}
                                    }
                                ],
                                series:[
                                    {
                                        name:'实际统计网吧数',
                                        type:'bar',
                                        data:terminaAveage
                                    },
                                    {
                                        name:'实际平均终端总数',
                                        type:'bar',
                                        data:customerCount
                                    },
                                    {
                                        name:'终端平均上座人次（次/每天)',
                                        type:'bar',
                                        data:terminaAveage
                                    },
                                    {
                                        name:'终端平均使用时间（小时/每天）',
                                        type:'bar',
                                        data:terminaTime
                                    }

                                ]
                            });

                        }
                );
            }
        });
    }

</script>
<script type="text/javascript">

</script>
<!--report end -->
<form id="sform" action="" method="post">
<!--查询结果列表 begin-->
<div class="result mlr2 mt5">
    <div>
        <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
            <tr style="height: 35px">
                <td width="20%" class="mc_table_head" align="center">名称：</td>
                <td width="30%" class="mc_table_head" align="left"><span>${searchBO.objectView}</span></td>
                <td width="20%" class="mc_table_head" align="center">编码：</td>
                <td width="30%" class="mc_table_head" align="left"><span>${searchBO.codeView}</span></td>
            </tr>
        </table>
        <table id="rtt" class="easyui-datagrid" title="历史运营状况统计结果" style="width:100%;height:auto"
               data-options="rownumbers:false,
				singleSelect:false,
				striped:true,
				autoRowHeight:false,
				pagination:true,
				pageSize:10,url:'${ctx}/report/historyjson',method:'get',onLoadSuccess:function(){testdata();}">
            <thead>
            <tr id="trl">
                <th data-options="field:'staTime',align:'center'" width="21%">日期</th>
                <th data-options="field:'monitorCode',align:'center'" width="15%">监管中心</th>
                <th data-options="field:'customerCount',align:'center'" width="15%">实际平均顾客总数</th>
                <th data-options="field:'terminaAveage',align:'center'" width="20%">终端平均上座人次（次/每天)</th>
                <th data-options="field:'terminaTime',align:'center'" width="20%">终端平均使用时间(小时/每天)</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!--查询结果列表 end-->
    <!--report begin  -->
    <div id="main"  style="height:500px;border:1px solid #ccc; margin:45px 60px 20px 0px;"></div>
</form>
</body>
</html>
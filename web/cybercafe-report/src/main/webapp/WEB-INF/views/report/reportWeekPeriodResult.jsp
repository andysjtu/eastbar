<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>顾客分时段统计</title>
    <link href="${ctx}/static/styles/report.css"  type="text/css" rel="stylesheet"/>
    <script src="${ctx}/static/js/esl.js"></script>
</head>
<body>
<form id="sform" action="" method="post">
    <!--查询结果列表 begin-->
    <div class="result mlr2 mt5">
               <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
                    <tr>
                        <td class="mc_tab_top02" align="center">
                            顾客分时段统计分析图
                         </td>
                    </tr>
                </table>
                <table cellpadding="0" cellspacing="0" border="1" width="90%" class="mc_tab_line">
                    <tr class="tab_cont01">
                        <td colspan="25" align="left" style="padding-left:10px;"><b>顾客分时段统计分析图（${beginTime}----${endTime}）</b></td>
                    </tr>
                    <tr class="tab_cont03">
                        <td width="10%">时段</td>
                        <td>星期日</td><td>星期一</td><td>星期二</td><td>星期三</td><td>星期四</td><td>星期五</td><td>星期六</td>
                    </tr>
                    <tr>
                        <c:if test="${gogalType == 'customer_count'}">
                            <td>上座人次</td>
                        </c:if>
                        <c:if test="${gogalType == 'online_time'}">
                            <td>使用时间</td>
                        </c:if>
                    <c:forEach items="${lists}" var="s" >
                        <td>${s}</td>
                    </c:forEach>
                    </tr>
                    <tr>
                        <td colspan="25"></td>
                    </tr>
                </table>
        <!--查询结果end-->
    </div>
    <!--report begin  -->
    <div id="main"  style="height:500px;width:90%; margin:45px 60px 20px 0px;"></div>
    <script type="text/javascript">
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
                        title : {
                            text: '顾客分时段统计分析图',
                            subtext: ''
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['上座人次']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        xAxis : [
                            {
                                type : 'category',
                                boundaryGap : false,
                                data : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六']
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value',
                                axisLabel : {
                                    formatter: '{value}'
                                }
                            }
                        ],
                        series : [
                            {
                                name:'上座人次',
                                type:'line',
                                data:${lists},
                                markPoint : {
                                    data : [
                                        {type : 'max', name: '最大值'},
                                        {type : 'min', name: '最小值'}
                                    ]
                                },
                                markLine : {
                                    data : [
                                        {type : 'average', name: '平均值'}
                                    ]
                                }
                            }
                        ]
                    });

                }
        );
    </script>
    <!--report end -->
</form>
</body>
</html>
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
                        <td>0-1</td><td>1-2</td><td>2-3</td><td>3-4</td><td>4-5</td><td>5-6</td><td>6-7</td><td>7-8</td><td>8-9</td><td>9-10</td>
                        <td>10-11</td><td>11-12</td><td>12-13</td><td>13-14</td><td>14-15</td><td>15-16</td><td>16-17</td><td>17-18</td><td>18-19</td><td>19-20</td>
                        <td>20-21</td><td>21-21</td><td>22-23</td><td>23-24</td>
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
    <div id="main"  style="height:500px;width:90%;margin:45px 60px 20px 0px;"></div>
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
                                data : ['0-1','1-2','2-3','3-4','4-5','5-6','6-7','7-8','8-9','9-10','10-11','11-12','12-13','13-14','14-15','15-16','16-17','17-18','18-19','19-20','20-21','21-22','22-23','23-24']
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
    <!--report end -->  </form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>处罚信息统计结果</title>
    <link href="${ctx}/static/styles/report.css"  type="text/css" rel="stylesheet"/>
    <script src="${ctx}/static/js/esl.js"></script>
</head>
<body>
<form id="sform" action="" method="post">
    <!--查询筛选条件 begin -->
    <%--<div class="search mt1 mlr2">--%>
    <%--<table width="100%" cellspacing="0" cellpadding="0">--%>
    <%--<thead>--%>
    <%--<tr onclick="$('.searchContext').toggle();" class="panel-header">--%>
    <%--<!--title -->--%>
    <%--<td class="panel-title">处罚信息统计结果查询</td>--%>
    <%--<!--后留白 -->--%>
    <%--<td>&nbsp;</td>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody>--%>
    <%--<tr class="searchContext panel-body">--%>
    <%--<!--条件输入  下面宽度可根据实际条件修改-->--%>
    <%--<td>--%>
    <%--<div class="mlr8">--%>
    <%--<p><span class="ipm">统计范围--%>
    <%--&lt;%&ndash;<input type="text" name="loginName"/>&ndash;%&gt;--%>
    <%--<select name="siteCode">--%>
    <%--<c:forEach items="${sitePunishList}" var="sitePunish">--%>
    <%--<option value="${sitePunish.siteCode}">${sitePunish.siteName}</option>--%>
    <%--</c:forEach>--%>
    <%--</select>--%>
    <%--</span>--%>
    <%--<span class="ipm">统计类型<select name="punishReasonOrType">--%>
    <%--<option></option>--%>
    <%--<option value="处罚原因">处罚原因</option>--%>
    <%--<option value="处罚类型">处罚类型</option>--%>
    <%--</select></span></p>--%>
    <%--<p class="dp pt5"><span class="ipm">统计时间<input name="btime"  type="text" class="easyui-datetimebox"/></span>--%>
    <%--<span class="ipm">&nbsp;&nbsp;至&nbsp;<input name="etime" type="text" class="easyui-datetimebox"/></span></p>--%>
    <%--</div>--%>
    <%--<input type="submit" class="btn btn-primary sub" style="margin-left: 25px;" value="查询"/>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--</tbody>--%>
    <%--</table>--%>
    <%--</div>--%>
    <!--查询筛选条件 end -->
    <div class="result mlr2 mt5">
        <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
            <tr>
                <td class="mc_tab_top02" align="center">
                    场所处罚信息统计结果
                </td>
            </tr>
        </table>
        <table cellpadding="0" cellspacing="0" border="1" width="100%" class="mc_tab_line">
            <tr class="tab_cont01">
                <td colspan="15" align="left" style="padding-left:10px;"><b>处罚信息统计结果</b></td>
            </tr>
            <tr class="tab_cont03">
                <td width="8%">区县/类型</td>
                <td><span>警告</span></td>
                <td><span>罚款</span></td>
                <td><span>没收工具</span></td>
                <td><span>没收违法所得</span></td>
                <td><span>责令停业整顿</span></td>
                <td><span>吊销许可证</span></td>
            </tr>
            <tr style="height: 25px">
                <td>总计：</td>
                <%--<td>10</td><td>20</td><td>8</td><td>15</td><td>32</td><td>12</td><td>21</td><td>18</td><td>16</td><td>25</td>--%>
                <%--<td>30</td><td>3</td><td>15</td><td>22</td>--%>
                <c:forEach items="${list}" var="reasonList">
                    <td>${reasonList.total}</td>
                </c:forEach>
            </tr>
            <tr>
                <td colspan="25"></td>
            </tr>
        </table>
        <!--report begin  -->
        <div id="main"  style="height:500px;border:1px solid #ccc; margin:45px 60px 20px 60px;"></div>
        <script type="text/javascript">
            require.config({
                paths:{
                    echarts:'${ctx}/static/js/echarts-map',
                    'echarts/chart/pie':'${ctx}/static/js/echarts-map'
                }
            });

            require(
                    [
                        'echarts',
                        'echarts/chart/pie'
                    ],
                    function (ec){
                        var myChart=ec.init(document.getElementById('main'));
                        myChart.setOption({
                            title : {
                                text: '处罚信息统计结果',
                                x:'center'
                            },
                            tooltip : {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                orient : 'vertical',
                                x : 'left',
                                data:['警告','罚款','没收工具','没收违法所得','责令停业整顿','吊销许可证']
                            },
                            toolbox: {
                                show : true,
                                feature : {
                                    mark : {show: true},
                                    dataView : {show: true, readOnly: false},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                            calculable : true,
                            series : [
                                {
                                    name:'场所处罚信息统计结果',
                                    type:'pie',
                                    radius : '55%',
                                    center: ['50%', '60%'],
                                    data:[
                                        {value:${list.get(0).total}, name:'警告'},
                                        {value:${list.get(1).total}, name:'罚款'},
                                        {value:${list.get(2).total}, name:'没收工具'},
                                        {value:${list.get(3).total}, name:'没收违法所得'},
                                        {value:${list.get(4).total}, name:'责令停业整顿'},
                                        {value:${list.get(5).total}, name:'吊销许可证'}
                                    ]
                                }
                            ]
                        });

                    }
            );
        </script>
        <!--report end -->
    </div>
    <%--<script>--%>
    <%--function submit(){--%>
    <%--$('#f1').submit;--%>
    <%--}--%>
    <%--</script>--%>
</form>
</body>
</html>
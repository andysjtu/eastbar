<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>场所终端营运状态</title>
    <link href="${ctx}/static/styles/ipc.css"  type="text/css" rel="stylesheet"/>
    <script src="${ctx}/static/echart/2.0.4/js/esl.js"></script>
</head>
<body>
<form id="sform">
    <!--查询筛选条件 begin -->
    <div class="search mt1 mlr2">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr onclick="$('.searchContext').toggle();" class="panel-header">
                <!--title -->
                <td class="panel-title">营运状态图</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="searchContext panel-body">
                <!--条件输入  下面宽度可根据实际条件修改-->
                <td>
                    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                    <div id="main"  style="height:170px;"></div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--查询筛选条件 end -->
    <!--查询结果列表 begin-->
    <div class="search mlr2 mt5">
        <table width="100%" cellspacing="0" cellpadding="0">
            <thead>
            <tr class="panel-header">
                <!--title -->
                <td class="panel-title">场所营运信息</td>
                <!--后留白 -->
                <td>&nbsp;</td>
            </tr>
            </thead>
            <tbody>
            <tr class="panel-body">
                <td>场所编码：${tb.siteCode}，场所名称：${tb.siteName}，场所状态：<span class="runStatus">${tb.siteRunStatus}</span></td>
            </tr>
            <tr class="panel-body">
                <td>上次更新时间：${tb.lastUpdateTime}，使用中 ${tb.siteTerminalTotalNum} 台</td>
            </tr>
            <tr class="panel-body">
                <td><div class="terBox"><div class="col">
                    <c:forEach items="${tbs}" var="tb">
                    <div class="ter">
                        <div class="dropdown">
                            <!-- Link or button to toggle dropdown -->
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                <li class="li">${tb.customerName}</li>
                                <li class="li">${tb.onlineTime}</li>
                                <li class="divider"></li>
                               <c:choose>
                                    <c:when test="${tb.siteState=='ONLINE'}">
                                        <li style="background-color: lightskyblue"><a tabindex="-1" href="javascript:void(0);" onclick="detail('${tb.hostIp}')">${tb.hostIp}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li style="background-color: darkgray"><a tabindex="-1"  href="javascript:void(0);">${tb.hostIp}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div></div>
                    </c:forEach>
                    </div></div></td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
<jsp:include page="siteTerminalDetail.jsp"/>


<script type="text/javascript">

    $(function(){
        var json = ${json};
        $.each(json,function(i,item){
            $.cachePut(item.hostIp,item);
        });
        var run = ${tb.siteRunStatus};
        if(run){
            $(".runStatus").html('正常');
        }else{
            $(".runStatus").html('故障');
        }
    });

    require.config({
        paths:{
            echarts:'${ctx}/static/js/echarts-map',
            'echarts/chart/bar':'${ctx}/static/echart/2.0.4/js/echarts-map',
            'echarts/chart/line':'${ctx}/static/echart/2.0.4/js/echarts-map',
            'echarts/chart/map':'${ctx}/static/echart/2.0.4/js/echarts-map'
        }
    });
    require(
            [
                'echarts',
                'echarts/chart/bar',
                'echarts/chart/line',
                'echarts/chart/map'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main'));

                var option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data:['空闲', '使用','未知','关机']
                    },
                    xAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    yAxis : [
                        {
                            type : 'category',
                            data : ['营运状态']
                        }
                    ],
                    series : [
                        {
                            name:'空闲',
                            type:'bar',
                            stack: '总量',
                            itemStyle : { normal: {label : {show: true, position: 'inside'}}},
                            data:[${tb.siteStatus[0]}]
                        },
                        {
                            name:'使用',
                            type:'bar',
                            stack: '总量',
                            itemStyle : { normal: {label : {show: true, position: 'inside'}}},
                            data:[${tb.siteStatus[1]}]
                        },
                        {
                            name:'未知',
                            type:'bar',
                            stack: '总量',
                            itemStyle : { normal: {label : {show: true, position: 'inside'}}},
                            data:[${tb.siteStatus[2]}]
                        },
                        {
                            name:'关机',
                            type:'bar',
                            stack: '总量',
                            itemStyle : { normal: {label : {show: true, position: 'inside'}}},
                            data:[${tb.siteStatus[3]}]
                        }
                    ]
                };
                // 为echarts对象加载数据
                myChart.setOption(option);
            }
    );
    function detail(ip){
        $("#myModal").cacheApply(ip);
        $("#myModal").modal('toggle');

        var certType = $("#customerIdType");
        certType.html(replaceCert(certType.html()));
//        switch (certType.html()){
//            case "2":certType.html('身份证');break;
//        }
    }

</script>
<script src="${ctx}/static/js/ipc.js" type="text/javascript"></script>
</body>
</html>
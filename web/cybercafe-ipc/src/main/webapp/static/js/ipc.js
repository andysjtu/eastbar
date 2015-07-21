//require(
//    [
//        'echarts',
//        'echarts/chart/bar',
//        'echarts/chart/line',
//        'echarts/chart/map'
//    ],
//    function (ec) {
//        // 基于准备好的dom，初始化echarts图表
//        var myChart = ec.init(document.getElementById('main'));
//
//        var option = {
//            tooltip: {
//                trigger: 'axis',
//                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
//                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
//                }
//            },
//            legend: {
//                data:['空闲', '使用','锁定','关机']
//            },
//            xAxis : [
//                {
//                    type : 'value'
//                }
//            ],
//            yAxis : [
//                {
//                    type : 'category',
//                    data : ['营运状态']
//                }
//            ],
//            series : [
//                {
//                    name:'空闲',
//                    type:'bar',
//                    stack: '总量',
//                    itemStyle : { normal: {label : {show: true, position: 'inside'}}},
//                    data:[${tb.siteStatus[0]}]
//                },
//                {
//                    name:'使用',
//                    type:'bar',
//                    stack: '总量',
//                    itemStyle : { normal: {label : {show: true, position: 'inside'}}},
//                    data:[${tb.siteStatus[1]}]
//                },
//                {
//                    name:'锁定',
//                    type:'bar',
//                    stack: '总量',
//                    itemStyle : { normal: {label : {show: true, position: 'inside'}}},
//                    data:[${tb.siteStatus[2]}]
//                },
//                {
//                    name:'关机',
//                    type:'bar',
//                    stack: '总量',
//                    itemStyle : { normal: {label : {show: true, position: 'inside'}}},
//                    data:[${tb.siteStatus[3]}]
//                }
//            ]
//        };
//        // 为echarts对象加载数据
//        myChart.setOption(option);
//    }
//);

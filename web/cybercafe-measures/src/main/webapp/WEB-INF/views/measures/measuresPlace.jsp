<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>场所营业时间规范</title>
    <link href="${ctx}/static/styles/measures.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div style="margin-top:10px">
    <table width="60%" class="tab_line">
        <tr>
            <td align="left" colspan="5" class="tab_top_title02">场所营业时间规范</td>
        </tr>
        <tr>
            <td>
            <div style="margin-bottom:5px">
                <shiro:hasPermission name="measures:shopHour:add"><a href="${ctx}/measures/intoPlaceAdd#_4" class="easyui-linkbutton" iconCls="icon-add"  plain="true"></a></shiro:hasPermission>
            </div>
            </td>
        </tr>
    </table>
    <hr/><blockquote> <small>按星期设置场所营业时间</small></blockquote>
    <table width="60%" class="tab_line">
        <tr class="tab_sub_title">
            <td width="20%">日期</td>
            <td>营业时间</td>
            <td>版本号</td>
            <td>发布状态</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${weeks}" var="week">
        <tr class="tr_h">
            <td>
             <c:if test="${week.dayOfWeek==0}">
                 星期日
             </c:if>
             <c:if test="${week.dayOfWeek==1}">
                    星期一
             </c:if>
             <c:if test="${week.dayOfWeek==2}">
                    星期二
             </c:if>
             <c:if test="${week.dayOfWeek==3}">
                   星期三
             </c:if>
             <c:if test="${week.dayOfWeek==4}">
                  星期四
             </c:if>
             <c:if test="${week.dayOfWeek==5}">
                  星期五
             </c:if>
             <c:if test="${week.dayOfWeek==6}">
                  星期六
             </c:if>
            </td>
            <td>
                  ${week.startTime1}点<-
                  <c:if test="${week.operStatus1==1}">
                      营业->
                  </c:if>
                  <c:if test="${week.operStatus1==0}">
                      打烊->
                  </c:if>
                  ${week.endTime1}点
                  &nbsp;&nbsp;&nbsp;
                  ${week.startTime2}点<-
                  <c:if test="${week.operStatus2==1}">
                          营业->
                 </c:if>
                 <c:if test="${week.operStatus2==0}">
                          打烊->
                 </c:if>
                          ${week.endTime2}点
                      &nbsp;&nbsp;&nbsp;
                          ${week.startTime3}点<-
                      <c:if test="${week.operStatus3==1}">
                          营业->
                      </c:if>
                      <c:if test="${week.operStatus3==0}">
                          打烊->
                      </c:if>
                          ${week.endTime3}点
                  </td>
            <td>${week.version}</td>
            <td>
               <c:if test="${week.isPub==0}">
                   未发布
               </c:if>
               <c:if test="${week.isPub==1}">
                   已发布
               </c:if>
            </td>
            <td>
                <shiro:hasPermission name="measures:shopHour:edit"><a href="#" onclick="editWeek(${week.id})" class="link_col01">修改</a></shiro:hasPermission>&nbsp;
                <c:if test="${week.isPub==0}">
                <shiro:hasPermission name="measures:shopHour:public"><a href="#" onclick="publish(${week.id})" class="link_col01">发布</a></shiro:hasPermission>&nbsp;
                </c:if>

                <shiro:hasPermission name="measures:shopHour:delete"><a href="#" onclick="del(${week.id})" class="link_col01">删除</a></shiro:hasPermission>

            </td>
        </tr>
        </c:forEach>
    </table>
    <table  width="60%" class="tab_line">
        <hr/><blockquote> <small>按具体日期设置场所营业时间</small></blockquote>
        <tr class="tab_sub_title">
            <td width="20%">日期</td>
            <td >营业时间</td>
            <td >版本号</td>
            <td>发布状态</td>
            <td >操作</td>
        </tr>
        <c:forEach items="${days}" var="day">
        <tr class="tr_h">
               <td>
                  从&nbsp;${day.startDate}

                  <br>至&nbsp;${day.endDate}
               </td>
               <td>
                   ${day.startTime1}点<-
                   <c:if test="${day.operStatus1==1}">
                       营业->
                   </c:if>
                   <c:if test="${day.operStatus1==0}">
                       打烊->
                   </c:if>
                   ${day.endTime1}点
                   &nbsp;&nbsp;&nbsp;
                   ${day.startTime2}点<-
                       <c:if test="${day.operStatus2==1}">
                           营业->
                       </c:if>
                       <c:if test="${day.operStatus2==0}">
                           打烊->
                       </c:if>
                           ${day.endTime2}点
                       &nbsp;&nbsp;&nbsp;
                           ${day.startTime3}点<-
                       <c:if test="${day.operStatus3==1}">
                           营业->
                       </c:if>
                       <c:if test="${day.operStatus3==0}">
                           打烊->
                       </c:if>
                           ${day.endTime3}点
                       &nbsp;&nbsp;&nbsp;
              </td>
            <td>${day.version}</td>
            <td>
                <c:if test="${day.isPub==0}">
                    未发布
                </c:if>
                <c:if test="${day.isPub==1}">
                    已发布
                </c:if>
            </td>
            <td>
                <shiro:hasPermission name="measures:shopHour:edit"><a href="#" onclick="editDate(${day.id})" >修改</a></shiro:hasPermission>&nbsp;
                <c:if test="${day.isPub==0}">
                    <shiro:hasPermission name="measures:shopHour:public"><a href="#" onclick="publish(${day.id})" class="link_col01">发布</a></shiro:hasPermission>&nbsp;
                </c:if>
                <shiro:hasPermission name="measures:shopHour:delete"><a href="#"  onclick="del(${day.id})" >删除</a></shiro:hasPermission>

            </td>
        </tr>
        </c:forEach>
     </table>
    <script>
        function editWeek(id){
            window.location="${ctx}/measures/intoPlaceWeekEdit/"+id+"#_4";
        }

        function editDate(id){
            window.location="${ctx}/measures/intoPlaceDateEdit/"+id+"#_4";
        }

        function del(id){
            if(window.confirm("确定要删除这条记录吗？")){
                window.location="${ctx}/measures/placeDelete/"+id+"#_4";
            }

        }
        function publish(id){
            if(window.confirm("确定要发布这条记录吗？")){
                window.location="${ctx}/measures/placePublic/"+id+"#_4";
            }

        }

        function releaseMany(){
            //获取表格选择行
            var rows = $('#rtt').datagrid('getSelections');
            //判断是否选择行
            if (!rows || rows.length == 0) {
                $.messager.alert('提示', '请选择要发布的数据!', 'info');
                return;
            }

            var ids=[];
            for (var i = 0; i < rows.length; i++) {
                //获取checkbox值
                var id=rows[i].id;
                ids.push(id); //然后把单个id循环放到ids的数组中
            }

            if(confirm("确定要发布选中的多个时间规范吗?")){
                $.ajax({
                    type:'POST',
                    url: '${ctx}/measures/releaseManyPlace/'+ids,
                    data: '',
                    dataType: "json",
                    success: function(data){
                        showTips(data.msg);
                        rertt();
                    }
                });

            }
        }

        function rertt(){
            window.reload();
        }
     </script>
  </div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title> 信任URL访问记录排行</title>
    <link href="${ctx}/static/styles/report.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="mc_tit um_box" style="width:950px;">
         <table width="100%" class="tab_line" cellpadding="0" cellspacing="1">
              <tr>
                  <td class="mc_tab_top02" align="center">
                      信任URL访问记录排行榜
                  </td>
              </tr>
         </table>
         <form id="f1" action="trustUrlRankSearch#_6" method="post">
             <table border="1" width="100%" class="mc_tab_line">
                 <tr class="tab_cont01">
                     <td colspan="4" align="left" style="padding-left:10px;"><b>信任URL排行榜条件输入</b></td>
                 </tr>
                 <tr class="tab_cont03">
                     <td width="130">起始时间</td>
                     <td align="left">
                         <input type="text" name="begindate" class="easyui-datetimebox" required style="width:200px" />
                     </td>
                     <td>
                         结束时间
                     </td>
                     <td align="left">
                         <input type="text" name="enddate" class="easyui-datetimebox" required style="width:200px" />
                     </td>
                 </tr>
                 <tr class="tab_cont03">
                     <td width="130">选定范围</td>
                     <td style="padding:0px;" colspan="3">
                         <table width="100%" border="1">
                             <tr>
                                 <td align="left" style="border:0px; width:100px;">
                                     &nbsp;<input name="" type="radio" value="">
                                     所有
                                 </td>
                                 <td align="left" style="border-top:0px; border-right:0px;">
                                     &nbsp;对所有场所的顾客URL访问记录进行排行
                                 </td>
                             </tr>
                             <tr>
                                 <td align="left" style="border-left:0px;">
                                     &nbsp;<input name="" type="radio" value="">
                                     按市级
                                 </td>
                                 <td align="left" style="border:0px; line-height:30px;">
                                     &nbsp;<select name=""  style="width:100px;">
                                     <option>市辖区</option>
                                 </select>

                                 </td>

                             </tr>
                             <tr>
                                 <td align="left" style="border:0px;">
                                     &nbsp;<input name="" type="radio" value="">
                                     按区/县级
                                 </td>
                                 <td align="left" style=" border-right:0px;border-bottom:0px;  line-height:30px;">
                                     &nbsp;<select name=""  style="width:160px;">
                                     <option>黄浦区监管中心</option>
                                 </select>

                                 </td>
                             </tr>
                             <tr>
                                 <td align="left" style=" border-bottom:0px; border-right:0px; border-left:0px;">
                                     &nbsp;<input name="" type="radio" value="">
                                     按场所
                                 </td>
                                 <td align="left" style=" border-right:0px;border-bottom:0px; line-height:30px; ">
                                     &nbsp;<input name="" type="text" class="mc_inp">

                                 </td>
                             </tr>
                             <tr>
                                 <td align="left" style=" border-bottom:0px; border-right:0px; border-left:0px;">
                                     &nbsp;<input name="" type="radio" value="">
                                     按照编码
                                 </td>
                                 <td align="left" style=" border-right:0px;border-bottom:0px; line-height:30px; ">
                                     &nbsp;起始编码：<input name="" type="text" class="mc_inp">
                                     结束编码：<input name="" type="text" class="mc_inp">
                                 </td>
                             </tr>
                         </table>
                     </td>
                 </tr>
             </table>
                <table width="100%" class="mc_btnbox">
                    <tr>
                        <td align="right" style="padding-top: 4px"><a href="#" onclick="save()"class="mc_btn03">确&nbsp;定</a></td>
                        <td align="center"style="padding-top: 4px" width="150"><a href="#" class="mc_btn03">清&nbsp;空</a></td>
                        <td align="left" style="padding-top: 4px"><a href="${ctx}/report/list#_6" class="mc_btn03">返&nbsp;回</a></td>
                    </tr>
                </table>
         </form>
</div>
<script>
    function save(){
        $('#f1').submit();
    }
</script>
</body>
</html>
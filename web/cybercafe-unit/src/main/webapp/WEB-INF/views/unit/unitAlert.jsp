<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>报警信息查询</title>
    <link href="${ctx}/static/styles/unit.css"  type="text/css" rel="stylesheet"/>
    <script src="${ctx}/static/js/unit.js" type="text/javascript"></script>
</head>
<body>
<div class="mc_tit um_box" style="width:750px;">
    <form id="f1" action="alertList#_5" mehod="post">
    <table width="100%" class="tab_line" border="1">
        <tr>
            <td class="mc_tab_top02" align="center">
                报警信息查询
            </td>
        </tr>
    </table>
    <table border="1" width="100%" class="mc_tab_line">
        <tr class="tab_cont01">
            <td colspan="4" align="left" style="padding-left:10px;"><b>报警信息查询</b></td>
        </tr>
        <tr class="tab_cont03">
            <td width="120">监管中心编码</td>
            <td align="left">
              <select name="">
                   <option>上海网吧监管中心</option>
              </select></td>
            <td width="120">场所编码</td>
            <td align="left">
                 <input name="" type="text" class="mc_inp">
            </td>
        </tr>
        <tr class="tab_cont03">
            <td>顾客帐号</td>
            <td align="left">
               <input name="" type="text" class="mc_inp">
            </td>
            <td>顾客姓名</td>
            <td align="left">
               <input name="" type="text" class="mc_inp">
            </td>
        </tr>
        <tr class="tab_cont03">
            <td>证件类型</td>
            <td align="left">
               <select name="" style="width:100px;">
                    <option>--全部--</option>
               </select></td>
            <td>证件号码</td>
            <td align="left">
               <input name="" type="text" class="mc_inp"></td>
        </tr>
        <tr class="tab_cont03">
             <td>发证机关</td>
             <td align="left">
               <input name="" type="text" class="mc_inp">
             </td>
             <td>使用机器IP</td>
             <td align="left">
               <input name="" type="text" class="mc_inp">
             </td>
        </tr>
        <tr class="tab_cont03">
             <td>起始时间</td>
             <td align="left">
                 <input type="text" name="begindate" class="easyui-datetimebox" required style="width:200px" />
             </td>
             <td>结束时间</td>
             <td align="left">
                  <input type="text" name="enddate" class="easyui-datetimebox" required style="width:200px" />
             </td>
        </tr>
        <tr class="tab_cont03">
             <td>报警类型</td>
             <td colspan="3" style="padding:0px;">
                <table width="100%">
                                <tr>
                                    <td style="border:0px; width:80px;">
                                        <input name="" type="radio" value="">
                                        所有
                                    </td>
                                    <td style="border-top:0px; border-right:0px;">
                                        包括游戏类和所有其它类型的报警
                                    </td>
                                </tr>
                                <tr>
                                    <td style="border-left:0px;">
                                        <input name="" type="radio" value="">
                                        游戏类
                                    </td>
                                    <td style="border:0px; line-height:30px;">
                                        <select name="" style="width:120px;">
                                            <option>--全部--</option>
                                        </select>
                                        <select name="">
                                            <option>--全部--</option>
                                        </select>
                                        <select name=""  style="width:120px;">
                                            <option>--全部--</option>
                                        </select>
                                        <select name=""  style="width:120px;">
                                            <option>--全部--</option>
                                        </select>
                                        <select name="">
                                            <option>--全部--</option>
                                        </select>
                                        <select name=""  style="width:120px;">
                                            <option>--全部--</option>
                                        </select>
                                        <select name=""  style="width:80px;">
                                            <option>--全部--</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="border:0px;">
                                        <input name="" type="radio" value="">
                                        其他
                                    </td>
                                    <td style=" border-right:0px; border-bottom:0px;">
                                        <select name="" style="width:280px;">
                                            <option>--全部--</option>
                                        </select>
                                    </td>
                                </tr>
                </table>
             </td>
        </tr>
    </table>
    <table width="100%" class="mc_btnbox" bgcolor="#f5f5f5" >
        <tr>
             <td align="right" style="padding-top: 4px" width="120"><a href="#" onclick="save()" class="mc_btn03">查&nbsp;询</a></td>
             <td align="center" style="padding-top: 4px" width="100"><a href="#" class="mc_btn03">清&nbsp;空</a></td>
             <td align="left" style="padding-top: 4px"><a href="${ctx}/unit/list#_5" class="mc_btn03">返&nbsp;回</a></td>
             <td align="center">
                  <a href="#"  class="link_col01">报警明细</a>&nbsp;&nbsp;
                  <a href="#" class="link_col01">排名</a>&nbsp;&nbsp;
                  <a href="#" class="link_col01">未成年人报警即时排名</a>
              </td>
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
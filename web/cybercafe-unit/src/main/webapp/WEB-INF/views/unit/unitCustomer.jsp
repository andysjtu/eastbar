<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>顾客信息查询</title>
    <link href="${ctx}/static/styles/unit.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
    <div class="mc_tit um_box" style="width:750px;">
        <form id="f1" action="customerList#_5" method="post">
               <table width="100%" class="tab_line" border="1">
                    <tr>
                        <td class="mc_tab_top02" align="center">
                            顾客信息查询
                        </td>
                    </tr>
               </table>
               <table border="1" width="100%" class="mc_tab_line">
                    <tr class="tab_cont01">
                        <td colspan="4" align="left" style="padding-left:10px;"><b>顾客信息查询</b></td>
                    </tr>
                    <tr class="tab_cont03">
                        <td width="120">监管中心编码</td>
                        <td align="left">
                            <select name="" class="mc_sel">
                                <option>上海网吧监管中心</option>
                            </select>
                        </td>
                        <td width="120">顾客帐号</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                    </tr>
                    <tr class="tab_cont03 sc_lineh">
                        <td>处罚人</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                        <td>顾客姓名</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                    </tr>
                    <tr class="tab_cont03 sc_lineh">
                        <td>证件类型</td>
                        <td align="left">
                            <select name="">
                                <option>上海网吧监管中心</option>
                            </select>
                        </td>
                        <td>证件号码</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                    </tr>
                    <tr class="tab_cont03 sc_lineh">
                        <td>发证机关</td>
                        <td align="left">
                            <select name="" >
                                <option>上海网吧监管中心</option>
                            </select>
                        </td>
                        <td>单位名</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                    </tr>
                    <tr class="tab_cont03 sc_lineh">
                        <td>国家名</td>
                        <td align="left">
                            <select name="">
                                <option>上海网吧监管中心</option>
                            </select>
                        </td>
                        <td>详细描述</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                    </tr>
                </table>

               <table width="100%" class="mc_btnbox">
                    <tr>
                        <td align="right" style="padding-top: 4px"><a href="#" onclick="save()" class="mc_btn03">确&nbsp;定</a></td>
                        <td align="center" width="150" style="padding-top: 4px"><a href="#" class="mc_btn03">清&nbsp;空</a></td>
                        <td align="left" style="padding-top: 4px"><a href="${ctx}/unit/list#_5" class="mc_btn03">返&nbsp;回</a></td>
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
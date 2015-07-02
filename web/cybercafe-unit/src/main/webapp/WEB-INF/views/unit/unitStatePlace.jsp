<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>场所状态查询</title>
    <link href="${ctx}/static/styles/unit.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
    <div class="mc_tit um_box" style="width:750px;">
        <form id="" action="" method="">
                <table width="100%" class="tab_line" style="padding-top: 4px">
                    <tr>
                        <td class="mc_tab_top02" align="center">
                            场所状态查询
                        </td>
                    </tr>
                </table>
                <table border="1" width="100%" class="mc_tab_line">
                    <tr class="tab_cont01">
                        <td colspan="4" align="left" style="padding-left:10px;"><b>场所状态查询</b></td>
                    </tr>
                    <tr class="tab_cont03 is_lheight">
                        <td width="120">监管中心编码</td>
                        <td align="left">
                            <select name="">
                                <option>上海网吧监管中心</option>
                            </select>
                        </td>

                    </tr>
                    <tr class="tab_cont03 is_lheight">
                        <td>是否在线</td>
                        <td align="left">
                            <select name=""  style="width:100px;">
                                <option>全部</option>
                            </select>
                        </td>
                    </tr>
                    <tr class="tab_cont03 is_lheight">
                        <td>
                            运行状态
                        </td>
                        <td align="left">
                            <input name="" type="radio" value=""> 开业
                            <input name="" type="radio" value=""> 停业
                            <input name="" type="radio" value=""> 整顿
                            <input name="" type="radio" value=""> 装修
                            <input name="" type="radio" value=""> 搬迁
                            <input name="" type="radio" value=""> 吊销
                        </td>
                    </tr>
                </table>
                <table width="100%" class="mc_btnbox">
                    <tr>
                        <td align="right" style="padding-right:12px; padding-top: 4px"><a href="#" class="mc_btn03">确&nbsp;定</a></td>
                        <td align="left" style="padding-left:12px; padding-top: 4px"><a href="#" class="mc_btn03">清&nbsp;空</a></td>
                        <td align="left" style="padding-left:12px; padding-top: 4px"><a href="${ctx}/report/list#_6" class="mc_btn03">返&nbsp;回</a></td>
                    </tr>
                </table>
        </form>
    </div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>场所处罚信息查询</title>
    <link href="${ctx}/static/styles/unit.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
        <div class="mc_tit um_box" style="width:750px;">
            <form id="" method="" action="">
                <table width="100%" class="tab_line" border="1">
                    <tr>
                        <td class="mc_tab_top02" align="center">
                            场所处罚信息查询
                        </td>
                    </tr>
                </table>
                <table border="1" width="100%" class="mc_tab_line">
                    <tr class="tab_cont01">
                        <td colspan="4" align="left" style="padding-left:10px;"><b>处罚信息查询</b></td>
                    </tr>
                    <tr class="tab_cont03">
                        <td width="120">监管中心编码</td>
                        <td align="left">
                            <select name="">
                                <option>上海网吧监管中心</option>
                            </select>
                        </td>
                        <td width="120">场所编码</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                    </tr>
                    <tr class="tab_cont03">
                        <td>处罚人</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                        <td>状 态</td>
                        <td align="left">
                            <select name=""  style="width:100px;">
                                <option>--全部--</option>
                            </select>
                        </td>
                    </tr>
                    <tr class="tab_cont03">
                        <td>处罚时间</td>
                        <td colspan="3" align="left">
                            <input type="text" name="punishdate" class="easyui-datetimebox" required style="width:200px" />
                        </td>
                    </tr>
                    <tr class="tab_cont03">
                        <td>处罚原因</td>
                        <td colspan="3" align="left">
                            <input name="" type="checkbox" value=""> 涂改、出租、出借等转让"经营许可证"&nbsp;
                            <input name="" type="checkbox" value=""> 在规定的营业时间外营业<br>
                            <input name="" type="checkbox" value=""> 接纳未成年人进入营业场所&nbsp;
                            <input name="" type="checkbox" value=""> 经营非网络游戏及违法游戏<br>
                            <input name="" type="checkbox" value=""> 擅自停止实施经营管理技术措施&nbsp;
                            <input name="" type="checkbox" value=""> 未悬挂网络文化经营许可证<br>
                            <input name="" type="checkbox" value=""> 未通过局域网的方式接入互联网&nbsp;
                            <input name="" type="checkbox" value=""> 未建立场内巡查制度<br>
                            <input name="" type="checkbox" value=""> 发现消费者违法行为未予制止并举报&nbsp;
                            <input name="" type="checkbox" value=""> 未按规定核对、登记上网者的有效证件<br>
                            <input name="" type="checkbox" value=""> 未记录有关上网信息&nbsp;
                            <input name="" type="checkbox" value=""> 未按规定保存登记内容、记录备份<br>
                            <input name="" type="checkbox" value=""> 变更名称、住所、法人或负责人等&nbsp;
                            <input name="" type="checkbox" value=""> 其他
                        </td>
                    </tr>
                    <tr class="tab_cont03">
                        <td>处罚类型</td>
                        <td colspan="3" align="left">
                            <input name="" type="checkbox" value=""> 警告&nbsp;
                            <input name="" type="checkbox" value=""> 罚款&nbsp;
                            <input name="" type="checkbox" value=""> 没收工具&nbsp;
                            <input name="" type="checkbox" value=""> 没收违法所得<br>
                            <input name="" type="checkbox" value=""> 责令停业整顿&nbsp;
                            <input name="" type="checkbox" value=""> 吊销许可证
                        </td>
                    </tr>
                </table>
                <table width="100%" class="mc_btnbox">
                    <tr>
                        <td align="right" style=" padding-top: 4px;"><a href="#" class="mc_btn03">确&nbsp;定</a></td>
                        <td align="center" width="150" style=" padding-top: 4px;"><a href="#" class="mc_btn03">清&nbsp;空</a></td>
                        <td align="left" style=" padding-top: 4px;"><a href="${ctx}/unit/list#_5" class="mc_btn03" >返&nbsp;回</a></td>
                    </tr>
                </table>
            </form>
        </div>
</body>
</html>
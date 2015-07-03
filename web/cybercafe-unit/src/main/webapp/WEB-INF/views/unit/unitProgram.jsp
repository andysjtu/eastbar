<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>应用程序使用记录查询输入</title>
    <link href="${ctx}/static/styles/unit.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
      <div class="mc_tit um_box" style="width:750px;">
          <form id="f1" action="programList#_5" method="post">
                <table width="100%" class="tab_line" border="1">
                    <tr>
                        <td class="mc_tab_top02" align="center">
                            应用程序使用记录查询输入
                        </td>
                    </tr>
                </table>
                <table border="1" width="100%" class="mc_tab_line">
                    <tr class="tab_cont01">
                        <td colspan="4" align="left" style="padding-left:10px;"><b>应用程序使用记录查询</b></td>
                    </tr>
                    <tr class="tab_cont03 is_lheight">
                        <td width="120">场所编码</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                        <td width="120">顾客上网日志编号</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                    </tr>
                    <tr class="tab_cont03 is_lheight">
                        <td>应用程序名称</td>
                        <td align="left">
                            <input name="" type="text" class="mc_inp">
                        </td>
                        <td colspan="2"></td>

                    </tr>
                    <tr class="tab_cont03 is_lheight">
                        <td>起始时间</td>
                        <td colspan="3" align="left">
                            <input type="text" name="begindate" class="easyui-datetimebox" required style="width:200px" />
                        </td>
                    </tr>
                    <tr class="tab_cont03 is_lheight">
                        <td>结束时间</td>
                        <td colspan="3" align="left">
                            <input type="text" name="enddate" class="easyui-datetimebox" required style="width:200px" />
                        </td>
                    </tr>
                </table>
                <table width="100%" class="mc_btnbox">
                    <tr>
                        <td align="right" style="padding-top: 4px;"><a href="#" onclick="save()" class="mc_btn03">确&nbsp;定</a></td>
                        <td align="center" style="padding-top: 4px;" width="150"><a href="#" class="mc_btn03">清&nbsp;空</a></td>
                        <td align="left" style="padding-top: 4px;"><a href="${ctx}/unit/list#_5" class="mc_btn03">返&nbsp;回</a></td>
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
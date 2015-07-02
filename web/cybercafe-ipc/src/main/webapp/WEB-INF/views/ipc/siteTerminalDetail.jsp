 <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 id="myModalLabel">终端信息及远程控制</h4>
    </div>
    <div class="modal-body form-horizontal">
        <div class="mlr8" style="border: 1px solid #d4d4d4;">
            <table width="100%" border="1" style="margin: 2px;" cellspacing="0">
                <tr>
                    <td width="18%" height="30">　场所编码</td>
                    <td width="32%" height="30" id="siteCode"></td>
                    <td width="18%" height="30">　场所名称</td>
                    <td width="32%" height="30" id="siteName">${tb.siteName}</td>
                </tr>
                <tr>
                    <td height="30">　客户端IP</td>
                    <td height="30" id="hostIp">&nbsp; 192.168.000.021</td>
                    <td height="30"> 　上网时间</td>
                    <td height="30" id="onlineTime">&nbsp; 2014-10-28 11:18:45</textarea>
                    </td>
                </tr>
                <tr>
                    <td height="30">　顾客名称</td>
                    <td height="30" id="customerName">&nbsp; 杨恩杰</td>
                    <td height="30"> 　证件类型</td>
                    <td height="30" id="customerIdType">&nbsp; 身份证</textarea>
                    </td>
                </tr>
                <tr>
                    <td height="30">　证件号码</td>
                    <td height="30" id="certId">&nbsp; </td>
                    <td height="30"> 证件发行机构</td>
                    <td height="30" id="authOrg">&nbsp; &nbsp;
                    </td>
                </tr>
                <tr>
                    <td height="30">　顾客组织</td>
                    <td height="30">&nbsp; &nbsp;</td>
                    <td height="30"> 　国籍</td>
                    <td height="30" id="nationality">&nbsp; &nbsp;
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="modal-footer">
        <shiro:hasPermission name="terminal:clientControl:turnOff"><button class="btn btn-info" style="margin:0 1px;" onclick="order(0);">关机</button></shiro:hasPermission>
        <shiro:hasPermission name="terminal:clientControl:restart"><button class="btn btn-info" style="margin:0 1px;" onclick="order(1);">重新启动</button></shiro:hasPermission>
        <shiro:hasPermission name="terminal:clientControl:lock"><button class="btn btn-info" style="margin:0 1px;" onclick="order(2);">锁定</button></shiro:hasPermission>
        <shiro:hasPermission name="terminal:clientControl:unlock"><button class="btn btn-info" style="margin:0 1px;" onclick="order(3);">解锁</button></shiro:hasPermission>
        <shiro:hasPermission name="terminal:clientControl:screenShots"><button class="btn btn-info" data-dismiss="modal" style="margin:0 1px;" onclick="order(4);">截取屏幕</button></shiro:hasPermission>
        <button class="btn" data-dismiss="modal" style="margin: 0 30px;" aria-hidden="true">关闭</button>
    </div>
</div>
<script type="text/javascript">
    function order(type){
        var ip = $("#hostIp").html();
        var siteCode = ${tb.siteCode};
        var cmd=$.get(ip);
        var cmdBO= allPrpos(cmd);
        switch (type){
            case 0: ajax("${ctx}/ipc/${tb.siteCode}/gj/"+cmdBO+"/");break;
            case 1: ajax("${ctx}/ipc/${tb.siteCode}/cq/"+cmdBO+"/");break;
            case 2: ajax("${ctx}/ipc/${tb.siteCode}/sd/"+cmdBO+"/");break;
            case 3: ajax("${ctx}/ipc/${tb.siteCode}/js/"+cmdBO+"/");break;
            case 4: window.location = "${ctx}/ipc/${tb.siteCode}/jp/"+cmdBO+"/#_0";break;
        }
    }

    function ajax(url){
        $.ajax({
            type:"GET",
            url:url,
            success: function(msg){
                showTips(msg);
            }
        });
    }
    function allPrpos ( obj ) {
        // 用来保存所有的属性名称和值
        var props = "{" ;
        // 开始遍历
        for ( var p in obj ){ // 方法
            if ( typeof ( obj [ p ]) == " function " ){ obj [ p ]() ;
            } else { // p 为属性名称，obj[p]为对应属性的值
                props += "\""+p +"\":\"" + obj [ p ] + "\"," ;
            } } // 最后显示所有的属性
        props=props.substring(0,props.length-1);
        props+="}";
        return  props ;
    }
</script>
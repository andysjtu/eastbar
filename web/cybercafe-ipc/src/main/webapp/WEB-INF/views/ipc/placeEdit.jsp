<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改营业场所</title>
    <link href="${ctx}/static/styles/ipc.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="result">
    <div>
    <form id="f1" action="${ctx}/ipc/submitPlaceEdit" method="post" class="form-horizontal">
        <div class="mlr8" style="border: 1px solid #d4d4d4;">
            <hr/><blockquote> <small>场所基本信息</small></blockquote>
            <input type="hidden" value="${site.monitorCode}" id="monitorCode"/>
            <div class="control-group">
                <label class="control-label">场所编码</label>
                <div class="controls">
                    <input type="text" name="siteCode" value="${site.siteCode}"  placeholder="场所编码" readonly="readonly">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">场所名称</label>
                <div class="controls">
                    <input type="text" name="name" id="name" value="${site.name}" placeholder="场所名称" onblur="validateSiteName()">
                    *必填（场所名称由3-20位字符组成）
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">场所地址</label>
                <div class="controls">
                    <input type="text" name="address" value="${site.address}" placeholder="场所地址">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">邮政编码</label>
                <div class="controls">
                    <input type="text" name="zip" id="zip" value="${site.zip}" placeholder="邮政编码" onblur="validateCode()">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">运行状态</label>
                <div class="controls">
                    <c:if test="${site.regStatus==0}">
                    <input name="regStatus" type="radio" value="0" checked="checked"> 开业
                    <input name="regStatus" type="radio" value="1" > 停业
                    <input name="regStatus" type="radio" value="2" > 整顿
                    <input name="regStatus" type="radio" value="3" > 装修　
                    <input name="regStatus" type="radio" value="4" > 搬迁
                    <input name="regStatus" type="radio" value="5" > 吊销
                    </c:if>
                    <c:if test="${site.regStatus==1}">
                        <input name="regStatus" type="radio" value="0" checked="checked"> 开业
                        <input name="regStatus" type="radio" value="1" > 停业
                        <input name="regStatus" type="radio" value="2" > 整顿
                        <input name="regStatus" type="radio" value="3" > 装修　
                        <input name="regStatus" type="radio" value="4" > 搬迁
                        <input name="regStatus" type="radio" value="5" > 吊销
                    </c:if>
                    <c:if test="${site.regStatus==2}">
                        <input name="regStatus" type="radio" value="0" > 开业
                        <input name="regStatus" type="radio" value="1" > 停业
                        <input name="regStatus" type="radio" value="2" checked="checked"> 整顿
                        <input name="regStatus" type="radio" value="3" > 装修　
                        <input name="regStatus" type="radio" value="4" > 搬迁
                        <input name="regStatus" type="radio" value="5" > 吊销
                    </c:if>
                    <c:if test="${site.regStatus==3}">
                        <input name="regStatus" type="radio" value="0" > 开业
                        <input name="regStatus" type="radio" value="1" > 停业
                        <input name="regStatus" type="radio" value="2" > 整顿
                        <input name="regStatus" type="radio" value="3" checked="checked"> 装修　
                        <input name="regStatus" type="radio" value="4" > 搬迁
                        <input name="regStatus" type="radio" value="5" > 吊销　
                    </c:if>
                    <c:if test="${site.regStatus==4}">
                        <input name="regStatus" type="radio" value="0" > 开业
                        <input name="regStatus" type="radio" value="1" > 停业
                        <input name="regStatus" type="radio" value="2" > 整顿
                        <input name="regStatus" type="radio" value="3" > 装修　
                        <input name="regStatus" type="radio" value="4" checked="checked"> 搬迁
                        <input name="regStatus" type="radio" value="5" > 吊销
                    </c:if>
                    <c:if test="${site.regStatus==5}">
                        <input name="regStatus" type="radio" value="0" > 开业
                        <input name="regStatus" type="radio" value="1" > 停业
                        <input name="regStatus" type="radio" value="2" > 整顿
                        <input name="regStatus" type="radio" value="3" > 装修　
                        <input name="regStatus" type="radio" value="4" > 搬迁
                        <input name="regStatus" type="radio" value="5"checked="checked" > 吊销
                    </c:if>
                    <c:if test="${site.regStatus!=1 and site.regStatus!=2 and site.regStatus!=3 and site.regStatus!=4 and site.regStatus!=5}">
                        <input name="regStatus" type="radio" value="0" > 开业
                        <input name="regStatus" type="radio" value="1" > 停业
                        <input name="regStatus" type="radio" value="2" > 整顿
                        <input name="regStatus" type="radio" value="3" > 装修　
                        <input name="regStatus" type="radio" value="4" > 搬迁
                        <input name="regStatus" type="radio" value="5" > 吊销
                    </c:if>
                </div>
            </div>
            <hr/><blockquote> <small>管理信息</small></blockquote>
            <div class="control-group">
                <label class="control-label">法人代表</label>
                <div class="controls">
                    <input type="text" name="legalRepresent" value="${site.legalRepresent}" placeholder="法人代表">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">终端总数</label>
                <div class="controls">
                    <input type="text" name="terminalNum" id="terminalNum" value="${site.terminalNum}" placeholder="终端总数" onblur="validateTerminalNum()">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">负责人</label>
                <div class="controls">
                    <input type="text" name="principal" value="${site.principal}" placeholder="负责人">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">负责人电话</label>
                <div class="controls">
                    <input type="text" name="principalTel" id="principalTel" value="${site.principalTel}" placeholder="负责人电话" onblur="validatePrincipalTel()">
                    *必填（合法联系电话）
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">网络管理员</label>
                <div class="controls">
                    <input type="text" name="adminstrator" value="${site.administrator}" placeholder="网络管理员">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">网络管理员电话</label>
                <div class="controls">
                    <input type="text" name="adminTel" value="${site.adminTel}" placeholder="网络管理员电话">
                </div>
            </div>
            <hr style="margin: 20px 10px;"/>
            <div style="margin: 0 auto 20px 150px;">
                <input type="button" class="btn btn-primary" onclick="save()" value="   确定   "/>
                <input type="button" class="btn btn-primary" onclick="returnList()" value="   返回   "/>
            </div>
        </div>
    </form>
    </div>
</div>
<script>
    function save(){
        if(validateSiteName() && validatePrincipalTel()){
            $('#f1').submit();
        }
    }

    function returnList(){
        window.location="/ipc/placeList/-1#_0";
    }
</script>
<script src="${ctx}/static/js/ipc_validate.js"></script>
</body>
</html>
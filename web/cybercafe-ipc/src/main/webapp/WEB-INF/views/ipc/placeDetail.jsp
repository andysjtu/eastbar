<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">营业场所详细信息</h3>
    </div>
    <div class="modal-body form-horizontal">
        <div class="mlr8" style="border: 1px solid #d4d4d4;">
            <hr/><blockquote> <small>基本信息</small></blockquote>
            <div class="control-group">
                <label class="control-label">场所编码:</label>
                <div class="controls">
                    <span id="siteCode"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">监管中心编码:</label>
                <div class="controls">
                    <span id="monitorCode"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">场所名称:</label>
                <div class="controls">
                    <span id="name"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">地址:</label>
                <div class="controls">
                    <span id="address"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">邮政编码:</label>
                <div class="controls">
                    <span id="zip"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">注册状态:</label>
                <div class="controls">
                    <span id="regStatus"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">营业时间版本:</label>
                <div class="controls">
                    <span id="hourVer"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">URL库版本:</label>
                <div class="controls">
                    <span id="urlVer"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">游戏库版本:</label>
                <div class="controls">
                    <span id="progVer"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">特殊人员版本号:</label>
                <div class="controls">
                    <span id="specialVer"></span>
                </div>
            </div>
            <hr/><blockquote> <small>管理信息</small></blockquote>
            <div class="control-group">
                <label class="control-label">场所法人代表:</label>
                <div class="controls">
                    <span id="legalRepresent"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">负责人:</label>
                <div class="controls">
                    <span id="principal"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">场所管理员:</label>
                <div class="controls">
                    <span id="administrator"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">负责人电话:</label>
                <div class="controls">
                    <span id="principalTel"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">场所电话:</label>
                <div class="controls">
                    <span id="adminTel"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">注册终端数:</label>
                <div class="controls">
                    <span id="terminalNum"></span>
                </div>
            </div>
            <hr/><blockquote> <small>场所实时信息</small></blockquote>
            <div class="control-group">
                <label class="control-label">连接终端数:</label>
                <div class="controls">
                    <span id="activeCustomerCount"></span>
                </div>
            </div>
            <%--<div class="control-group">--%>
                <%--<label class="control-label">活动顾客数:</label>--%>
                <%--<div class="controls">--%>
                    <%--<span id="activeCustomerCount"></span>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="control-group">
                <label class="control-label">告警总数:</label>
                <div class="controls">
                    <span id="totalAlarm"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">场所当前状态:</label>
                <div class="controls">
                    <span id="runStatus"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">处罚总数:</label>
                <div class="controls">
                    <span id="totalPunish"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-info" data-dismiss="modal" style="margin-right: 30px;" aria-hidden="true">关闭</button>
    </div>
</div>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">消息详细信息</h3>
    </div>
    <div class="modal-body form-horizontal">
        <div class="mlr8" style="border: 1px solid #d4d4d4;">
            <hr/><blockquote> <small>消息信息</small></blockquote>
            <div class="control-group">
                <label class="control-label">消息接收者:</label>
                <div class="controls">
                    <span id="monitorCode"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">过期日期:</label>
                <div class="controls">
                    <span id="noticeTime"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">消息标题:</label>
                <div class="controls">
                    <span id="noticeTitle"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">消息内容:</label>
                <div class="controls">
                    <span id="noticeContent"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-info" data-dismiss="modal" style="margin-right: 30px;" aria-hidden="true">关闭</button>
    </div>
</div>
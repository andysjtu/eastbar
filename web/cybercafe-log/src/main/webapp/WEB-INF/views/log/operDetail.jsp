<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">终端信息及远程控制</h3>
    </div>
    <div class="modal-body form-horizontal">
        <div class="mlr8" style="border: 1px solid #d4d4d4;">
            <hr/><blockquote> <small>终端信息</small></blockquote>
            <div class="control-group">
                <label class="control-label">场所编码:</label>
                <div class="controls">
                    <span id="siteCode"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">操作状态:</label>
                <div class="controls">
                    <span id="isSuccess"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">操作时间:</label>
                <div class="controls">
                    <span id="cmdTime"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">客户端ip:</label>
                <div class="controls">
                    <span id="hostIp"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-info" data-dismiss="modal" style="margin-right: 30px;" aria-hidden="true">关闭</button>
    </div>
</div>
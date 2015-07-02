<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">角色信息</h3>
    </div>
    <div class="modal-body form-horizontal">
        <div class="mlr8" style="border: 1px solid #d4d4d4;">
            <hr/><blockquote> <small>基本信息</small></blockquote>
            <div class="control-group">
                <label class="control-label">角色名</label>
                <div class="controls">
                    <span id="commonName"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">角色描述</label>
                <div class="controls">
                    <span id="roleDesc"></span>
                </div>
            </div>
            <hr/><blockquote> <small>权限信息</small></blockquote>
            <div class="control-group">
                <label class="control-label">权限</label>
                <div class="controls permissions">
                    <span></span>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-info" data-dismiss="modal" style="margin-right: 30px;" aria-hidden="true">关闭</button>
    </div>

</div>

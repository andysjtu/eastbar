<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">账号信息</h3>
    </div>
    <div class="modal-body form-horizontal">
        <div class="mlr8" style="border: 1px solid #d4d4d4;">
            <hr/><blockquote> <small>基本信息</small></blockquote>
            <div class="control-group">
                <label class="control-label">账号</label>
                <div class="controls">
                    <span id="loginName"></span>
                </div>
            </div>
            <hr/><blockquote> <small>用户信息</small></blockquote>
            <div class="control-group">
                <label class="control-label">姓名</label>
                <div class="controls">
                    <span id="commonName"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">身份证</label>
                <div class="controls">
                    <span id="idCard"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">手机</label>
                <div class="controls">
                    <span id="mobile"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">邮箱</label>
                <div class="controls">
                    <span id="email">11</span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态</label>
                <div class="controls">
                  <span id="status" ></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">角色</label>
                <div class="controls roles">
                    <span></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">最后登录时间</label>
                <div class="controls">
                    <span id="lastlogin"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-info" data-dismiss="modal" style="margin-right: 30px;" aria-hidden="true">关闭</button>
    </div>

</div>

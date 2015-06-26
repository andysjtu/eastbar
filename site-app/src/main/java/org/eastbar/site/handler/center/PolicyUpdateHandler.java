package org.eastbar.site.handler.center;

import com.google.common.base.Charsets;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.JsonUtil;
import org.eastbar.codec.PolicyVersion;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.policy.PolicyUpdateMsg;
import org.eastbar.site.policy.PolicySaver;
import org.eastbar.site.policy.UrlPolicyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 收到策略更新指令，更新策略
 * Created by AndySJTU on 2015/5/27.
 */
public class PolicyUpdateHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(PolicyUpdateHandler.class);

    private final PolicySaver policySaver;

    public PolicyUpdateHandler(PolicySaver policySaver) {
        this.policySaver = policySaver;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.UPDATE_URL_POLICY.shortValue()) {
            PolicyUpdateMsg updateMsg = new PolicyUpdateMsg(msg);
            byte[] content = updateMsg.getContent();
            policySaver.saveUrlPolicy(content, ctx.channel());

        } else if (type == SiteMsgType.UPDATE_PROG_POLICY.shortValue()) {
            PolicyUpdateMsg updateMsg = new PolicyUpdateMsg(msg);
            byte[] content = updateMsg.getContent();
            policySaver.saveProgPolicy(content, ctx.channel());

        } else if (type == SiteMsgType.UPDATE_KW_POLICY.shortValue()) {
            PolicyUpdateMsg updateMsg = new PolicyUpdateMsg(msg);
            byte[] content = updateMsg.getContent();
            policySaver.saveKwPolicy(content,ctx.channel());

        } else if (type == SiteMsgType.UPDATE_SP_POLICY.shortValue()) {
            PolicyUpdateMsg updateMsg = new PolicyUpdateMsg(msg);
            byte[] content = updateMsg.getContent();
            policySaver.saveSpPolicy(content,ctx.channel());
        } else {
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }
}

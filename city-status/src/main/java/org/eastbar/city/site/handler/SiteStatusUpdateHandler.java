package org.eastbar.city.site.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.city.CityCenter;
import org.eastbar.codec.*;
import org.eastbar.codec.policy.PolicyUpdateRespMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/5/30.
 */
public class SiteStatusUpdateHandler extends SimpleChannelInboundHandler<SocketMsg> {

    private final CityCenter center;
    public final static Logger logger = LoggerFactory.getLogger(SiteStatusUpdateHandler.class);

    public SiteStatusUpdateHandler(CityCenter center) {
        this.center = center;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        logger.info("收到消息类似是:{}",type);
        if (type == SiteMsgType.TERM_STATUS.shortValue()) {
            TermReportMsg reportMsg = new TermReportMsg(msg);
            center.updateTermStatus(reportMsg.getReport());
        } else if (type == SiteMsgType.POLICY_STATUS.shortValue()) {
            SiteReportMsg siteReportMsg = new SiteReportMsg(msg);
            center.updateSitePolicyStatus(siteReportMsg.getReport());
        }else if(type==SiteMsgType.UPDATE_POLICY_RESP.shortValue()){
            logger.info("收到策略更新的响应");
            PolicyUpdateRespMsg respMsg = new PolicyUpdateRespMsg(msg);
            short status = respMsg.getStatus();
            if(status==1){
                String siteCode =respMsg.getSiteCode();
                int curVersionNum = respMsg.getCurVersion();
                short policyType =respMsg.getPolicyType();
                center.updateSitePolicyVersion(siteCode,policyType,curVersionNum);
            }
        }
        else {
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }
}

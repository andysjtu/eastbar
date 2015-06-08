package org.eastbar.site.policy;

import io.netty.channel.Channel;
import org.eastbar.codec.JsonUtil;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.policy.PolicyUpdateRespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AndySJTU on 2015/6/8.
 */
@Component
public class PolicySaver {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);
    @Autowired
    private PolicyService service;

    public static short SUC=(short)0;
    public static short FAIL=(short)1;

    public void saveUrlPolicy(final byte[] content,final Channel channel){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    saveUrlPolicyDo(content);
                    PolicyUpdateRespMsg msg = new PolicyUpdateRespMsg(SiteMsgType.UPDATE_URL_POLICY.shortValue(),SUC);
                    channel.writeAndFlush(msg);
                }catch (Throwable t){
                    t.printStackTrace();
                }
            }
        });
    }

    private void saveUrlPolicyDo(byte[] content) {
        UrlPolicyObject urlPolicyObject = JsonUtil.fromJson(UrlPolicyObject.class, content);
        int verNum = urlPolicyObject.getVerNum();
        service.updateUrlPolicy(verNum, urlPolicyObject.getAdd(), urlPolicyObject.getEdit(), urlPolicyObject.getRemove());
    }

    public void saveProgPolicy(final byte[] content, final Channel channel){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    saveProgPolicyDo(content);
                    PolicyUpdateRespMsg msg = new PolicyUpdateRespMsg(SiteMsgType.UPDATE_PROG_POLICY.shortValue(),SUC);
                    channel.writeAndFlush(msg);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }

    private void saveProgPolicyDo(byte[] content) {
        ProgPolicyObject progPolicyObject = JsonUtil.fromJson(ProgPolicyObject.class, content);
        int verNum = progPolicyObject.getVerNum();
        service.updateProgPolicy(verNum, progPolicyObject.getAdd(), progPolicyObject.getEdit(), progPolicyObject.getRemove());
    }

    public void saveKwPolicy(final byte[] content, final Channel channel){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    saveKwPolicyDo(content);
                    PolicyUpdateRespMsg msg = new PolicyUpdateRespMsg(SiteMsgType.UPDATE_KW_POLICY.shortValue(),SUC);
                    channel.writeAndFlush(msg);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }

    private void saveKwPolicyDo(byte[] content) {
        KwPolicyObject policyObject = JsonUtil.fromJson(KwPolicyObject.class, content);
        int verNum = policyObject.getVerNum();
        service.updateKwPolicy(verNum, policyObject.getAdd(), policyObject.getEdit(), policyObject.getRemove());
    }

    public void saveSpPolicy(final byte[] content, final Channel channel){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    saveSpPolicyDo(content);
                    PolicyUpdateRespMsg msg = new PolicyUpdateRespMsg(SiteMsgType.UPDATE_SP_POLICY.shortValue(),SUC);
                    channel.writeAndFlush(msg);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }

    private void saveSpPolicyDo(byte[] content) {
        SpersonPolicyObject policyObject = JsonUtil.fromJson(SpersonPolicyObject.class, content);
        int verNum = policyObject.getVerNum();
        service.updateSpPolicy(verNum, policyObject.getAdd(), policyObject.getEdit(), policyObject.getRemove());
    }

    private void sendSuccessResp(short type,short suc){



    }
}

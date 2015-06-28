package org.eastbar.site.policy;

import io.netty.channel.Channel;
import org.eastbar.codec.JsonUtil;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.policy.PolicyUpdateRespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${sitecode}")
    private String siteCode;

    public static short SUC=(short)0;
    public static short FAIL=(short)1;

    public void saveUrlPolicy(final byte[] content,final Channel channel){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    int curVersion=saveUrlPolicyDo(content);
                    PolicyUpdateRespMsg msg = new PolicyUpdateRespMsg(SiteMsgType.UPDATE_URL_POLICY.shortValue(),SUC,curVersion,siteCode);
                    channel.writeAndFlush(msg);
                }catch (Throwable t){
                    t.printStackTrace();
                }
            }
        });
    }

    private int saveUrlPolicyDo(byte[] content) {
        UrlPolicyObject urlPolicyObject = JsonUtil.fromJson(UrlPolicyObject.class, content);
        int verNum = urlPolicyObject.getVerNum();
        service.updateUrlPolicy(verNum, urlPolicyObject.getAdd(), urlPolicyObject.getEdit(), urlPolicyObject.getRemove());
        return verNum;
    }

    public void saveProgPolicy(final byte[] content, final Channel channel){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    int curVersion = saveProgPolicyDo(content);
                    PolicyUpdateRespMsg msg = new PolicyUpdateRespMsg(SiteMsgType.UPDATE_PROG_POLICY.shortValue(),SUC,curVersion,siteCode);
                    channel.writeAndFlush(msg);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }

    private int saveProgPolicyDo(byte[] content) {
        ProgPolicyObject progPolicyObject = JsonUtil.fromJson(ProgPolicyObject.class, content);
        int verNum = progPolicyObject.getVerNum();
        service.updateProgPolicy(verNum, progPolicyObject.getAdd(), progPolicyObject.getEdit(), progPolicyObject.getRemove());
        return verNum;
    }

    public void saveKwPolicy(final byte[] content, final Channel channel){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    int curVersion = saveKwPolicyDo(content);
                    PolicyUpdateRespMsg msg = new PolicyUpdateRespMsg(SiteMsgType.UPDATE_KW_POLICY.shortValue(),SUC,curVersion,siteCode);
                    channel.writeAndFlush(msg);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }

    private int saveKwPolicyDo(byte[] content) {
        KwPolicyObject policyObject = JsonUtil.fromJson(KwPolicyObject.class, content);
        int verNum = policyObject.getVerNum();
        service.updateKwPolicy(verNum, policyObject.getAdd(), policyObject.getEdit(), policyObject.getRemove());
        return verNum;
    }

    public void saveSpPolicy(final byte[] content, final Channel channel){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    int curVersion = saveSpPolicyDo(content);
                    PolicyUpdateRespMsg msg = new PolicyUpdateRespMsg(SiteMsgType.UPDATE_SP_POLICY.shortValue(),SUC,curVersion,siteCode);
                    channel.writeAndFlush(msg);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }

    private int saveSpPolicyDo(byte[] content) {
        SpersonPolicyObject policyObject = JsonUtil.fromJson(SpersonPolicyObject.class, content);
        int verNum = policyObject.getVerNum();
        service.updateSpPolicy(verNum, policyObject.getAdd(), policyObject.getEdit(), policyObject.getRemove());
        return verNum;
    }

}

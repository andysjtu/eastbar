package org.eastbar.site.policy;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;

/**
 * Created by andysjtu on 2015/5/11.
 */
@Component
public class PolicyManager {
    private List<BanUrl> urlList = Lists.newArrayList();

    private List<KeyWord> keywords = Lists.newArrayList();

    private List<BanProg> banProgs = Lists.newArrayList();

    @Autowired
    private KeyWordDao kwDao;
    @Autowired
    private BanProgDao banProgDao;
    @Autowired
    private BanUrlDao banUrlDao;

    @Autowired
    private PolicyVersionDao versionDao;

    public SitePolicyVersion getPolicyVersion() {
        Iterator<SitePolicyVersion> it = versionDao.findAll().iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return new SitePolicyVersion();
    }


    public String getBanUrlString() {
        StringBuilder builder = new StringBuilder();
        for (BanUrl url : urlList) {
            builder.append(url.getUrlType());
            builder.append(" : ");
            builder.append(url.getUrlValue());
            builder.append(" : ");
            builder.append(url.getAlarmType());
            builder.append(" : ");
            builder.append(url.getAlarmRank());
            builder.append(" : ");
            builder.append(url.isBlock() ? "1" : "0");
            builder.append("\n");
        }

        return builder.toString();
    }

    @PostConstruct
    public void init() {

        BanUrl url = new BanUrl();
        url.setUrlType(1);
        url.setUrlValue("www.sina.com");
        url.setAlarmType(1);
        url.setAlarmRank(2);
        url.setIsBlock(false);
        urlList.add(url);
        KeyWord word = new KeyWord();
        word.setKeyword("中文测试");
        word.setAlarmRank(1);
        word.setAlarmType(1);
        word.setIsBlock(true);
        keywords.add(word);
    }

    public String getBanProgString() {
        StringBuilder builder = new StringBuilder();
        for (BanProg prog : banProgs) {
            builder.append(prog.getProgName());
            builder.append(" : ");
            builder.append(prog.getProgressName());
            builder.append(" : ");
            builder.append(prog.getFeatureCode());
            builder.append(" : ");
            builder.append(prog.getAlarmType());
            builder.append(" : ");
            builder.append(prog.getAlarmRank());
            builder.append(" : ");
            builder.append(prog.isBlock() ? "1" : "0");
            builder.append("\n");
        }

        return builder.toString();
    }

    public String getBanKeywordString() {
        StringBuilder builder = new StringBuilder();
        for (KeyWord kw : keywords) {
            builder.append("\"");
            builder.append(kw.getKeyword());
            builder.append("\"");
            builder.append(" : ");
            builder.append(kw.getAlarmType());
            builder.append(" : ");
            builder.append(kw.getAlarmRank());
            builder.append(" : ");
            builder.append(kw.isBlock() ? "1" : "0");
            builder.append("\n");
        }

        return builder.toString();

    }

    public static void main(String[] args) {
        PolicyManager manager = new PolicyManager();
        manager.init();
        System.out.println(manager.getBanKeywordString());
    }
}

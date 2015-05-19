package org.eastbar.site.policy;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
        for (int i = 0; i < 100; i++) {
            BanUrl url = new BanUrl();
            url.setUrlType(1);
            url.setUrlValue("www.sina.com");
            url.setAlarmType(1);
            url.setAlarmRank(2);
            url.setIsBlock(false);
            urlList.add(url);
        }
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
            builder.append(kw.getKeyword());
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
}

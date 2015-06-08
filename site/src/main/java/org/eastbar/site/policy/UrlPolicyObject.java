package org.eastbar.site.policy;

import com.google.common.collect.Lists;
import org.eastbar.site.policy.entity.BanUrl;
import org.eastbar.site.policy.entity.KeyWord;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/8.
 */
public class UrlPolicyObject {
    private int verNum;
    private List<BanUrl> add = Lists.newArrayList();
    private List<BanUrl> edit = Lists.newArrayList();
    private List<BanUrl> remove = Lists.newArrayList();

    public List<BanUrl> getAdd() {
        return add;
    }

    public void setAdd(List<BanUrl> add) {
        this.add = add;
    }

    public List<BanUrl> getEdit() {
        return edit;
    }

    public void setEdit(List<BanUrl> edit) {
        this.edit = edit;
    }

    public List<BanUrl> getRemove() {
        return remove;
    }

    public void setRemove(List<BanUrl> remove) {
        this.remove = remove;
    }

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }

    @Override
    public String toString() {
        return "UrlPolicyObject{" +
                "add=" + add +
                ", verNum=" + verNum +
                ", edit=" + edit +
                ", remove=" + remove +
                '}';
    }
}

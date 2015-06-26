package org.eastbar.site.policy;

import com.google.common.collect.Lists;
import org.eastbar.site.policy.entity.BanProg;
import org.eastbar.site.policy.entity.BanUrl;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/8.
 */
public class ProgPolicyObject {
    private int verNum;
    private List<BanProg> add = Lists.newArrayList();
    private List<BanProg> edit = Lists.newArrayList();
    private List<BanProg> remove = Lists.newArrayList();

    public List<BanProg> getAdd() {
        return add;
    }

    public void setAdd(List<BanProg> add) {
        this.add = add;
    }

    public List<BanProg> getEdit() {
        return edit;
    }

    public void setEdit(List<BanProg> edit) {
        this.edit = edit;
    }

    public List<BanProg> getRemove() {
        return remove;
    }

    public void setRemove(List<BanProg> remove) {
        this.remove = remove;
    }

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }
}

package org.eastbar.site.policy;

import com.google.common.collect.Lists;
import org.eastbar.site.policy.entity.BanUrl;
import org.eastbar.site.policy.entity.SpecialPerson;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/8.
 */
public class SpersonPolicyObject {
    private int verNum;
    private List<SpecialPerson> add = Lists.newArrayList();
    private List<SpecialPerson> edit = Lists.newArrayList();
    private List<SpecialPerson> remove = Lists.newArrayList();

    public List<SpecialPerson> getAdd() {
        return add;
    }

    public void setAdd(List<SpecialPerson> add) {
        this.add = add;
    }

    public List<SpecialPerson> getEdit() {
        return edit;
    }

    public void setEdit(List<SpecialPerson> edit) {
        this.edit = edit;
    }

    public List<SpecialPerson> getRemove() {
        return remove;
    }

    public void setRemove(List<SpecialPerson> remove) {
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
        return "SpersonPolicyObject{" +
                "add=" + add +
                ", verNum=" + verNum +
                ", edit=" + edit +
                ", remove=" + remove +
                '}';
    }
}

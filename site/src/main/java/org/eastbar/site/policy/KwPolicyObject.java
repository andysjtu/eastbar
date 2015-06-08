package org.eastbar.site.policy;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.eastbar.codec.JsonUtil;
import org.eastbar.site.policy.entity.KeyWord;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/8.
 */
public class KwPolicyObject {
    private int verNum;
    private List<KeyWord> add = Lists.newArrayList();
    private List<KeyWord> edit = Lists.newArrayList();
    private List<KeyWord> remove = Lists.newArrayList();

    public List<KeyWord> getAdd() {
        return add;
    }

    public void setAdd(List<KeyWord> add) {
        this.add = add;
    }

    public List<KeyWord> getEdit() {
        return edit;
    }

    public void setEdit(List<KeyWord> edit) {
        this.edit = edit;
    }

    public List<KeyWord> getRemove() {
        return remove;
    }

    public void setRemove(List<KeyWord> remove) {
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
        return "PolicyObject{" +
                "add=" + add +
                ", verNum=" + verNum +
                ", edit=" + edit +
                ", remove=" + remove +
                '}';
    }

    public static void main(String[] args) {
        String content = "{\"verNum\":9,\"add\":[{\"id\":12,\"keyword\":\"暴力\",\"alarmType\":5,\"alarmRank\":1,\"isBlock\":1,\"verNum\":9},{\"id\":14,\"keyword\":\"泛滥\",\"alarmType\":5,\"alarmRank\":1,\"isBlock\":1,\"verNum\":9},{\"id\":16,\"keyword\":\"泛滥\",\"alarmType\":5,\"alarmRank\":1,\"isBlock\":1,\"verNum\":9}],\"edit\":[],\"remove\":[]}\n";
        KwPolicyObject po = JsonUtil.fromJson(KwPolicyObject.class, content.getBytes(Charsets.UTF_8));
        System.out.println(po);
    }
}

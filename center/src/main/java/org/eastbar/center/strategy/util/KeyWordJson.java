/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.util;

import org.eastbar.center.strategy.service.biz.KeyWordBO;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年05月30
 * @time 下午3:59
 * @description :
 */
public class KeyWordJson {

    private int verNum;
    private List<KeyWordBO> add;
    private List<KeyWordBO> edit;
    private List<KeyWordBO> remove;

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }

    public List<KeyWordBO> getAdd() {
        return add;
    }

    public void setAdd(List<KeyWordBO> add) {
        this.add = add;
    }

    public List<KeyWordBO> getEdit() {
        return edit;
    }

    public void setEdit(List<KeyWordBO> edit) {
        this.edit = edit;
    }

    public List<KeyWordBO> getRemove() {
        return remove;
    }

    public void setRemove(List<KeyWordBO> remove) {
        this.remove = remove;
    }
}

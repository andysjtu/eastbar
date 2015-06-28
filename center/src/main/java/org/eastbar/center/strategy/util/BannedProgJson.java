/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.util;

import org.eastbar.center.strategy.service.biz.BannedProgBO;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年05月30
 * @time 下午3:55
 * @description :
 */
public class BannedProgJson {

    private int verNum;
    private List<BannedProgBO> add;
    private List<BannedProgBO> edit;
    private List<BannedProgBO> remove;

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }

    public List<BannedProgBO> getAdd() {
        return add;
    }

    public void setAdd(List<BannedProgBO> add) {
        this.add = add;
    }

    public List<BannedProgBO> getEdit() {
        return edit;
    }

    public void setEdit(List<BannedProgBO> edit) {
        this.edit = edit;
    }

    public List<BannedProgBO> getRemove() {
        return remove;
    }

    public void setRemove(List<BannedProgBO> remove) {
        this.remove = remove;
    }
}

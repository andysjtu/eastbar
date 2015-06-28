/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.util;


import org.eastbar.center.strategy.service.biz.BannedUrlBO;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年05月30
 * @time 下午3:56
 * @description :
 */
public class BannedUrlJson {

    private int verNum;
    private List<BannedUrlBO> add;
    private List<BannedUrlBO> edit;
    private List<BannedUrlBO> remove;

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }

    public List<BannedUrlBO> getAdd() {
        return add;
    }

    public void setAdd(List<BannedUrlBO> add) {
        this.add = add;
    }

    public List<BannedUrlBO> getEdit() {
        return edit;
    }

    public void setEdit(List<BannedUrlBO> edit) {
        this.edit = edit;
    }

    public List<BannedUrlBO> getRemove() {
        return remove;
    }

    public void setRemove(List<BannedUrlBO> remove) {
        this.remove = remove;
    }
}

/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.util;

import org.eastbar.centers.strategy.service.biz.SpecialCustomerBO;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年05月30
 * @time 下午3:57
 * @description :
 */
public class SpecialCustomerJson {

    private int verNum;
    private List<SpecialCustomerBO> add;
    private List<SpecialCustomerBO> edit;
    private List<SpecialCustomerBO> remove;

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }

    public List<SpecialCustomerBO> getAdd() {
        return add;
    }

    public void setAdd(List<SpecialCustomerBO> add) {
        this.add = add;
    }

    public List<SpecialCustomerBO> getEdit() {
        return edit;
    }

    public void setEdit(List<SpecialCustomerBO> edit) {
        this.edit = edit;
    }

    public List<SpecialCustomerBO> getRemove() {
        return remove;
    }

    public void setRemove(List<SpecialCustomerBO> remove) {
        this.remove = remove;
    }
}

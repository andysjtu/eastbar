/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.biz;

import org.eastbar.web.InitSearchTime;

/**
 * @author cindy-jia
 * @date 2014年12月09
 * @time 下午3:22
 * @description :
 */
public class SearchBO {

    private Integer byear;
    private Integer eyear;
    private Integer bmyear;
    private Integer emyear;
    private String bmonth;
    private String emonth;
    private String btime;
    private String etime;
    private String type;
    private String objectType;//统计对象
    private String fmonitorCode;//一级监管中心
    private String smonitorCode;//二级监管中心
    private String siteCode;

    private String objectView;
    private String codeView;

    {
        String[] times = new String[2];
        InitSearchTime.init(times);
        this.setBtime(times[0]);
        this.setEtime(times[1]);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getByear() {
        return byear;
    }

    public void setByear(Integer byear) {
        this.byear = byear;
    }

    public Integer getEyear() {
        return eyear;
    }

    public void setEyear(Integer eyear) {
        this.eyear = eyear;
    }

    public String getBmonth() {
        return bmonth;
    }

    public void setBmonth(String bmonth) {
        this.bmonth = bmonth;
    }

    public String getEmonth() {
        return emonth;
    }

    public void setEmonth(String emonth) {
        this.emonth = emonth;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public Integer getBmyear() {
        return bmyear;
    }

    public void setBmyear(Integer bmyear) {
        this.bmyear = bmyear;
    }

    public Integer getEmyear() {
        return emyear;
    }

    public void setEmyear(Integer emyear) {
        this.emyear = emyear;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getFmonitorCode() {
        return fmonitorCode;
    }

    public void setFmonitorCode(String fmonitorCode) {
        this.fmonitorCode = fmonitorCode;
    }

    public String getSmonitorCode() {
        return smonitorCode;
    }

    public void setSmonitorCode(String smonitorCode) {
        this.smonitorCode = smonitorCode;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getObjectView() {
        return objectView;
    }

    public void setObjectView(String objectView) {
        this.objectView = objectView;
    }

    public String getCodeView() {
        return codeView;
    }

    public void setCodeView(String codeView) {
        this.codeView = codeView;
    }
}

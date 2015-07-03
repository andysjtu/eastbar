/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web;

import org.eastbar.web.pagehelper.Page;

import java.util.List;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月21
 * @time 下午2:31
 * @description : Service层用于封装分页结果
 */
public class PageInfo<T> {

    public static PageInfo clone(List list) {
        PageInfo pi = new PageInfo();
        if (list instanceof Page) {
            Page pl = (Page) list;
            pi.setPage(pl.getPageNum());
            pi.setRows(pl.getPageSize());
            pi.setTotal(pl.getTotal());
            pi.setListing(pl.getResult());
        }
        return pi;
    }
    public static PageInfo clone(List list,List live) {
        PageInfo pi = new PageInfo();
        if (list instanceof Page) {
            Page pl = (Page) list;
            pi.setPage(pl.getPageNum());
            pi.setRows(pl.getPageSize());
            pi.setTotal(pl.getTotal());
            pi.setListing(live);
        }
        return pi;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getListing() {
        return listing;
    }

    public void setListing(List listing) {
        this.listing = listing;
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

    private Integer page, rows;
    private Long total;
    private List listing;

    private String btime,etime;
    {
        String[] times = new String[2];
        InitSearchTime.init(times);
        this.setBtime(times[0]);
        this.setEtime(times[1]);
    }
}

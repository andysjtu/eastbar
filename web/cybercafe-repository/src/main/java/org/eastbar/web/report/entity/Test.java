/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.entity;

import java.io.Serializable;

/**
 * @author cindy-jia
 * @date 2015年04月22
 * @time 上午10:30
 * @description :
 */
public class Test implements Serializable {

    private Integer id;
    private String name;
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

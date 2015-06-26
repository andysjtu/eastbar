/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.statusMachine.core;

/**
 * @author C.lins@aliyun.com
 * @date 2015年04月16
 * @time 下午2:53
 * @description :
 */
public class Offset {

    private int[] run = {0,0,0,0};
    private int open = 0;
    private int total = 0;

    public int[] getRun() {
        return run;
    }

    public void setRun(int[] run) {
        this.run = run;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

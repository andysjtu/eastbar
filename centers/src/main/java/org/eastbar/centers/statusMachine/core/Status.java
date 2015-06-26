/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.statusMachine.core;

/**
 * @author C.lins@aliyun.com
 * @date 2015年04月14
 * @time 上午10:50
 * @description :
 */
public enum Status {

    ONLINE(new int[]{1,0,0,0}), // 使用中(默认)
    FREE(new int[]{0,1,0,0}), //空闲
    OFFLINE(new int[]{0,0,1,0}), //关机
    UNKNOWN(new int[]{0,0,0,1}); //未知

    private int[] value = null;

    private Status(int[] value){
        this.value = value;
    }
    public int[] getValue(){
        return value;
    }

    public int[] countOffset(Status status){
        return Count.offset(value,status.getValue());
    }

}

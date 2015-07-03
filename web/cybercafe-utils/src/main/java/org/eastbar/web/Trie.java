/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月14
 * @time 下午2:52
 * @description : 字典树
 */
public enum Trie {
    UNKNOWN(-1,"未知"),
    SUCCEED(1,"成功"),
    FAILED(2,"失败"),
    /**
     * 用户状态
     */
    LOCK(0,"锁定"),
    UNLOCK(1,"未锁定");


    public static Trie byCode(Integer code) {
        Trie t = UNKNOWN;
        for (Trie element : Trie.values()) {
            if (element.getCode().equals(code)) {
                t = element;
                break;
            }
        }
        return t;
    }

    private Integer id;
    private Integer code;
    private String name;
    private String[] attr;

    private Trie(Integer code, String name, String...attr){
        this.code = code;
        this.name = name;
        this.attr = attr;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String[] getAttr() {
        return attr;
    }

}

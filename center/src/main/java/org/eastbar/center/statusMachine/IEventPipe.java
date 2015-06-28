/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.statusMachine;

import org.eastbar.center.statusMachine.core.Event;

/**
 * @author C.lins@aliyun.com
 * @date 2015年01月22
 * @time 下午2:55
 * @description :
 */
public interface IEventPipe {

    public boolean addEvents(Event event);
    public Event getEvents();
}

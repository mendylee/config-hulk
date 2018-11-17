package com.xiangrikui.hulk.client.registry;

/**
 * 创建时间：2017年3月13日
 * <p>修改时间：2017年3月13日
 * <p>类说明：节点变更通知监听器
 * 
 * @author jerry
 * @version 1.0
 */
public interface NoticeListener {
    
     void notice(NoticeEvent event,String key,Object value);
}

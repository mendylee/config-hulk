package com.xiangrikui.hulk.core.zookeeper;

/**
 * 创建时间：2017年3月7日
 * <p>修改时间：2017年3月7日
 * <p>类说明：子节点变更Listener监听
 * 
 * @author jerry
 * @version 1.0
 */
public interface ChildListener {
    
    void childChanged(String changeEvent,String path,Object data);
}

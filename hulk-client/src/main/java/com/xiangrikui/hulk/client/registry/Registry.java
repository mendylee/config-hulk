package com.xiangrikui.hulk.client.registry;

import com.xiangrikui.hulk.client.registry.node.AppNode;

/**
 * 创建时间：2017年3月9日
 * <p>修改时间：2017年3月9日
 * <p>类说明：应用节点注册接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface Registry {

    /**
     * 应用节点注册
     */
    void register(AppNode app);
    
    /**
     * 应用 取消注册
     */
    void unregister(AppNode app);
    
    /**
     * 监听节点
     */
    void subscribe(AppNode app,NoticeListener listener);
    
    /**
     * 取消节点监听
     */
    void unsubscribe(AppNode app,NoticeListener listener);
    
    /**
     * 销毁
     */
    void destory();
}

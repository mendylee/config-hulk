package com.xiangrikui.hulk.client;

import com.xiangrikui.hulk.client.registry.NoticeEvent;

/**
 * 创建时间：2017年3月15日
 * <p>修改时间：2017年3月15日
 * <p>类说明：配置变更监听器
 * 
 * @author jerry
 * @version 1.0
 */
public interface ConfigChangeLisener {

    /**
     * 配置变更
     * @param changeEvent
     * @param key  
     * @param value
     */
    public void configChange(NoticeEvent changeEvent,String key, Object value);
    
    
}

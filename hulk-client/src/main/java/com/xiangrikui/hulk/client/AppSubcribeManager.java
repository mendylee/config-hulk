package com.xiangrikui.hulk.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.client.common.HulkAppContext;
import com.xiangrikui.hulk.client.conf.handler.HulkConfigHandler;
import com.xiangrikui.hulk.client.conf.handler.impl.HulkItemHandlerImpl;
import com.xiangrikui.hulk.client.registry.NoticeEvent;

/**
 * 创建时间：2017年3月15日
 * <p>修改时间：2017年3月15日
 * <p>类说明：配置中心应用订阅管理服务
 * 
 * @author jerry
 * @version 1.0
 */
public class AppSubcribeManager implements ConfigChangeLisener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSubcribeManager.class);
    
    private HulkConfigHandler configHandler;
    
    private HulkAppContext context;
    
    public AppSubcribeManager(HulkAppContext context){
        this.context = context;
        this.configHandler = new HulkItemHandlerImpl(context);
    }
    
    public void configChange(NoticeEvent changeEvent, String key,Object value) {
        
        LOGGER.info("change event:{},app:{}",changeEvent.name(),key);
        switch (changeEvent) {
            case CHILD_ADD:
                configHandler.writeConfig(key, value.toString());
                break;
            case CHILD_UPDATE:
                configHandler.updateConfig(key, value.toString());
                break;
            default:
                break;
        }
       
        
    }

}

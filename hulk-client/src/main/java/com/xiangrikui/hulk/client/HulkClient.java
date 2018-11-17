package com.xiangrikui.hulk.client;

import com.xiangrikui.hulk.client.conf.ConfigManager;
import com.xiangrikui.hulk.client.conf.DefaultConfigManager;
import com.xiangrikui.hulk.client.conf.model.HulkConfigItemModel;

/**
 * 创建时间：2017年3月8日
 * <p>修改时间：2017年3月8日
 * <p>类说明：Hulk配置中心客户端
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkClient extends HulkClientBootStrap{ 
    
    private volatile ConfigManager configManager;
    
    /**
     * 获取配置管理器
     * @return
     */
    private ConfigManager getConfigManager(){
        if(configManager == null){
            synchronized (this) {
                if(configManager == null){
                    configManager = new DefaultConfigManager(appConfig);
                }
            }
        }
        return configManager;
    }
    
    /**
     * 获取配置信息
     * @param 配置项
     * @return
     */
    public HulkConfigItemModel getConfig(String key){
        return getConfigManager().getConfig(key);
    }
   
}

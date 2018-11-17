package com.xiangrikui.hulk.client.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.client.common.constans.Constants;
import com.xiangrikui.hulk.client.conf.model.HulkConfigFile;
import com.xiangrikui.hulk.client.conf.model.HulkConfigItemModel;
import com.xiangrikui.hulk.client.conf.store.HulkConfigCache;
import com.xiangrikui.hulk.client.registry.node.NodeUtils;
import com.xiangrikui.hulk.core.context.AppConfig;

/**
 * 创建时间：2017年3月17日
 * <p>修改时间：2017年3月17日
 * <p>类说明：配置中心默认配置管理器
 * 
 * @author jerry
 * @version 1.0
 */
public class DefaultConfigManager implements ConfigManager {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfigManager.class);
    
    /**
     * 应用通用配置信息
     */
    private AppConfig appConfig;
    
    public DefaultConfigManager(AppConfig appConfig){
        this.appConfig = appConfig;
    }
    
    @Override
    public HulkConfigItemModel getConfig(String key) {
        String nodeFullPath = NodeUtils.getNodeFullPath(appConfig);
        HulkConfigItemModel configItem = HulkConfigCache.getInstance().getConfigItem().get(nodeFullPath+Constants.CONFIG_PATH_SPLIT_TOKEN+key);
        if(configItem == null){
            LOGGER.info("cannot find {} in HulkConfigCache",key);
            return null;
        }
        return configItem;
    }

    
    @Override
    public HulkConfigFile getConfigFile(String fileName) {
        return null;
    }
    
}

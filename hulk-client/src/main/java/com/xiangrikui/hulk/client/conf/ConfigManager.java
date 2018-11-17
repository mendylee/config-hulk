package com.xiangrikui.hulk.client.conf;

import com.xiangrikui.hulk.client.conf.model.HulkConfigFile;
import com.xiangrikui.hulk.client.conf.model.HulkConfigItemModel;

/**
 * 创建时间：2017年3月17日
 * <p>修改时间：2017年3月17日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public interface ConfigManager {
    
    /**
     * 获取配置项
     * @param key
     * @return
     */
    public HulkConfigItemModel getConfig(String key);
    
    /**
     * 获取配置文件
     * @param fileName
     * @return
     */
    public HulkConfigFile getConfigFile(String fileName);
}

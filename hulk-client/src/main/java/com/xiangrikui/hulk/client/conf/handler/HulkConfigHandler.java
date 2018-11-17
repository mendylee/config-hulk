package com.xiangrikui.hulk.client.conf.handler;

/**
 * 创建时间：2017年3月16日
 * <p>修改时间：2017年3月16日
 * <p>类说明：Hulk配置中心配置处理器接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface HulkConfigHandler {
    
    /**
     * 写入一个配置
     * @param 配置key
     * @param 配置value
     */
    void writeConfig(String key,String value);
    
    /**
     * 更新一个配置
     * @param 配置值
     * @param 配置value
     */
    void updateConfig(String key,String value);
}

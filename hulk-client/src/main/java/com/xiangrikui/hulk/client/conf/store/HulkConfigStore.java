package com.xiangrikui.hulk.client.conf.store;

import java.util.List;
import java.util.Set;

import com.xiangrikui.hulk.client.conf.model.HulkConfigModel;

/**
 * 创建时间：2017年3月16日
 * <p>修改时间：2017年3月16日
 * <p>类说明：Hulk配置中心配置存储服务接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface HulkConfigStore {

    /**
     * 根据key获取指定数据
     */
    HulkConfigModel getConfData(String key);
    
    /**
     * 获取所有配置的key列表集合
     * @return
     */
    Set<String> getConfigKeySet();
    
    /**
     * 是否存在此配置
     */
    boolean hasConfig(String key);
    
    /**
     * 扫描的实例写入
     * @param object
     * @param key
     */
    void insertScanInstance(Object object,String key);
    
    /**
     * 扫描的实例更新配置
     * @param object
     * @param key
     */
    void updateScanInstance(Object object,String key);
    
    /**
     * 更新
     * @param key
     * @param value
     */
    void update(String key,Object value);
    
    /**
     * 添加一个配置数据
     */
    void addConfigData(HulkConfigModel hulkConfigModel);
    
    /**
     * 
     * 批量添加配置
     */
    void batchConfigData(List<HulkConfigModel> hulkConfigModel);

    /**
     * 获取配置信息
     */
    Object getConfig(String keyName);
}

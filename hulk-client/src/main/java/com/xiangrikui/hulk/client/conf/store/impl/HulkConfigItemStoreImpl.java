package com.xiangrikui.hulk.client.conf.store.impl;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.client.conf.model.HulkConfigItemModel;
import com.xiangrikui.hulk.client.conf.model.HulkConfigModel;
import com.xiangrikui.hulk.client.conf.store.HulkConfigCache;
import com.xiangrikui.hulk.client.conf.store.HulkConfigStore;

/**
 * 创建时间：2017年3月16日
 * <p>修改时间：2017年3月16日
 * <p>类说明：配置中心配置项存储实现类
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkConfigItemStoreImpl implements HulkConfigStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(HulkConfigItemStoreImpl.class);
    
    
    public HulkConfigModel getConfData(String key) {
        if(hasConfig(key)){
            return HulkConfigCache.getInstance().getConfigItem().get(key);
        }else{
            return null;
        }
    }

   
    public Set<String> getConfigKeySet() {
        return null;
    }

    
    public boolean hasConfig(String key) {
        return HulkConfigCache.getInstance().getConfigItem().containsKey(key);
    }

    
    public void addConfigData(HulkConfigModel hulkConfigModel) {
        //添加配置信息到配置中心存储cache中
        HulkConfigCache.getInstance().storeConfigItem(hulkConfigModel);
    }

    
    public void batchConfigData(List<HulkConfigModel> hulkConfigModel) {
        //批量添加配置信息到配置中心存储cache中
        for (HulkConfigModel hulkCfongiItem : hulkConfigModel) {
            addConfigData(hulkCfongiItem);
        }
    }

    
    public Object getConfig(String keyName) {
        HulkConfigItemModel configItem = HulkConfigCache.getInstance().getConfigItem().get(keyName);
        
        if(configItem == null){
            LOGGER.info("cannot find {} in HulkConfigCache",keyName);
            return null;
        }
        return configItem.getValue();
        
        
    }

    

    
    @Override
    public void update(String key, Object value) {
        
    }


    @Override
    public void insertScanInstance(Object object, String key) {
        
    }


    @Override
    public void updateScanInstance(Object object, String key) {
        HulkConfigItemModel configItem  = HulkConfigCache.getInstance().getConfigItem().get(key);
        if(configItem == null){
            LOGGER.info("cannot find {} in HulkConfigCache",key);
            return;
        }
        if(object != null){
            configItem.setObject(object);
        }
            
        try {
            //判断对象是否为空,空说明是静态类
            if(object != null){
                Object defaultValue = configItem.getField().get(object);
                if(configItem.getValue() == null){
                    configItem.getField().set(object, defaultValue);
                    configItem.setValue(defaultValue);
                }else{
                    configItem.getField().set(object, configItem.getValue());
                }
            }else{
                if(Modifier.isStatic(configItem.getField().getModifiers())){
                    configItem.getField().set(null, configItem.getValue());
                }
            }
        } catch (Exception e) {
            LOGGER.error("updateScanInstance key: " + key + " " + e.toString(), e);
        }
    }


   
}

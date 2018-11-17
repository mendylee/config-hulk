package com.xiangrikui.hulk.client.conf.handler.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.xiangrikui.hulk.client.common.HulkAppContext;
import com.xiangrikui.hulk.client.conf.handler.HulkConfigHandler;
import com.xiangrikui.hulk.client.conf.model.HulkConfigItemModel;
import com.xiangrikui.hulk.client.conf.store.HulkConfigStore;
import com.xiangrikui.hulk.client.conf.store.HulkConfigStoreFactory;
import com.xiangrikui.hulk.client.scan.reflect.ReflectBeanService;
import com.xiangrikui.hulk.client.scan.support.ReflectTargetObjectBean;

/**
 * 创建时间：2017年3月16日
 * <p>修改时间：2017年3月16日
 * <p>类说明：配置中心项处理实现类
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkItemHandlerImpl implements HulkConfigHandler {

    
    private ReflectBeanService reflectBeanService;
    
    public HulkItemHandlerImpl(HulkAppContext context){
        this.reflectBeanService = context.getReflectBeanService();
    }
    
    /**
     * 获取配置中心配置项存储服务
     */
    private HulkConfigStore hulkConfigStore = HulkConfigStoreFactory.getHulkConfigItemStore();
    
    /**
     * 写入一个配置项
     */
    public void writeConfig(String key,String value){
        insertConfigItem(key, value);
    }
    /**
     * 更新一个配置项
     */
    public void updateConfig(String key,String value){
        updateConfigItem(key, value);
    }
    
    
    private void insertConfigItem(String key,String value){
        HulkConfigItemModel configItem = (HulkConfigItemModel) hulkConfigStore.getConfData(key);
        if(configItem == null){
            HulkConfigItemModel configItemModel = new HulkConfigItemModel();
            configItemModel.setKey(key);
            configItemModel.setValue(value);
            hulkConfigStore.addConfigData(configItemModel);
        }else{
            //配置中心存储服务中存在此配置,那么可能是配置中心客户端启动初始化时扫描存储,此时用配置中心的值进行替换
            configItem.setValue(value);
            updateConfigInstance(key, value, configItem);
        }
    }
    
    private void updateConfigItem(String key,String value){
        HulkConfigItemModel configItem = (HulkConfigItemModel) hulkConfigStore.getConfData(key);
        if(configItem != null){
            //更新缓存
            configItem.setValue(value);
            if(configItem.getField() != null){
                updateConfigInstance(key, value, configItem);
            }
        }
    }
    
    private void updateConfigInstance(String key,String value,HulkConfigItemModel configItem){
        if(configItem == null){
            return;
        }
        try {
            Object object = null;
            Field field = configItem.getField();
            if(!Modifier.isStatic(field.getModifiers())){
                //非静态从springbean上下文中通过动态代理获取目标对象
                Class<?> clazz  = field.getDeclaringClass();
                object = reflectBeanService.findByClass(clazz, true);
            }
            hulkConfigStore.updateScanInstance(object, key);
        } catch (Exception e) {
        }
        
    }
    
    
    
}

package com.xiangrikui.hulk.client.scan.handle.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.client.common.HulkAppContext;
import com.xiangrikui.hulk.client.common.annotation.HulkConfigItem;
import com.xiangrikui.hulk.client.conf.model.HulkConfigItemModel;
import com.xiangrikui.hulk.client.conf.model.HulkConfigModel;
import com.xiangrikui.hulk.client.conf.store.HulkConfigStoreFactory;
import com.xiangrikui.hulk.client.registry.Registry;
import com.xiangrikui.hulk.client.registry.node.AppNode;
import com.xiangrikui.hulk.client.registry.node.AppNodeFactory;
import com.xiangrikui.hulk.client.scan.handle.HulkScanHandle;
import com.xiangrikui.hulk.client.scan.model.ScanModel;
import com.xiangrikui.hulk.client.scan.reflect.ReflectBeanService;
import com.xiangrikui.hulk.client.scan.support.HulkScanHelper;
import com.xiangrikui.hulk.core.common.util.CollectionUtils;
import com.xiangrikui.hulk.core.common.util.StringUtils;
import com.xiangrikui.hulk.core.context.AppConfig;
import com.xiangrikui.hulk.core.exception.HulkException;

/**
 * 创建时间：2017年3月17日
 * <p>修改时间：2017年3月17日
 * <p>类说明：Hulk配置中心扫描配置项注解Handle处理类
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkScanItemHandler implements HulkScanHandle {

    private static final Logger LOGGER = LoggerFactory.getLogger(HulkScanItemHandler.class);
    /**
     * 配置中心注册服务
     */
    private Registry registry;
    
    /**
     * 应用通用配置信息
     */
    private AppConfig config;
    
    /**
     * 反射查找Bean服务
     */
    private ReflectBeanService reflectBeanService;
    
    
    
    public HulkScanItemHandler(HulkAppContext appContext){
        this.registry = appContext.getRegistry();
        this.config = appContext.getAppConfig();
        this.reflectBeanService = appContext.getReflectBeanService();
    }
    
    @Override
    public void scanDataStore(ScanModel scanModel) {
        
        List<HulkConfigModel> hulkConfigModel = getHulkConfigItems(scanModel);
            
        regiestChildrenNode(hulkConfigModel);
        
        HulkConfigStoreFactory.getHulkConfigItemStore().batchConfigData(hulkConfigModel);
    }
    
    private List<HulkConfigModel> getHulkConfigItems(ScanModel scanModel){
        List<HulkConfigModel> hulkConfigItems = new ArrayList<HulkConfigModel>();
        Set<Method> itemMethod =  scanModel.getHulkConfigItemMethod();
        if(itemMethod.size() > 0 || itemMethod != null){
            for (Method method : itemMethod) {
                HulkConfigItemModel itemModel = converModelFromScan(method);
                if(itemModel != null){
                    hulkConfigItems.add(itemModel);
                }
            } 
        }
        Set<Field> itemField = scanModel.getHulkConfigItemField();
        if(itemField.size() > 0 || itemField != null){
            for (Field field : itemField) {
                HulkConfigItemModel itemModel = converByFieldFromScan(field);
                if(itemModel != null){
                    hulkConfigItems.add(itemModel);
                }
            }
        }
        
        return hulkConfigItems;
    }
    
    
    private HulkConfigItemModel converByFieldFromScan(Field field){
        HulkConfigItemModel configItemModel = null;
        try {
            configItemModel = new HulkConfigItemModel();
            if(field == null){
                return null;
            }
            configItemModel.setField(field);
            HulkConfigItem configItem = field.getAnnotation(HulkConfigItem.class);
            String key = configItem.key().replace(" ", "");
            configItemModel.setKey(key);
            field.setAccessible(true);
            //非静态从springbean上下文中通过动态代理获取目标对象
            //value的值及静态方法的判断取值
            if (Modifier.isStatic(field.getModifiers())) {
                configItemModel.setValue(field.get(null));
            } else {
                Class<?> targetClazz  = field.getDeclaringClass();
                Object object = reflectBeanService.findByClass(targetClazz, true);
                configItemModel.setValue(field.get(object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configItemModel;
    }
    
    private HulkConfigItemModel converModelFromScan(Method method){
        HulkConfigItemModel configItemModel = null; 
        try {
            configItemModel = new HulkConfigItemModel();
            Class<?> clazz = method.getDeclaringClass();
            Field[] fields = clazz.getDeclaredFields();
            Field field = HulkScanHelper.getFieldFromMethod(method, fields);
            if(field == null){
                return null;
            }
            configItemModel.setField(field);
            HulkConfigItem configItem = method.getAnnotation(HulkConfigItem.class);
            String key = configItem.key().replace(" ", "");
            configItemModel.setKey(key);
            field.setAccessible(true);
            //非静态从springbean上下文中通过动态代理获取目标对象
            //value的值及静态方法的判断取值
            if (Modifier.isStatic(field.getModifiers())) {
            
                configItemModel.setValue(field.get(null));
                
            } else {
                Class<?> targetClazz  = field.getDeclaringClass();
                Object object = reflectBeanService.findByClass(targetClazz, true);
                configItemModel.setValue(field.get(object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configItemModel;
    }
    
    private void regiestChildrenNode(List<HulkConfigModel> itemList){
        if(CollectionUtils.isNotEmpty(itemList)){
            for (HulkConfigModel hulkConfigModel : itemList) {
                HulkConfigItemModel configItem = (HulkConfigItemModel) hulkConfigModel;
                AppNode node = AppNodeFactory.create(AppNode.class, config);
                node.setKey(configItem.getKey());
                node.setValue(configItem.getValue());
                configItem.setKey(node.getAppFullInfo());
                if(config.isUseLocalConfigUpload()){
                    if(node.getValue() == null ||  StringUtils.isEmpty(node.getValue().toString())){
                        LOGGER.error("Use the local configuration to upload a hulk exception,the exception is the value of the key:{} is empty",node.getKey());
                        throw new HulkException("Use the local configuration to upload a hulk null exception");
                    }
                    registry.register(node);
                }
            }
        }
        
    }
}

package com.xiangrikui.hulk.client.conf.store;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.client.conf.model.HulkConfigItemModel;
import com.xiangrikui.hulk.client.conf.model.HulkConfigModel;
import com.xiangrikui.hulk.core.cache.DataCache;
import com.xiangrikui.hulk.core.cache.MemoryCache;

/**
 * 创建时间：2017年3月16日
 * <p>修改时间：2017年3月16日
 * <p>类说明：Hulk配置中心本地缓存数据存储类
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkConfigCache {
   
   
   private static final Logger LOGGER = LoggerFactory.getLogger(HulkConfigCache.class); 
    
   //配置项数据缓存 
   private DataCache<String, HulkConfigItemModel> configItemMap = new MemoryCache<String, HulkConfigItemModel>(1000);

   private HulkConfigCache(){
   }
   
   /**
    * 类级别的内部类,此实例与外部实例没有绑定关系，可实现延迟加载
    */
   private static class SingletonHolder{
       //静态初始化,JVM保证线程安全
       private static HulkConfigCache instance = new HulkConfigCache();
   }
   
   public static HulkConfigCache getInstance(){
       
       return SingletonHolder.instance;
   }
       
   /**
    * 存储配置项
    * @param hulkModel
    */
   public void storeConfigItem(HulkConfigModel hulkModel){
       HulkConfigItemModel configItem = (HulkConfigItemModel) hulkModel;
       String key = configItem.getKey();
       if(configItemMap.containsKey(key)){
           LOGGER.error("The storage failed and the key:{} already exists",key);
       }else{
           configItemMap.put(key, configItem);
       }
   }
   
   /**
    * 删除配置项
    * @param key
    */
   public void removeConfigItem(String key){
       if(configItemMap.containsKey(key)){
           configItemMap.remove(key);
       }
   }
   
   public DataCache<String, HulkConfigItemModel> getConfigItem(){
       return configItemMap;
   }
   
   public Map<String, HulkConfigItemModel> getAllConfigItem(){
       return configItemMap.getCacheMap();
   }
   
   
}

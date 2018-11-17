package com.xiangrikui.hulk.core.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间：2017年3月15日
 * <p>修改时间：2017年3月15日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class MemoryCache<K,V> implements DataCache<K, V> {

    private Map<K, V> cacheMap;
    
    public MemoryCache(final int maxCacheSize){
        this.cacheMap = Collections.synchronizedMap(new HashMap<>(maxCacheSize));
    }
    
    @Override
    public void put(K key, V value) {
        cacheMap.put(key, value);
    }

    
    @Override
    public V get(K key) {
        return cacheMap.get(key);
    }

    
    @Override
    public V remove(K key) {
        V value = cacheMap.get(key);
        return value;
    }

    
    @Override
    public int size() {
        return cacheMap.size();
    }

    @Override
    public void clear() {
        cacheMap.clear();
    }

    
    @Override
    public boolean containsKey(K key) {
        if(cacheMap.containsKey(key)){
            return true;
        }
        return false;
    }
    @Override
    public Map<K, V> getCacheMap() {
        return cacheMap;
    }
    
    

}

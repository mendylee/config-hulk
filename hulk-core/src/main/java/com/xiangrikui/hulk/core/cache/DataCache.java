package com.xiangrikui.hulk.core.cache;

import java.util.Map;

/**
 * 创建时间：2017年3月15日
 * <p>修改时间：2017年3月15日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public interface DataCache<K,V> {

    void put(K key,V value);
    
    Map<K, V> getCacheMap();
    
    V get(K key);
    
    V remove(K key);
    
    boolean containsKey(K key);
    
    int size();
    
    void clear();
}

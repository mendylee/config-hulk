package com.xiangrikui.hulk.client.scan.reflect;

import java.util.List;

/**
 * 创建时间：2017年4月6日
 * <p>修改时间：2017年4月6日
 * <p>类说明：反射查找bean对象接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface ReflectBeanService {

    /**
     * 根据class查找bean的列表
     * @param clazz
     * @param newInstance
     * @return
     */
    <T> List<T> findAllByClass(Class<T> clazz,boolean newInstance);
    
    /**
     * 根据class查找Bean
     * @param clazz
     * @param newInstance
     * @return
     */
    <T> T findByClass(Class<T> clazz,boolean newInstance);
    
    /**
     * 根据class查找Bean,可选动态代理查询
     * @param clazz
     * @param newInstance
     * @param proxy
     * @return
     */
    <T> T findByClass(Class<T> clazz,boolean newInstance,boolean proxy);
}

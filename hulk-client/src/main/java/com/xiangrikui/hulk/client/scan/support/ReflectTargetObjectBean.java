package com.xiangrikui.hulk.client.scan.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

/**
 * 创建时间：2017年3月31日
 * <p>修改时间：2017年3月31日
 * <p>类说明：根据反射获取目标对象帮助类
 * 
 * @author jerry
 * @version 1.0
 */
public class ReflectTargetObjectBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectTargetObjectBean.class);
    
   
    public static <T> List<T> getTargetObject(Class<T> clazz,boolean newInstance){
        if(SpringContextUtil.getApplicationContext() == null){
            LOGGER.error("Spring Context is null .Cannot autowire " + clazz.getCanonicalName());
            
        }
        if(clazz == null){
            return new ArrayList<T>(0);
        }
        
        Map<String, T> map = findByBeanName(clazz);
        if(map == null || map.isEmpty()){
            if(newInstance){
                //TODO 直接new个实例返回
            }
        }
        return new ArrayList<T>(map.values());
    }
    
    /**
     * 获取最先找到的目标对象
     * @param clazz
     * @param newInstance
     * @return
     */
    public static <T> T getFirstTargetObject(Class<T> clazz,boolean newInstance){
        List<T> list = getTargetObject(clazz, newInstance);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }
    
    public <T> T getFirstTargetObject(Class<T> type, boolean newInstance, boolean withProxy) {

        T object = getFirstTargetObject(type, newInstance);

        if (!withProxy) {
            return object;
        }

        try {
            return getTargetObject(object, type);
        } catch (Exception e) {
            LOGGER.warn(e.toString());
            return object;
        }
    }
    
    /**
     * 通过Spring ApplicationContext上下文获取Bean
     * @param clazz
     * @return
     */
    public static <T> Map<String, T> findByBeanName(Class<T> clazz){
        return SpringContextUtil.findByBeanName(clazz);
    }
    
    @SuppressWarnings("unchecked")
    protected  static <T> T getTargetObject(Object proxy, Class<T> targetClass) throws Exception {
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return (T) ((Advised) proxy).getTargetSource().getTarget();
        } else if (AopUtils.isCglibProxy(proxy)) {
            return (T) ((Advised) proxy).getTargetSource().getTarget();
        } else {
            return (T) proxy;
        }
    }
   
   
}

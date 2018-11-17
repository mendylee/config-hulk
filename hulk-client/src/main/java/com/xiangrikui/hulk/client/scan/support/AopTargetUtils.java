package com.xiangrikui.hulk.client.scan.support;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

/**
 * 创建时间：2017年3月21日
 * <p>修改时间：2017年3月21日
 * <p>类说明：通过AOP获取目标对象工具类
 * 
 * @author jerry
 * @version 1.0
 */

public class AopTargetUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(AopTargetUtils.class);
    
    
    @SuppressWarnings({"unchecked"})
    public static <T> T getTargetObject(Object proxy, Class<T> targetClass) throws Exception {

        
        return (T) ((AdvisedSupport) proxy).getTargetSource().getTarget();
    }
    
    /** 
     * 获取 目标对象 
     * @param proxy 代理对象 
     * @return  
     * @throws Exception 
     */  
    public static Object getTarget(Object proxy) throws Exception {  
          
        if(!AopUtils.isAopProxy(proxy)) {  
            return proxy;//不是代理对象  
        }  
        if(AopUtils.isJdkDynamicProxy(proxy)) {  
            return getJdkDynamicProxyTargetObject(proxy);  
        } else { //cglib  
            return getCglibProxyTargetObject(proxy);  
        }  
          
          
          
    }  
  
  
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {  
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");  
        h.setAccessible(true);  
        Object dynamicAdvisedInterceptor = h.get(proxy);  
          
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");  
        advised.setAccessible(true);  
          
        Object target = ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();  
          
        return target;  
    }  
  
  
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {  
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");  
        h.setAccessible(true);  
        AopProxy aopProxy = (AopProxy) h.get(proxy);  
          
        Field advised = aopProxy.getClass().getDeclaredField("advised");  
        advised.setAccessible(true);  
          
        Object target = ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget();  
          
        return target;  
    } 
    
    /**
     * 获取Spring Bean
     */
    public static Object getSpringBean(Class<?> cls) throws Exception {

        if (SpringContextUtil.getApplicationContext() == null) {
            LOGGER.error("Spring Context is null. Cannot autowire " + cls.getCanonicalName());
            return null;
        }

        // spring 方式
        Object object = SpringContextUtil.getBean(cls);

        return getTarget(object);
    }
}

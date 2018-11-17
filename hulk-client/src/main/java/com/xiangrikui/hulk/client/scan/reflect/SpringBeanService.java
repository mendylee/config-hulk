package com.xiangrikui.hulk.client.scan.reflect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 创建时间：2017年4月6日
 * <p>修改时间：2017年4月6日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class SpringBeanService implements ReflectBeanService,ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBeanService.class);
    private static ApplicationContext applicationContext;
    
    private ReflectBeanService reflectBeanService = new SimpleBeanService();
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    
    @Override
    public <T> List<T> findAllByClass(Class<T> clazz, boolean newInstance) {
        if(applicationContext == null){
            LOGGER.error("Spring Context is null .Cannot autowire " + clazz.getCanonicalName());
            LOGGER.debug("Not found from Spring IoC container for " + clazz.getSimpleName() + ", and try to init by "
                    + "calling newInstance.");
            return reflectBeanService.findAllByClass(clazz, newInstance);
        }
        if(clazz == null){
          return new ArrayList<T>();  
        }
        Map<String, T> map = findBeanMap(clazz);
        if(map == null || map.isEmpty()){
            if(newInstance){
                //TODO
                LOGGER.debug("Not found from Spring IoC container for " + clazz.getSimpleName() + ", and try to init by "
                        + "calling newInstance.");
                return reflectBeanService.findAllByClass(clazz, newInstance);
            }
        }
        return new ArrayList<T>(map.values());
    }

    
    @Override
    public <T> T findByClass(Class<T> clazz, boolean newInstance) {
        List<T> list = this.findAllByClass(clazz, newInstance);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    
    @Override
    public <T> T findByClass(Class<T> clazz, boolean newInstance, boolean proxy) {
        T object = findByClass(clazz, newInstance);
        if(!proxy){
            return object;
        }
        try {
            return getTargetObject(object, clazz);
        } catch (Exception e) {
            LOGGER.warn(e.toString());
            return object;
        }
    }
    
    private <T> Map<String, T> findBeanMap(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }
    
    @SuppressWarnings("unchecked")
    protected <T> T getTargetObject(Object proxy, Class<T> targetClass) throws Exception {
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return (T) ((Advised) proxy).getTargetSource().getTarget();
        } else if (AopUtils.isCglibProxy(proxy)) {
            return (T) ((Advised) proxy).getTargetSource().getTarget();
        } else {
            return (T) proxy;
        }
    }

}

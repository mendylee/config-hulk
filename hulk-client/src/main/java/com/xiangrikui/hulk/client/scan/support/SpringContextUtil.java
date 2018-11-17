package com.xiangrikui.hulk.client.scan.support;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 创建时间：2017年3月21日
 * <p>修改时间：2017年3月21日
 * <p>类说明：Spring应用上下文工具类
 * 
 * @author jerry
 * @version 1.0
 */
public class SpringContextUtil implements ApplicationContextAware {

 // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @throws BeansException
     */
    public static Object getBean(Class<?> cls) throws BeansException {
        return applicationContext.getBean(cls);
    }

    /**
     * @throws BeansException
     */
    public static Object getBeansOfType(Class<?> type) throws BeansException {
        return applicationContext.getBeansOfType(type);
    }

    /**
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }
    
    /**
     * 通过Spring ApplicationContext上下文获取Bean
     * @param clazz
     * @return
     */
    public static <T> Map<String, T> findByBeanName(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 获取使用 Proxy.newProxyInstance 生成的代理对象
     *
     * @throws Exception
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T getProxyObject(Object proxy, Class<T> thisClass) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        return (T) h.get(proxy);
    }
}

package com.xiangrikui.hulk.client.scan.reflect;

import org.springframework.context.ApplicationContext;

/**
 * 创建时间：2017年4月6日
 * <p>修改时间：2017年4月6日
 * <p>类说明：反射查找Bean工厂
 * 
 * @author jerry
 * @version 1.0
 */
public class ReflectBeanFactory {

    /**
     * 获得反射Bean服务
     * @param applicationContext
     * @return
     */
    public static ReflectBeanService getReflectBeanService(ApplicationContext applicationContext){
        SpringBeanService springBeanService = new SpringBeanService();
        springBeanService.setApplicationContext(applicationContext);
        return springBeanService;
    }
}

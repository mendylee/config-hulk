package com.xiangrikui.hulk.client.spring.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import com.google.common.base.Objects;

/**
 * 创建时间：2017年3月10日
 * <p>修改时间：2017年3月10日
 * <p>类说明：spring bean注册工具类
 * 
 * @author jerry
 * @version 1.0
 */
public class BeanRegisterUtil {
    
    /**
     * 注册bean
     * @param beanRegistray
     * @param beanName
     * @param beanClass
     * @return
     */
    public static boolean regiesterBeanDefintion(BeanDefinitionRegistry beanRegistray,String beanName,Class<?> beanClass){
        
        if(beanRegistray.containsBeanDefinition(beanName)){
            return false;
        }
        
        String[] beanNames = beanRegistray.getBeanDefinitionNames();
        
        for (String name : beanNames) {
            BeanDefinition beanDefinition = beanRegistray.getBeanDefinition(name);
            if(Objects.equal(beanDefinition.getBeanClassName(), beanClass.getName())){
                return false;
            }
        }
        
        BeanDefinition annotationProcess = BeanDefinitionBuilder.genericBeanDefinition(beanClass).getBeanDefinition();
        beanRegistray.registerBeanDefinition(beanName,annotationProcess);
        
        return true;
    }
}

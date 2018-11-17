package com.xiangrikui.hulk.client.scan.reflect;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建时间：2017年4月6日
 * <p>修改时间：2017年4月6日
 * <p>类说明：简单实现的反射查找Bean服务实现类
 * 
 * @author jerry
 * @version 1.0
 */
public class SimpleBeanService implements ReflectBeanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBeanService.class);
   
    @Override
    public <T> List<T> findAllByClass(Class<T> clazz, boolean newInstance) {
       List<T> list = new ArrayList<>(1);
       try {
           list.add(clazz.newInstance());
       } catch (InstantiationException e) {

           LOGGER.error("Failed to init " + clazz.getSimpleName() + " " + e.toString());

       } catch (IllegalAccessException e) {

           LOGGER.error("Failed to init " + clazz.getSimpleName() + " " + e.toString());
       }
       return list;
    }

    
    @Override
    public <T> T findByClass(Class<T> clazz, boolean newInstance) {
        List<T> list = findAllByClass(clazz, newInstance);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    
    @Override
    public <T> T findByClass(Class<T> clazz, boolean newInstance, boolean proxy) {
        return findByClass(clazz, newInstance);
    }

}

package com.xiangrikui.hulk.core.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建时间：2017年3月24日
 * <p>修改时间：2017年3月24日
 * <p>类说明：Properties属性文件自动导入工厂类
 * 
 * @author jerry
 * @version 1.0
 */
public class PropertiesConfigurationFactory {
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfigurationFactory.class);
    
    /**
     * 根据文件路径位置反射自动装配properties对象信息
     * @param clazz
     * @param locations
     * @return
     */
    public static <T> T createPropertiesConfiguration(Class<T> clazz,String[] locations){
        if(clazz == null){
            throw new IllegalArgumentException("clazz should not be null");
        }
        if(locations == null || locations.length == 0){
            throw new IllegalArgumentException("must specified the properties locations");
        }
        Properties properties = new Properties();
        for (String location : locations) {
            try {
                properties.load(PropertiesConfigurationFactory.class.getClassLoader().getResourceAsStream(location.trim()));
            } catch (IOException e) {
                throw new IllegalStateException("Load properties [" + location + "] error", e);
            }
        }
        Map<String, String> map = new HashMap<>(properties.size());
        for(Map.Entry<Object, Object> entry:properties.entrySet()){
            String key = entry.getKey().toString();
            String value = entry.getValue()==null?null:entry.getValue().toString();
            if(value != null){
                map.put(key, value);
            }
        }
        
        return autowareConfig(clazz, map);
    }
    
    /**
     * 根据properties集合自动装配
     * @param clazz
     * @param propertiesMap
     * @return
     */
    public static <T> T autowareConfig(Class<T> clazz,Map<String, String> propertiesMap){
        if(clazz == null){
            throw new IllegalArgumentException("clazz should not be null");
        }
        T targetObj;
        try {
            targetObj = clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(clazz.getName() + " instance error", e);
        }
        try {
            Field[] fields = targetObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                String name;
                String value;
                name = field.getName();
                value = propertiesMap.get("hulk." + name);
                field.setAccessible(true);
                if(value != null){
                    try {
                        setFieldValeByType(field, targetObj, value);
                    } catch (Exception e) {
                        LOGGER.error(String.format("invalid config: %s", name), e);
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("autowareConfig failed",e);
        }
        
        return targetObj;
        
    }
    
    /**
     * 反射方法,设置字段的值
     * @param field
     * @param obj
     * @param value
     * @throws Exception
     */
    private static void setFieldValeByType(Field field, Object obj, String value)
            throws Exception {

        Class<?> type = field.getType();

        String typeName = type.getName();

        if (typeName.equals("int")) {

            if (value.equals("")) {
                value = "0";
            }
            field.set(obj, Integer.valueOf(value));

        } else if (typeName.equals("long")) {

            if (value.equals("")) {
                value = "0";
            }
            field.set(obj, Long.valueOf(value));

        } else if (typeName.equals("boolean")) {

            if (value.equals("")) {
                value = "false";
            }
            field.set(obj, Boolean.valueOf(value));

        } else if (typeName.equals("double")) {

            if (value.equals("")) {
                value = "0.0";
            }
            field.set(obj, Double.valueOf(value));

        } else {

            field.set(obj, value);
        }
    }
    
    
}

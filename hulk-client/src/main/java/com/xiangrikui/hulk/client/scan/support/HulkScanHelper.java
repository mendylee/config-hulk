package com.xiangrikui.hulk.client.scan.support;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.client.common.annotation.HulkConfigItem;
import com.xiangrikui.hulk.core.common.util.StringUtils;

/**
 * 创建时间：2017年3月21日
 * <p>修改时间：2017年3月21日
 * <p>类说明：配置中心扫描帮助类
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkScanHelper {
    
    private static Logger LOGGER = LoggerFactory.getLogger(HulkScanHelper.class);

    
    public <T> List<T> getTargetObject(Class<?> clazz,boolean newInstance){
        if(clazz == null){
            return new ArrayList<T>(0);
        }
        return new ArrayList<T>(0);
    }
    
    public static Field getFieldFromMethod(Method method,Field[] fields){
        String fieldName;
        HulkConfigItem configItem = method.getAnnotation(HulkConfigItem.class);
        fieldName = configItem.name();
        for (Field field : fields) {
            if(field.getName().equals(fieldName)){
                return field;
            }
        }
        LOGGER.error("cannot get FieldName,FromMethod:{}");
        return null;
    }
    
    
    /**
     * @param source
     * @param token
     *
     * @return
     */
    public static List<String> parseStringToStringList(String source,
                                                       String token) {

        if (StringUtils.isEmpty(token)) {
            return null;
        }

        List<String> result = new ArrayList<String>();

        String[] units = source.split(token);
        for (String unit : units) {
            result.add(unit);
        }
        return result;
    }
    
    
}

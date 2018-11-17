package com.xiangrikui.hulk.client.conf.model;

import java.lang.reflect.Field;

/**
 * 创建时间：2017年3月16日
 * <p>修改时间：2017年3月16日
 * <p>类说明：配置中心配置项实体类
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkConfigItemModel extends HulkConfigModel {
    
    //文件项key和value
    private String key;
    private Object value;
    //字段
    private Field field;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }
    
    
    public Object getFieldDefaultValue(Object object) throws Exception {
        return field.get(object);
    }
    
}

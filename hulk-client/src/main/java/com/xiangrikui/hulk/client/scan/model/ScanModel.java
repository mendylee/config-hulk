package com.xiangrikui.hulk.client.scan.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import org.reflections.Reflections;

/**
 * 创建时间：2017年3月17日
 * <p>修改时间：2017年3月17日
 * <p>类说明：配置中心扫描实体信息
 * 
 * @author jerry
 * @version 1.0
 */
public class ScanModel {
    
    //java运行时元数据分析(注解scannotations)
    private Reflections reflections;
    
    //配置项Item方法集合
    private Set<Method> hulkConfigItemMethod;
    
    //配置项Item字段集合
    private Set<Field> hulkConfigItemField;
    
    
    

    public Set<Field> getHulkConfigItemField() {
        return hulkConfigItemField;
    }

    public void setHulkConfigItemField(Set<Field> hulkConfigItemField) {
        this.hulkConfigItemField = hulkConfigItemField;
    }

    public Set<Method> getHulkConfigItemMethod() {
        return hulkConfigItemMethod;
    }

    public void setHulkConfigItemMethod(Set<Method> hulkConfigItemMethod) {
        this.hulkConfigItemMethod = hulkConfigItemMethod;
    }

    public Reflections getReflections() {
        return reflections;
    }

    public void setReflections(Reflections reflections) {
        this.reflections = reflections;
    }
    
    
    
}

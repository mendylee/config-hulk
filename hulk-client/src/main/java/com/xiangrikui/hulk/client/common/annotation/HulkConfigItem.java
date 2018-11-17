package com.xiangrikui.hulk.client.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间：2017年3月20日
 * <p>修改时间：2017年3月20日
 * <p>类说明：Hulk配置中心配置项注解
 * 
 * @author jerry
 * @version 1.0
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HulkConfigItem {
    /**
     * 配置的key
     * @return
     */
    String key();
    /**
     * 字段属性名称
     * @return
     */
    String name();
    
    /**
     * 配置项注释,默认空
     * @return
     */
    String comment() default "";
}

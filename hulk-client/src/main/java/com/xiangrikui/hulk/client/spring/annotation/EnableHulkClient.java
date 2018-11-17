package com.xiangrikui.hulk.client.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.xiangrikui.hulk.client.spring.boot.HulkClientConfiguration;

/**
 * 创建时间：2017年3月8日
 * <p>修改时间：2017年3月8日
 * <p>类说明：Hulk配置中心客户端注解
 * 
 * @author jerry
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HulkClientConfiguration.class)
public @interface EnableHulkClient {
    
}

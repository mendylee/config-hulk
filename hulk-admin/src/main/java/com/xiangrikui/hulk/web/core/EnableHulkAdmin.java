package com.xiangrikui.hulk.web.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * 创建时间：2017年5月22日
 * <p>修改时间：2017年5月22日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HulkAdminCongiuration.class)
public @interface EnableHulkAdmin {

}

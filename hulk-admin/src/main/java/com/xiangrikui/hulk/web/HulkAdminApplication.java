package com.xiangrikui.hulk.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xiangrikui.hulk.web.core.EnableHulkAdmin;

/**
 * 
 * 创建时间：2017年5月5日
 * <p>修改时间：2017年5月5日
 * <p>类说明：配置中心后台管理系统应用入口
 * 
 * @author jerry
 * @version 1.0
 */
@EnableHulkAdmin
@SpringBootApplication
public class HulkAdminApplication 
{
    
    public static void main( String[] args )
    {
       SpringApplication.run(HulkAdminApplication.class, args);
    }
    
}

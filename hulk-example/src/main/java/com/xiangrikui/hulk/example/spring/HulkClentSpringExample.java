package com.xiangrikui.hulk.example.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiangrikui.hulk.client.HulkClient;

/**
 * 创建时间：2017年3月15日
 * <p>修改时间：2017年3月15日
 * <p>类说明：HulkClientSpring集成示例
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkClentSpringExample{
   
    @SuppressWarnings("resource")
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("/hulk-spring.xml");
        HulkClient hulkClient = (HulkClient) context.getBean("hulkClient");
        String serverTimeOut = (String) hulkClient.getConfig("serverTimeOut").getValue();
        System.out.println(serverTimeOut);
    }
    
}

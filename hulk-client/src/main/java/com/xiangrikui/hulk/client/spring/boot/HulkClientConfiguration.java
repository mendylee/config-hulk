package com.xiangrikui.hulk.client.spring.boot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.xiangrikui.hulk.client.HulkClient;
import com.xiangrikui.hulk.client.common.HulkClientBuilder;
import com.xiangrikui.hulk.client.spring.properties.HulkClientProperties;

/**
 * 创建时间：2017年3月15日
 * <p>修改时间：2017年3月15日
 * <p>类说明：Hulk配置中心客户端核心Bean类
 * 
 * @author jerry
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(HulkClientProperties.class)
public class HulkClientConfiguration implements ApplicationContextAware,Ordered,InitializingBean,DisposableBean  {

    private ApplicationContext applicationContext;
    private int order = 1;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    private HulkClient hulkClient;
    
    @Autowired(required = false)
    private HulkClientProperties properties;
    
    
    
   
    
    public void afterPropertiesSet() throws Exception {
        //1.检查配置信息
        properties.checkProperties();
        //2.构造HulkClient客户端
        hulkClient = HulkClientBuilder.buildByProperties(properties);
        hulkClient.setApplicationContext(applicationContext);
        //3.启动
        hulkClient.start();
    }
    
    @Bean
    public HulkClient hulkClient(){
        return hulkClient;
    }
    
    

    public void destroy() throws Exception {
        if(hulkClient != null){
            hulkClient.destory();
        }
    }

    
    @Override
    public int getOrder() {
        return this.order;
    }

   
    

    
}

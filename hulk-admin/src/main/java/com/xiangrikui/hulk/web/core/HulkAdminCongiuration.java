package com.xiangrikui.hulk.web.core;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.xiangrikui.hulk.web.zookeeper.ZookeeperManager;
import com.xiangrikui.hulk.web.zookeeper.config.ZookeeperConfig;

/**
 * 创建时间：2017年5月22日
 * <p>修改时间：2017年5月22日
 * <p>类说明：Hulk配置中心后台启动入口类
 * 
 * @author jerry
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(ZookeeperConfig.class)
public class HulkAdminCongiuration implements Ordered,InitializingBean,DisposableBean {

    @Autowired(required = false)
    private ZookeeperConfig zkConfig;
    
    
    
    
    
   

   
    @Override
    public void afterPropertiesSet() throws Exception {
        //2.连接并初始化zookeeper
        ZookeeperManager.getInstance().init(zkConfig.getUrl());
    }
    
    
    @Override
    public void destroy() throws Exception {
        ZookeeperManager.getInstance().close();
    }
    
    

    @Override
    public int getOrder() {
        
        return Ordered.LOWEST_PRECEDENCE;
    }
    
    

}

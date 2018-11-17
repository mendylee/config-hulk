package com.xiangrikui.hulk.web.core;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiangrikui.hulk.web.entity.HulkApp;
import com.xiangrikui.hulk.web.service.AppService;
import com.xiangrikui.hulk.web.zookeeper.ZookeeperManager;

/**
 * 创建时间：2017年5月19日
 * <p>修改时间：2017年5月19日
 * <p>类说明：配置中心后台启动入口类
 * 
 * @author jerry
 * @version 1.0
 */
public  class HulkAdminBootStrap {

    /**
     * 启动入口标识
     */
    private AtomicBoolean started = new AtomicBoolean(false);
    
    private String ROOT_PATH = "/hulk";
    
    
    @Autowired
    private AppService configService;
    
  
    
    
    
    /**
     * 启动
     */
    final public void start(){
        if(started.compareAndSet(false, true)){
            //1.获取zookeeper连接
            List<String> childList = ZookeeperManager.getInstance().getConf(ROOT_PATH);
            //2.获取根节点zookeeper节点信息
            for (String string : childList) {
                System.out.println(string);
            }
            List<HulkApp> appService = configService.findAll();
            for (HulkApp hulkApp : appService) {
                System.out.println(hulkApp.getAppName());
            }
            //3.同步zookeeper数据到数据库
            System.out.println("!!!!!!!!!!!!!!!");
        }
    }
    
}

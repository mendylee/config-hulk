package com.xiangrikui.hulk.client.registry;

import com.xiangrikui.hulk.client.registry.zookeeper.ZookeeperRegistry;
import com.xiangrikui.hulk.core.context.AppContext;

/**
 * 创建时间：2017年3月9日
 * <p>修改时间：2017年3月9日
 * <p>类说明：配置中心应用注册工厂类
 * 
 * @author jerry
 * @version 1.0
 */
public class RegistryFactory {
    
    /**
     * 获取注册工厂
     * @return
     */
    public static Registry getRegistry(AppContext hulkAppContext){
        //目前支持zookeeper作为配置中心服务注册,支持后续扩展其他实现
        return new ZookeeperRegistry(hulkAppContext);
        
    }
}

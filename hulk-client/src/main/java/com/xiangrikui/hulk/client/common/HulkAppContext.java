package com.xiangrikui.hulk.client.common;

import com.xiangrikui.hulk.client.registry.Registry;
import com.xiangrikui.hulk.client.scan.reflect.ReflectBeanService;
import com.xiangrikui.hulk.core.context.AppContext;

/**
 * 创建时间：2017年3月9日
 * <p>修改时间：2017年3月9日
 * <p>类说明：配置中心应用上下文
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkAppContext extends AppContext {

    /**
     * 注册服务实例
     */
    private Registry registry;
    
    /**
     * 反射查找Bean服务
     * @return
     */
    private ReflectBeanService reflectBeanService;
    
    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public ReflectBeanService getReflectBeanService() {
        return reflectBeanService;
    }

    public void setReflectBeanService(ReflectBeanService reflectBeanService) {
        this.reflectBeanService = reflectBeanService;
    }
    
    
}

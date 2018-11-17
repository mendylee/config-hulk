package com.xiangrikui.hulk.client.conf.model;

import com.xiangrikui.hulk.core.context.AppConfig;

/**
 * 创建时间：2017年3月16日
 * <p>修改时间：2017年3月16日
 * <p>类说明：配置中心抽象配置模型实体基类
 * 
 * @author jerry
 * @version 1.0
 */
public abstract class HulkConfigModel {
    
    /**
     * 扫描的对象实体
     */
    private Object object;
    
    /**
     * 配置中心通用应用基础信息
     */
    private AppConfig appConfig;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
    
    
    
}

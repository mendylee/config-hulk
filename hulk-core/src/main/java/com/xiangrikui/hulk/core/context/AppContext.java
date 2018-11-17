package com.xiangrikui.hulk.core.context;

/**
 * 创建时间：2017年3月7日
 * <p>修改时间：2017年3月7日
 * <p>类说明：配置中心上下文抽象类
 * 
 * @author jerry
 * @version 1.0
 */
public abstract class AppContext {
    
    /**应用客户端环境信息*/
    private AppConfig appConfig;

    
    

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
    
    
}

package com.xiangrikui.hulk.client.common;

import com.xiangrikui.hulk.core.context.AppConfig;

/**
 * 创建时间：2017年3月10日
 * <p>修改时间：2017年3月10日
 * <p>类说明：通用应用配置管理工厂类
 * 
 * @author jerry
 * @version 1.0
 */
public class AppConfigFactory {

    public static AppConfig getAppConfig(){
        AppConfig appConfig = new AppConfig();
        appConfig.setRegiestZkAddress("127.0.0.1:2181");
        return appConfig;
    }
}

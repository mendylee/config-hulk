package com.xiangrikui.hulk.client.registry.node;

import com.xiangrikui.hulk.core.context.AppConfig;

/**
 * 创建时间：2017年3月15日
 * <p>修改时间：2017年3月15日
 * <p>类说明：应用节点工厂类
 * 
 * @author jerry
 * @version 1.0
 */
public class AppNodeFactory {
    public static <T extends AppNode> T create(Class<T> clazz,AppConfig config){
        try {
            T appNode = clazz.newInstance();
            appNode.setAppId(config.getAppId());
            appNode.setAppName(config.getAppName());
            appNode.setEnv(config.getEnv());
            appNode.setVersion(config.getVersion());
            return appNode;
        } catch (Exception e) {
            throw new RuntimeException("Create App Node error: clazz="+clazz);
        }
    }
}

package com.xiangrikui.hulk.core.zookeeper.util;

import com.xiangrikui.hulk.core.common.constant.Constants;
import com.xiangrikui.hulk.core.common.util.StringUtils;
import com.xiangrikui.hulk.core.context.AppConfig;

/**
 * 创建时间：2017年3月3日
 * <p>修改时间：2017年3月3日
 * <p>类说明：zookeeper路径工具类
 * 
 * @author jerry
 * @version 1.0
 */
public class ZookeeperPathUtils {

    /**
     * 获取配置中心根路径
     * @return
     */
    public static String getRootPath(){
        return Constants.DEFAULT_NODE_NAMESPACE;
    }
    
    /**
     * 根据应用节点获取配置中心节点的完整路径信息
     * @param hulkNode
     * @return
     */
    public static String getNodeFullPath(String app,String env,String version,String key){
        StringBuilder path = new StringBuilder();
        path.append(getRootPath());
        path.append(Constants.CONFIG_PATH_SPLIT_TOKEN);
        path.append(app);
        
        path.append("_");
        path.append(env);
        
        path.append("_");
        path.append(version);
        
        if(StringUtils.isNotEmpty(key)){
            path.append("/");
            path.append(key);
        }
        
        return path.toString();
    }
    
    /**
     * 根据应用通用配置信息获取配置中心节点的完整路径信息
     * @param config
     * @return
     */
    public static String getNodeFullPath(AppConfig config){
        StringBuilder path = new StringBuilder();
        path.append(getRootPath());
        path.append(Constants.CONFIG_PATH_SPLIT_TOKEN);
        path.append(config.getAppName());
        
        path.append("_");
        path.append(config.getEnv());
        
        path.append("_");
        path.append(config.getVersion());
        
        return path.toString();
    }
    
}

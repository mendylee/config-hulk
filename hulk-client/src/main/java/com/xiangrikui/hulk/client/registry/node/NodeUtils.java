package com.xiangrikui.hulk.client.registry.node;

import com.xiangrikui.hulk.client.common.constans.Constants;
import com.xiangrikui.hulk.core.common.util.StringUtils;
import com.xiangrikui.hulk.core.context.AppConfig;

/**
 * 创建时间：2017年3月22日
 * <p>修改时间：2017年3月22日
 * <p>类说明：配置中心节点工具类
 * 
 * @author jerry
 * @version 1.0
 */
public class NodeUtils {
    
    
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
    public static String getNodeFullPath(AppNode node){
        StringBuilder path = new StringBuilder();
        path.append(getRootPath());
        path.append(Constants.CONFIG_PATH_SPLIT_TOKEN);
        path.append(node.getAppName());
        
        path.append("_");
        path.append(node.getEnv());
        
        path.append("_");
        path.append(node.getVersion());
        
        if(StringUtils.isNotEmpty(node.getKey())){
            path.append("/");
            path.append(node.getKey());
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

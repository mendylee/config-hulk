package com.xiangrikui.hulk.web.zookeeper;

import java.util.List;
import java.util.Map;

/**
 * 创建时间：2017年5月19日
 * <p>修改时间：2017年5月19日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public interface ZookeeperServer {
    
    /**
     * 通知节点数据变更
     * @param app
     * @param env
     * @param version
     * @param kye
     * @param value
     */
    void notifyNodeChange(String app,String env,String version,String kye,String value);
    
    /**
     * 获取根节点下的所有zookeeper节点信息
     * @param path
     * @return
     */
    List<Map<String, String>> getConfig(String rootPath);
    
    
    
}

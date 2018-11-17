package com.xiangrikui.hulk.web.zookeeper.service;

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
public interface ZookeeperService {
    
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
    Map<String, List<String>> getConfig(String rootPath);
    
    /**
     * 保存配置到zookeeper服务
     * @param key
     * @param value
     */
    void saveZookeeperConfig(String key,String value);
    
    
    
}

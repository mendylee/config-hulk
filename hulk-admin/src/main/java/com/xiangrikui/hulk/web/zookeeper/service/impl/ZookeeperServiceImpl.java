package com.xiangrikui.hulk.web.zookeeper.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xiangrikui.hulk.core.zookeeper.util.ZookeeperPathUtils;
import com.xiangrikui.hulk.web.zookeeper.ZookeeperManager;
import com.xiangrikui.hulk.web.zookeeper.service.ZookeeperService;

/**
 * 创建时间：2017年5月24日
 * <p>修改时间：2017年5月24日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */

@Component
public class ZookeeperServiceImpl implements ZookeeperService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperServiceImpl.class);
    
    @Override
    public void notifyNodeChange(String app, String env, String version, String key, String value) {
        String nodePath = ZookeeperPathUtils.getNodeFullPath(app, env, version, key);
        ZookeeperManager.getInstance().setData(nodePath, value);
        LOGGER.info("ZookeeperConfig NotifyNodeChange Success: key:{},value:{}",key,value);
    }

    
    @Override
    public Map<String, List<String>> getConfig(String rootPath) {
        return null;
    }


    
    @Override
    public void saveZookeeperConfig(String key, String value) {
        ZookeeperManager.getInstance().addConf(key, value);
        LOGGER.info("save ZookeeperConfig Success: key:{},value:{}",key,value);
    }

}

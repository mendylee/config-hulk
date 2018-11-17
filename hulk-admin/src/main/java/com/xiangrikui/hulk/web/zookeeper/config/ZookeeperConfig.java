package com.xiangrikui.hulk.web.zookeeper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 创建时间：2017年5月19日
 * <p>修改时间：2017年5月19日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperConfig {

    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}

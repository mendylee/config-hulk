package com.xiangrikui.hulk.web.common;

import com.xiangrikui.hulk.core.context.AppContext;

/**
 * 创建时间：2017年5月19日
 * <p>修改时间：2017年5月19日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkAdminContext extends AppContext {

    private String zookeeperUrl;

    public String getZookeeperUrl() {
        return zookeeperUrl;
    }

    public void setZookeeperUrl(String zookeeperUrl) {
        this.zookeeperUrl = zookeeperUrl;
    }
    
    
}

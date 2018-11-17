package com.xiangrikui.hulk.core.zookeeper;

import com.xiangrikui.hulk.core.context.AppContext;

/**
 * 创建时间：2017年3月3日
 * <p>修改时间：2017年3月3日
 * <p>类说明：Zookeeper传输连接器接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface ZookeeperTransport {
   
    ZookeeperClient connect(AppContext hulkAppContext);
}

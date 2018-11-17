package com.xiangrikui.hulk.core.zookeeper.curator;

import com.xiangrikui.hulk.core.context.AppContext;
import com.xiangrikui.hulk.core.zookeeper.ZookeeperClient;
import com.xiangrikui.hulk.core.zookeeper.ZookeeperTransport;

/**
 * 创建时间：2017年3月3日
 * <p>修改时间：2017年3月3日
 * <p>类说明：CuratorZookeeper客戶端连接器
 * 
 * @author jerry
 * @version 1.0
 */
public class CuratorZookeeperTransport implements ZookeeperTransport {

    @Override
    public ZookeeperClient connect(AppContext hulkAppContext) {
        return new CuratorZookeeperClient(hulkAppContext);
    }

}

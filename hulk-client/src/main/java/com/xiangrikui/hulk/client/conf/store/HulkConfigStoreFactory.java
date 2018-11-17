package com.xiangrikui.hulk.client.conf.store;

import com.xiangrikui.hulk.client.conf.store.impl.HulkConfigItemStoreImpl;

/**
 * 创建时间：2017年3月16日
 * <p>修改时间：2017年3月16日
 * <p>类说明：Hulk配置中心存储服务工厂类
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkConfigStoreFactory {

    public static HulkConfigStore getHulkConfigItemStore(){
        return new HulkConfigItemStoreImpl();
    }
}

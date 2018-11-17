package com.xiangrikui.hulk.web.zookeeper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.core.context.AppConfig;
import com.xiangrikui.hulk.core.context.AppContext;
import com.xiangrikui.hulk.core.zookeeper.StateListener;
import com.xiangrikui.hulk.core.zookeeper.ZookeeperClient;
import com.xiangrikui.hulk.core.zookeeper.ZookeeperTransport;
import com.xiangrikui.hulk.core.zookeeper.curator.CuratorZookeeperTransport;
import com.xiangrikui.hulk.web.common.HulkAdminContext;

/**
 * 创建时间：2017年5月22日
 * <p>修改时间：2017年5月22日
 * <p>类说明：zookeeper统一管理器
 * 
 * @author jerry
 * @version 1.0
 */
public class ZookeeperManager {

    public static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperManager.class);
    
    private ZookeeperClient zkClient;
    
    
    /**
     * 建立连接
     */
    private ZookeeperManager() {

    }
    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
     */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static ZookeeperManager instance = new ZookeeperManager();
    }

    public static ZookeeperManager getInstance() {
        return SingletonHolder.instance;
    }
    
    public void init(String host){
        AppContext hulkAppContext = new HulkAdminContext();
        AppConfig config = new AppConfig();
        config.setRegiestZkAddress(host);
        hulkAppContext.setAppConfig(config);
        ZookeeperTransport transport = new CuratorZookeeperTransport();
        zkClient = transport.connect(hulkAppContext);
        zkClient.addStateListener(new StateListener() {
            public void stateChange(int connectionState) {
             if(connectionState == DISCONNECTED){
                 //配置中心注册服务不可用,切换本地模式
             }else if(connectionState == CONNECTED){
                 //配置中心注册服务正常
             }else if(connectionState == RECONNECTED){
                 //配置中心重新连接,做恢复逻辑
             }
            }
        });
    }
    
    public boolean  exists(String path){
        return zkClient.exists(path);
    }
    
    public List<String> getConf(String path){
        return zkClient.getChildren(path);
    }
    
    public void setData(String key,Object data){
        zkClient.setData(key, data);
    }
    
    public Object getData(String path){
        return zkClient.getData(path);
    }
    
    public void addConf(String path,String value){
        if(zkClient.exists(path)){
            return;
        }
        zkClient.create(path,value, false, false);
    }
    
    public void close(){
        zkClient.close();
    }
}

package com.xiangrikui.hulk.client.registry.zookeeper;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.client.registry.AbstractRegistry;
import com.xiangrikui.hulk.client.registry.NoticeEvent;
import com.xiangrikui.hulk.client.registry.NoticeListener;
import com.xiangrikui.hulk.client.registry.node.AppNode;
import com.xiangrikui.hulk.core.context.AppContext;
import com.xiangrikui.hulk.core.zookeeper.ChildListener;
import com.xiangrikui.hulk.core.zookeeper.StateListener;
import com.xiangrikui.hulk.core.zookeeper.ZookeeperClient;
import com.xiangrikui.hulk.core.zookeeper.ZookeeperTransport;
import com.xiangrikui.hulk.core.zookeeper.curator.CuratorZookeeperTransport;

/**
 * 创建时间：2017年3月9日
 * <p>修改时间：2017年3月9日
 * <p>类说明：zookeeper注册服务
 * 
 * @author jerry
 * @version 1.0
 */
public class ZookeeperRegistry extends AbstractRegistry{

   private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperRegistry.class);
   
   //用来记录每一个通知监听对应多个子监听的数据结构
   private final ConcurrentMap<AppNode, ConcurrentMap<NoticeListener,ChildListener>> zkListeners;
   
   //zookeeper客户端实例
   private ZookeeperClient zkClient;
   
   public ZookeeperRegistry(AppContext hulkAppContext){
       ZookeeperTransport transport = new CuratorZookeeperTransport();
       zkClient = transport.connect(hulkAppContext);
       this.zkListeners = new ConcurrentHashMap<AppNode, ConcurrentMap<NoticeListener, ChildListener>>();
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
       LOGGER.info("Started ZookeeperRegistry ...");
   }

    @Override
    public void doRegiester(AppNode app) {
        if(zkClient.exists(app.getAppFullInfo())){
            return;
        }
        zkClient.create(app.getAppFullInfo(),app.getValue(), false, false);
        LOGGER.info("doRegiester current app {}",app.getAppFullInfo());
        
    }
    
    @Override
    public void doSubScribe(AppNode app,NoticeListener listener){
        final String appNodePath = app.getAppFullInfo(); 
        //为当前app节点路径添加子节点监听
        ChildListener childListener = addChildListener(app, appNodePath,listener);
        zkClient.addNodeChangeListener(appNodePath, childListener);
    }
    
    private ChildListener addChildListener(AppNode app,final String appNodePath,final NoticeListener noticeListener){
       ConcurrentMap<NoticeListener, ChildListener> listeners = zkListeners.get(noticeListener);
       if(listeners == null){
           zkListeners.putIfAbsent(app, new ConcurrentHashMap<NoticeListener, ChildListener>());
           listeners = zkListeners.get(app);
       }
       ChildListener childListener = listeners.get(noticeListener);
       if(childListener == null){
           listeners.putIfAbsent(noticeListener, new ChildListener() {
            public void childChanged(String changeEvent, String path, Object data) {
                LOGGER.info("node changed:{},change type:{},change path:{},change content:{}",appNodePath,changeEvent,path,data);
                if("CHILD_ADDED".equals(changeEvent)){
                    notice(NoticeEvent.CHILD_ADD, path,data,noticeListener);
                }else if("CHILD_UPDATED".equals(changeEvent)){
                    notice(NoticeEvent.CHILD_UPDATE, path,data,noticeListener);
                }
            }
        });
       }
       childListener = listeners.get(noticeListener);
       return childListener;
    }
    
    
    
    public void destory() {
        try {
            zkClient.close();
        } catch (Exception e) {
            LOGGER.warn("Failed to close zookeeper client " + getAppNode() + ", cause: " + e.getMessage(), e);
        }
       
    }

    

    
    
    

   
   
    

}

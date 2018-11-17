package com.xiangrikui.hulk.core.zookeeper.curator;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.core.context.AppContext;
import com.xiangrikui.hulk.core.exception.ZkException;
import com.xiangrikui.hulk.core.zookeeper.ChildListener;
import com.xiangrikui.hulk.core.zookeeper.DataListener;
import com.xiangrikui.hulk.core.zookeeper.StateListener;
import com.xiangrikui.hulk.core.zookeeper.TreeChangeListener;
import com.xiangrikui.hulk.core.zookeeper.ZookeeperClient;
import com.xiangrikui.hulk.core.zookeeper.serializer.SerializableSerializer;
import com.xiangrikui.hulk.core.zookeeper.serializer.ZkSerializer;
import com.xiangrikui.hulk.core.zookeeper.support.AbstractZookeeperClient;

/**
 * 创建时间：2017年3月3日
 * <p>修改时间：2017年3月3日
 * <p>类说明：CuratorZookeeper客户端实现类
 * 
 * @author jerry
 * @version 1.0
 */
public class CuratorZookeeperClient extends AbstractZookeeperClient<CuratorZookeeperClient.PathChildrenListener> implements ZookeeperClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorZookeeperClient.class);
    private final CuratorFramework client;
    
    private final ZkSerializer zkSerializer;
    
    public CuratorZookeeperClient(AppContext hulkAppContext){
        //从hulk配置中心上下文中获取zk地址信息
        String zkRegiesterAddress = hulkAppContext.getAppConfig().getRegiestZkAddress();
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(zkRegiesterAddress)
                .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 3000))
                .connectionTimeoutMs(5000);
                
        client = builder.build();
        //添加curator zookeeper 连接状态监听
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState state) {
                if(state == ConnectionState.LOST){
                    CuratorZookeeperClient.this.stateChanged(StateListener.DISCONNECTED);
                }else if(state == ConnectionState.CONNECTED){
                    CuratorZookeeperClient.this.stateChanged(StateListener.CONNECTED);
                }else if(state == ConnectionState.RECONNECTED){
                    CuratorZookeeperClient.this.stateChanged(StateListener.RECONNECTED);
                }else if(state == ConnectionState.SUSPENDED){
                    CuratorZookeeperClient.this.stateChanged(StateListener.DISCONNECTED);
                }
            }
        });
        
        zkSerializer = new SerializableSerializer();
        
        client.start();
                 
                
    }

    @Override
    protected String createPersistent(String path, boolean sequential) {
        try {
            if(sequential){
                return client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path); 
            }else{
                return client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return path;
        }
    }

    @Override
    protected String createPersistent(String path, Object data, boolean sequential) {
        try {
            byte[] zkDataBytes;
            if(data instanceof Serializable){
                zkDataBytes = zkSerializer.serializer(data);
            }else{
                zkDataBytes = (byte[]) data;
            }
            if(sequential){
                return client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path, zkDataBytes);
            }else{
                return client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path,zkDataBytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    protected String createEphemeral(String path, boolean sequential) {
        try {
            if(sequential){
                return client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
            }else{
                return client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
        
    }

    @Override
    protected String createEphemeral(String path, Object data, boolean sequential) {
        try {
            byte[] zkDataBytes;
            if(data instanceof Serializable){
                zkDataBytes = zkSerializer.serializer(data);
            }else{
                zkDataBytes = (byte[]) data;
            }
            if(sequential){
                return client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path,zkDataBytes);
            }else{
                return client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path,zkDataBytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
    
    @Override
    public boolean delete(String path) {
        try {
            client.delete().forPath(path);
            return true;
        }catch(KeeperException.NoNodeException e){
            return true;
        }catch (Exception e) {
            throw new IllegalStateException(e.getMessage(),e);
        }
    }


    @Override
    public boolean exists(String path) {
        try {
            return client.checkExists().forPath(path) != null;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(),e);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T> T getData(String path) {
        try {
            return (T) zkSerializer.deserializer(client.getData().forPath(path));
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(),e);
        }
    }


    @Override
    public void setData(String path, Object data) {
        byte[] zkDataBytes;
        if(data instanceof Serializable){
            zkDataBytes = zkSerializer.serializer(data);
        }else{
            zkDataBytes = (byte[]) data;
        }
        try {
            client.setData().forPath(path,zkDataBytes);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(),e);
        }
    }


    @Override
    public List<String> getChildren(String path) {
        try {
            return client.getChildren().forPath(path);
        } catch (KeeperException.NoNodeException e) {
            return null;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
    
    
    
    

    

    @Override
    protected PathChildrenListener createNodeChangeListener(String path, ChildListener nodeChangeListener) {
        return new PathChildrenListener(path,nodeChangeListener);
    }

    @Override
    protected void addTargetNodeChangeListener(String path, PathChildrenListener listener) {
        listener.startListener();
    }

    @Override
    protected void removeTargetNodeChangeListener(String path, PathChildrenListener listener) {
        listener.stopListener();
    }

    @Override
    protected void doClose() {
        client.close();
    }

    @Override
    public boolean isConnected() {
        return client.getZookeeperClient().isConnected();
    }
   
    
    public class PathChildrenListener{
        private PathChildrenCache childrenCache;
        private PathChildrenCacheListener childrenCacheListener;
        private AtomicBoolean start = new AtomicBoolean(false);
        
        public PathChildrenListener(String path,final ChildListener listener){
            childrenCache = new PathChildrenCache(client, path, true);
            childrenCacheListener = new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    switch(event.getType()){
                        case CHILD_ADDED:
                        case CHILD_REMOVED:
                        case CHILD_UPDATED:
                            //得到本次节点变更的类型、路径、内容
                            String changeEventType = event.getType().name();
                            String changeNodePath = event.getData().getPath();
                            Object changeData = "";
                            if(event.getData().getData()!=null && CuratorZookeeperClient.this.exists(changeNodePath)){
                                changeData = CuratorZookeeperClient.this.getData(changeNodePath);
                            }
                            
                            listener.childChanged(changeEventType, changeNodePath, changeData);
                        default:
                            break;
                    }
                }
            };
        }
        
        public void startListener(){
            try {
                if(start.compareAndSet(false, true)){
                    childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
                    childrenCache.getListenable().addListener(childrenCacheListener);
                }
            } catch (Exception e) {
               throw new ZkException(e);
            }
        }
        
        public void stopListener(){
            try {
                if(start.compareAndSet(true, false)){
                    childrenCache.getListenable().removeListener(childrenCacheListener);
                    childrenCache.clear();
                    childrenCache.close();
                }
            } catch (Exception e) {
                throw new ZkException(e);
            }
        }
    }
    
    public class NodeDataListener{
        private NodeCache nodeCache;
        private NodeCacheListener nodeCacheListener;
        
        public NodeDataListener(String path,final DataListener listener){
            nodeCache = new NodeCache(client, path);
            nodeCacheListener  = new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    String path = nodeCache.getCurrentData().getPath();
                    Object data = nodeCache.getCurrentData().getData();
                    if(data == null){
                        listener.dataDeleted(path);
                    }else{
                        listener.dataChange(path, data);
                    }
                }
            };
        }
        
        public void startListener(){
            try {
                nodeCache.start(true);
                nodeCache.getListenable().addListener(nodeCacheListener);
            } catch (Exception e) {
                throw new ZkException(e);
            }
        }
        
        public void stopListener(){
            try {
                nodeCache.getListenable().removeListener(nodeCacheListener);
                nodeCache.close();
            } catch (Exception e) {
                throw new ZkException();
            }
        }
    }
    
    public class TreeDataListener{
        
        private TreeCache treeCache;
        private TreeCacheListener treeCacheListener;
        
        public TreeDataListener(String path,final TreeChangeListener listener){
            treeCache = new TreeCache(client, path);
            treeCacheListener = new TreeCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                    switch (event.getType()) {
                        case NODE_ADDED:
                        case NODE_REMOVED:
                        case NODE_UPDATED:
                           String path = event.getData().getPath();
                           Object data = event.getData().getData();
                           listener.nodeChange(path, data); 
                        default:
                            break;
                    }
                }
            };
        }
        
        public void startListener(){
            try {
                treeCache.start();
                treeCache.getListenable().addListener(treeCacheListener);
            } catch (Exception e) {
                throw new ZkException(e);
            }
        }
        
        public void stopListener(){
            try {
                treeCache.getListenable().removeListener(treeCacheListener);
                treeCache.close();
            } catch (Exception e) {
                throw new ZkException(e);
            }
        }
        
    }

   
   

   
   
    

}

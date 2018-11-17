package com.xiangrikui.hulk.core;

import java.io.Serializable;

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
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

import com.xiangrikui.hulk.core.context.AppContext;
import com.xiangrikui.hulk.core.zookeeper.ZookeeperClient;
import com.xiangrikui.hulk.core.zookeeper.ZookeeperTransport;
import com.xiangrikui.hulk.core.zookeeper.curator.CuratorZookeeperClient;
import com.xiangrikui.hulk.core.zookeeper.serializer.SerializableSerializer;
import com.xiangrikui.hulk.core.zookeeper.serializer.ZkSerializer;

/**
 * 创建时间：2017年3月10日
 * <p>修改时间：2017年3月10日
 * <p>类说明：
 * 
 * @author jerry
 * @version 1.0
 */
public class ZookeeperTest {
    
    public static void main(String[] args) throws Exception {
        String ROOT_PATH = "/hulk/hulk-example_dev_1.0.0";
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.6.61:2181")
                .connectionTimeoutMs(5000)
                .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
                .build();
        client.start();
        
        final NodeCache nodeCache  = new NodeCache(client, ROOT_PATH);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("node change path:"+nodeCache.getCurrentData().getPath());
                System.out.println("node change content:"+nodeCache.getCurrentData().getData());
            }
        });
        
        /*ZookeeperClient transport = new CuratorZookeeperClient(hulkAppContext);*/
        
        /*final PathChildrenCache pathChildrenCache = new PathChildrenCache(client,ROOT_PATH,true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                    case CHILD_REMOVED:
                    case CHILD_UPDATED:
                        System.out.println("变更路径:"+event.getData().getPath());
                        System.out.println("变更内容:"+new String(event.getData().getData()));
                    default:
                        break;
                }
            }
        });*/
        /*
        final NodeCache nodeCache = new NodeCache(client, ROOT_PATH+"/1.2.2");
        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("current path = "+nodeCache.getCurrentData().getPath());
                System.out.println("current data" + new String(nodeCache.getCurrentData().getData()));
            }
        });*/
        
        
        /*final TreeCache treeCache = new TreeCache(client, ROOT_PATH);
        treeCache.start();
        treeCache.getListenable().addListener(new TreeCacheListener() {
            
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case NODE_ADDED:
                        System.out.println("数据变更:"+ZKPaths.getNodeFromPath(event.getData().getPath())+",内容为"
                                +new String(event.getData().getData()));
                    case NODE_REMOVED:
                        System.out.println("数据变更:"+ZKPaths.getNodeFromPath(event.getData().getPath())+",内容为"
                                +new String(event.getData().getData()));
                    case NODE_UPDATED:
                        System.out.println("数据变更:"+ZKPaths.getNodeFromPath(event.getData().getPath())+",内容为"
                                +new String(event.getData().getData()));

                    default:
                        break;
                }
            }
        });*/
        Object data = "192.168.8.333";
        ZkSerializer zkSerializer = new SerializableSerializer();
        byte[] zkDataBytes;
        if(data instanceof Serializable){
            zkDataBytes = zkSerializer.serializer(data);
        }else{
            zkDataBytes = (byte[]) data;
        }
        
        client.setData().forPath(ROOT_PATH+"/redis.hostname", zkDataBytes);
        
        //client.create().forPath(ROOT_PATH+"/name", zkDataBytes);
        System.out.println("Read name value:"+zkSerializer.deserializer(client.getData().forPath(ROOT_PATH+"/test")));
        /*client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(ROOT_PATH+"/1.3.7");
        client.setData().forPath(ROOT_PATH+"/1.2.2", "jerry".getBytes());
        System.out.println("再次获取1.2.2的值"+ new String(client.getData().forPath(ROOT_PATH+"/1.2.2")));
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(ROOT_PATH+"/1.3.8","hello".getBytes());
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(ROOT_PATH+"/1.3.9","james".getBytes());
        client.setData().forPath(ROOT_PATH+"/1.2.9", "jerry".getBytes());*/
        
        Thread.sleep(5000);
        client.close();
        }
        
        
    
}

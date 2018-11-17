package com.xiangrikui.hulk.core.zookeeper;

import java.util.List;

/**
 * 创建时间：2017年3月3日
 * <p>修改时间：2017年3月3日
 * <p>类说明：定义zookeeper客户端接口,可扩展多个客户端实现
 * 
 * @author jerry
 * @version 1.0
 */
public interface ZookeeperClient {
    
    String create(String path,boolean ephemeral,boolean sequential);
    
    String create(String path,Object data,boolean ephemeral,boolean sequential);
    
    boolean delete(String path);
    
    boolean exists(String path);
    
    <T> T getData(String path);
    
    void setData(String path,Object data);
    
    List<String> getChildren(String path);
    
    void addStateListener(StateListener listener);

    void removeStateListener(StateListener listener);
    
    void addNodeChangeListener(String path,ChildListener nodeChangeListener);
    
    void removeNodeChangeListener(String path,ChildListener nodeChangeListener);
    
    boolean isConnected();
    
    void close();
    
    
}

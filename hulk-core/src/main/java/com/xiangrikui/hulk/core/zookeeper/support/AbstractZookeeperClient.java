package com.xiangrikui.hulk.core.zookeeper.support;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.core.zookeeper.ChildListener;
import com.xiangrikui.hulk.core.zookeeper.StateListener;
import com.xiangrikui.hulk.core.zookeeper.ZookeeperClient;

/**
 * 创建时间：2017年3月3日
 * <p>修改时间：2017年3月3日
 * <p>类说明：Zookeeper客戶端抽象类
 * 
 * @author jerry
 * @version 1.0
 */
public abstract class AbstractZookeeperClient<TargetNodeChangeListener> implements ZookeeperClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractZookeeperClient.class);
   
    private final Set<StateListener> stateListeners = new CopyOnWriteArraySet<StateListener>();
    
    private final ConcurrentMap<String, ConcurrentMap<ChildListener, TargetNodeChangeListener>> changeListeners
                        = new ConcurrentHashMap<String, ConcurrentMap<ChildListener,TargetNodeChangeListener>>();
    
    
    private volatile boolean closed = false;
    
    @Override
    public String create(String path, boolean ephemeral, boolean sequential) {
        
        if(ephemeral){
            return createEphemeral(path,sequential);
        }else{
            return createPersistent(path, sequential);
        }
    }
   
    @Override
    public String create(String path, Object data, boolean ephemeral, boolean sequential) {
        
        if(ephemeral){
            return createEphemeral(path, data, sequential);
        }else{
            return createPersistent(path, data,sequential);
        }
    }
   
    
    
    public Set<StateListener> getSessionListeners(){
        return stateListeners;
    }
    
    public void addStateListener(StateListener stateListener){
        stateListeners.add(stateListener);
    }
    
    public void removeStateListener(StateListener stateListener){
        stateListeners.remove(stateListener);
    }
    
    
    

    @Override
    public void close() {
        if(closed){
            return;
        }
        closed = true;
        try {
            doClose();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(),e);
        }
        
    }

    protected void stateChanged(int state){
        for(StateListener stateListener : getSessionListeners()){
            stateListener.stateChange(state);
        }
    }
    
    
    
    

    @Override
    public void addNodeChangeListener(String path,final ChildListener nodeChangeListener) {
       ConcurrentMap<ChildListener, TargetNodeChangeListener> listeners =  changeListeners.get(path);
       if(listeners == null){
           changeListeners.putIfAbsent(path, new ConcurrentHashMap<ChildListener,TargetNodeChangeListener>());
           listeners = changeListeners.get(path);
       }
       TargetNodeChangeListener targetListener = listeners.get(nodeChangeListener);
       if(targetListener == null){
           listeners.putIfAbsent(nodeChangeListener, createNodeChangeListener(path,nodeChangeListener));
           targetListener = listeners.get(nodeChangeListener);
       }
       addTargetNodeChangeListener(path, targetListener);
       
    }

    @Override
    public void removeNodeChangeListener(String path,ChildListener nodeChangeListener) {
        ConcurrentMap<ChildListener, TargetNodeChangeListener> listeners = changeListeners.get(path);
        if(listeners != null){
            TargetNodeChangeListener targetListener = listeners.remove(nodeChangeListener);
            if(targetListener != null){
                removeTargetNodeChangeListener(path, targetListener);
            }
        }
    }

    protected abstract void doClose();
    
    protected abstract String createPersistent(String path,boolean sequential);
    
    protected abstract String createPersistent(String path,Object data,boolean sequential);
    
    protected abstract String createEphemeral(String path,boolean sequential);

    protected abstract String createEphemeral(String path,Object data,boolean sequential);
    
    protected abstract TargetNodeChangeListener createNodeChangeListener(String path,ChildListener nodeChangeListener);
    
    protected abstract void addTargetNodeChangeListener(String path,TargetNodeChangeListener listener);
    
    protected abstract void removeTargetNodeChangeListener(String path,TargetNodeChangeListener listener);
}

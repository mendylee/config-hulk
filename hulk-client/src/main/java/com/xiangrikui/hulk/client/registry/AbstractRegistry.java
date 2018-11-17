package com.xiangrikui.hulk.client.registry;

import java.util.HashSet;
import java.util.Set;

import com.xiangrikui.hulk.client.registry.node.AppNode;

/**
 * 创建时间：2017年3月9日
 * <p>修改时间：2017年3月9日
 * <p>类说明：应用节点注册抽象类
 * 
 * @author jerry
 * @version 1.0
 */
public abstract class AbstractRegistry implements Registry {

    private AppNode appNode;
    
    private final  Set<AppNode> registered = new HashSet<AppNode>();
    
    public void register(AppNode app) {
        if(app == null){
            throw new IllegalArgumentException("regiester app is null");
        }
        registered.add(app);
        doRegiester(app);
    }

    public void unregister(AppNode app) {
        if(app == null){
            throw new IllegalArgumentException("unregister app is null");
        }
        registered.remove(app);
    }

    public void subscribe(AppNode app,NoticeListener listener) {
        if(app == null){
            throw new IllegalArgumentException("subscribe app is null");
        }
        if(listener == null){
            throw new IllegalArgumentException("subscribe listener is null");
        }
        doSubScribe(app,listener);
    }

    public void unsubscribe(AppNode app,NoticeListener listener) {
        //TODO 暂不实现解除订阅
    }
    
    protected void notice(NoticeEvent noticeEvent,String nodePath,Object value,NoticeListener noticeListener){
        if(noticeEvent == null){
            throw new IllegalArgumentException("notice event is null");
        }
        if(noticeListener == null){
            throw new IllegalArgumentException("notice listener is null");
        }
        noticeListener.notice(noticeEvent, nodePath,value);
    }
    
    
    public AppNode getAppNode() {
        return appNode;
    }

    public void setAppNode(AppNode appNode) {
        this.appNode = appNode;
    }

    public abstract void doRegiester(AppNode app);
    
    public abstract void doSubScribe(AppNode app,NoticeListener listener);
    
}

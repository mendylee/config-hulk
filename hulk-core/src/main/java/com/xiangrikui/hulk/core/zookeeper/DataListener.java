package com.xiangrikui.hulk.core.zookeeper;

/**
 * 创建时间：2017年3月7日
 * <p>修改时间：2017年3月7日
 * <p>类说明：指定节点数据变更Listener监听
 * 
 * @author jerry
 * @version 1.0
 */
public interface DataListener {
    
    void dataChange(String dataPath,Object data) throws Exception;
    
    void dataDeleted(String dataPath) throws Exception;
}

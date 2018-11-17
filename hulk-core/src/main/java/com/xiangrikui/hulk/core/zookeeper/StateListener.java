package com.xiangrikui.hulk.core.zookeeper;

/**
 * 创建时间：2017年3月3日
 * <p>修改时间：2017年3月3日
 * <p>类说明：zookeeer状态监听接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface StateListener {
    int DISCONNECTED = 0;
    
    int CONNECTED = 1;
    
    int RECONNECTED = 2;
    
    void stateChange(int connectionState);
}

package com.xiangrikui.hulk.core.zookeeper;

/**
 * 创建时间：2017年3月13日
 * <p>修改时间：2017年3月13日
 * <p>类说明：树形结构的子节点监听(无限监听)
 * 
 * @author jerry
 * @version 1.0
 */
public interface TreeChangeListener {
    
    void nodeChange(String path,Object data);
}

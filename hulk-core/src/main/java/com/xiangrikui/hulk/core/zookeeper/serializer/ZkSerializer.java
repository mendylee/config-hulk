package com.xiangrikui.hulk.core.zookeeper.serializer;

/**
 * 创建时间：2017年3月7日
 * <p>修改时间：2017年3月7日
 * <p>类说明：Zookeeper序列化接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface ZkSerializer {
    
    public byte[] serializer(Object data);
    
    public Object deserializer(byte[] bytes);
}

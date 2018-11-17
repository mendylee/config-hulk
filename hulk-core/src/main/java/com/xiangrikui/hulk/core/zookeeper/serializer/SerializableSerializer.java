package com.xiangrikui.hulk.core.zookeeper.serializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.xiangrikui.hulk.core.common.io.UnsafeByteArrayInputStream;
import com.xiangrikui.hulk.core.common.io.UnsafeByteArrayOutputStream;
import com.xiangrikui.hulk.core.exception.ZkSerializerException;

/**
 * 创建时间：2017年3月7日
 * <p>修改时间：2017年3月7日
 * <p>类说明：对象-byte序列化实现类
 * 
 * @author jerry
 * @version 1.0
 */
public class SerializableSerializer implements ZkSerializer {

    
    @Override
    public byte[] serializer(Object data) {
        try {
            if (data == null) {
                return null;
            }
            UnsafeByteArrayOutputStream byteArrayOS = new UnsafeByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(byteArrayOS);
            stream.writeObject(data);
            stream.close();
            return byteArrayOS.toByteArray();
        } catch (Exception e) {
            throw new ZkSerializerException(e);
        }
    }

    
    @SuppressWarnings("resource")
    @Override
    public Object deserializer(byte[] bytes) {
        if(bytes == null){
            return null;
        }
        try {
            return new ObjectInputStream(new UnsafeByteArrayInputStream(bytes)).readObject();
        } catch (ClassNotFoundException e) {
            throw new ZkSerializerException("Unable to find object class.", e);
        } catch (IOException e) {
            throw new ZkSerializerException(e);
        }
        
    }

}

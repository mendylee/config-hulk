package com.xiangrikui.hulk.client.test.conf;

import java.io.Serializable;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.xiangrikui.hulk.core.zookeeper.serializer.SerializableSerializer;
import com.xiangrikui.hulk.core.zookeeper.serializer.ZkSerializer;


/**
 * 创建时间：2017年5月17日
 * <p>修改时间：2017年5月17日
 * <p>类说明：Hulk配置中心集成测试类
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkConfigTest {
    
    private CuratorFramework client;
    
    @Before
    public void connection(){
        client = CuratorFrameworkFactory.builder()
                .connectString("192.168.6.60:2181,192.168.6.61:2181,192.168.6.62:2181")
                .connectionTimeoutMs(5000)
                .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
                .build();
        client.start();
        System.out.println("zookeeper start success");
    }
    
   
    private String getRootPath(){
        String version = "1.0.0";
        String appname = "hulk-example";
        String env = "dev";
        String ROOT_PATH = "/hulk/"+appname+"_"+env+"_"+version;
        return ROOT_PATH;
    }
    
    @Test
    public void update() throws Exception{
        String rootPath = getRootPath();
        writeData(rootPath, "test", "5000");
        String value = (String) getData("test");
        Assert.assertEquals(value, "5000");
        client.close();
    }
   
    private void writeData(String rootPath,String keyName,Object data) throws Exception {
        String path =rootPath+"/"+keyName;
        Object rootobj = client.checkExists().forPath(rootPath);
        if(rootobj==null){
            client.create().forPath(rootPath);
        }
        Object obj = client.checkExists().forPath(path);
        if(obj==null){
            client.create().forPath(path);
        }
        
        
        ZkSerializer zkSerializer = new SerializableSerializer();
        byte[] zkDataBytes;
        if(data instanceof Serializable){
            zkDataBytes = zkSerializer.serializer(data);
        }else{
            zkDataBytes = (byte[]) data;
        }
        client.setData().forPath(path, zkDataBytes);
        
    }
    
    private Object getData(String path) throws Exception {
        String rootPath = getRootPath();
        ZkSerializer zkSerializer = new SerializableSerializer();
        Object value = zkSerializer.deserializer(client.getData().forPath(rootPath+"/"+path));
        System.out.println("Read New Value:"+ value);
        return value;
        
    }
    
}

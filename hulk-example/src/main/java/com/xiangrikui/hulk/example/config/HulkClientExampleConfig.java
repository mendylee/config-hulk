package com.xiangrikui.hulk.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.xiangrikui.hulk.client.common.annotation.HulkConfigItem;

/**
 * 创建时间：2017年3月9日
 * <p>修改时间：2017年3月9日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
@Configuration
public class HulkClientExampleConfig  {
    
    
    
    @HulkConfigItem(key="redis.hostname",name="redis.hostname")
    public String redisHostName;
    
    @HulkConfigItem(key="request.timeout",name="request.timeout")
    public String requestTimeOut;
    
    /*@HulkConfigItem(key="serverTimeOut",name="serverTimeOut")
    @Value("${serverTimeOut}")
    public String serverTimeOut;
    
    @HulkConfigItem(key="name",name="name")
    @Value("${name}")
    public String name;
    
    @HulkConfigItem(key="num",name="num")
    public static String num = "28";
    
    @HulkConfigItem(key="age",name="age")
    public int age = 20;
    
    @HulkConfigItem(key="online",name="online")
    public boolean online = false;*/
    
}

package com.xiangrikui.hulk.client.common;

import java.util.concurrent.atomic.AtomicBoolean;

import com.xiangrikui.hulk.client.HulkClient;
import com.xiangrikui.hulk.client.spring.properties.HulkClientProperties;
import com.xiangrikui.hulk.core.common.PropertiesConfigurationFactory;

/**
 * 创建时间：2017年3月23日
 * <p>修改时间：2017年3月23日
 * <p>类说明：hulk配置中心客户端构造器
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkClientBuilder {
    
    private AtomicBoolean build = new AtomicBoolean(false);
    
    /**
     * 配置文件路径
     */
    private String[] locations;
    
    public final  HulkClient build(){
        if(!build.compareAndSet(false, true)){
            throw new IllegalArgumentException("HulkClientBuilder Already Built");
        }
        
        checkLocations();
        
        HulkClientProperties properties = PropertiesConfigurationFactory
                                    .createPropertiesConfiguration(HulkClientProperties.class, locations);
       
        HulkClient hulkClient = buildByProperties(properties);
        
        return hulkClient;
    }
    
    public final HulkClientBuilder setProperteisLocations(String... locations){
        if(locations == null || locations.length == 0){
            throw new IllegalArgumentException("locations can not null");
        }
        this.locations = locations;
        return this;
    }

    public static HulkClient buildByProperties(HulkClientProperties properties){
        HulkClient hulkClient = new HulkClient();
        hulkClient.setRegistryAddress(properties.getRegistryAddress());
        hulkClient.setScanPackages(properties.getScanPackages());
        hulkClient.setAppName(properties.getAppName());
        hulkClient.setEnv(properties.getEnv());
        hulkClient.setVersion(properties.getVersion());
        hulkClient.setUseLocalConfigUpload(properties.getUseLocalConfigUpload());
        return hulkClient;
    }
    
    private void checkLocations(){
        if(locations == null || locations.length == 0){
            throw new IllegalArgumentException("locations can not null");
        }
    }
}

package com.xiangrikui.hulk.client.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import com.xiangrikui.hulk.client.HulkClient;
import com.xiangrikui.hulk.client.common.HulkClientBuilder;
import com.xiangrikui.hulk.client.spring.properties.HulkClientProperties;
import com.xiangrikui.hulk.core.common.PropertiesConfigurationFactory;
import com.xiangrikui.hulk.core.common.util.Assert;


/**
 * 创建时间：2017年3月8日
 * <p>修改时间：2017年3月8日
 * <p>类说明：Hulk配置中心FactoryBean核心类
 * 
 * @author jerry
 * @version 1.0
 */


public class HulkClientFactoryBean implements ApplicationContextAware,PriorityOrdered,FactoryBean<HulkClient>,
                InitializingBean,DisposableBean{

    /**
     * 配置中心客户端对象
     */
    private HulkClient hulkClient;
    
    /**
     * 配置中心注册地址
     */
    private String registryAddress;
    
    /**
     * 扫描配置注解包的路径 
     */
    private String scanPackages;
    
    /**
     * 应用名称
     */
    private String appName;
    
    /**
     * 环境
     */
    private String env;
    
    /**
     * 版本
     */
    private String version;
    
    /**
     * 
     */
    private boolean useLocalConfigUpload = false;
    
    /**
     * 启动标志
     */
    private boolean started;
    
    /**
     * 配置文件路径
     */
    private String[] locations;
    
    
    private ApplicationContext context;
    
    
    
    
    
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.context = applicationContext;
    }
    
    
    /**
     * 启动方法,单独定义的好处可以在spring集成的项目中通过init属性指定方法
     */
    public void start(){
        if(!started){
            hulkClient.start();
            started = true;
        }
    }
    
    public void destroy() throws Exception {
        hulkClient.destory();
    }
    
    
    public void afterPropertiesSet() throws Exception {
        
        HulkClientProperties hulkClientProperties;
        //1.客户端未指定配置文件路径按设置的进行properties赋值
        if(locations ==null || locations.length ==0 ){
            hulkClientProperties = new HulkClientProperties();
            hulkClientProperties.setRegistryAddress(registryAddress);
            hulkClientProperties.setAppName(appName);
            hulkClientProperties.setEnv(env);
            hulkClientProperties.setVersion(version);
            hulkClientProperties.setScanPackages(scanPackages);
            hulkClientProperties.setUseLocalConfigUpload(useLocalConfigUpload);
        }else{
            hulkClientProperties = PropertiesConfigurationFactory.createPropertiesConfiguration(HulkClientProperties.class, locations);
        }
        //2.根据配置中心的属性信息通过构造器创建hulkClient客户端
        hulkClient = HulkClientBuilder.buildByProperties(hulkClientProperties);
        hulkClient.setApplicationContext(context);
    }
    
    
    

    
    
    public HulkClient getObject() throws Exception {
        return hulkClient;
    }

   
    public Class<?> getObjectType() {
        return HulkClient.class;
    }

    public boolean isSingleton() {
        return true;
    }
    
    public void checkProperties(){
        Assert.hasText(registryAddress,"registryAddress must have value.");
        Assert.hasText(appName, "appName must have value.");
        Assert.hasText(env, "env must have value.");
        Assert.hasText(version,"version must hava value.");
    }
    
    
    
    public void setRegisryAddress(String registryAddress){
        this.registryAddress = registryAddress;
    }

    public void setScanPackages(String scanPackages){
        this.scanPackages = scanPackages;
    }
    
    public void setAppName(String appName){
        this.appName = appName;
    }
    
    public void setEnv(String env){
        this.env = env;
    }
    
    public void setVersion(String version){
        this.version = version;
    }

    public void setLocations(String... locations) {
        this.locations = locations;
    }

    public void setUseLocalConfigUpload(boolean useLocalConfigUpload) {
        this.useLocalConfigUpload = useLocalConfigUpload;
    }

    
    


    
    


    
    

    
    

   
    
    

    
}

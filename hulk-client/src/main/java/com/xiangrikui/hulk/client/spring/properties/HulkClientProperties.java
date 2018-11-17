package com.xiangrikui.hulk.client.spring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.xiangrikui.hulk.core.common.util.Assert;

/**
 * 创建时间：2017年3月15日
 * <p>修改时间：2017年3月15日
 * <p>类说明：hulk配置中心属性类
 * 
 * @author jerry
 * @version 1.0
 */
@ConfigurationProperties(prefix = "hulk")
public class HulkClientProperties {

    /**
     * 注册中心地址
     */
    private String registryAddress;
    
    /**
     * 本地副本路径
     */
    private String localConfigPath;
    
    
    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用环境
     */
    private String env;
    
    /**
     * 应用环境
     */
    private String version;
    
    /**
     * 扫描包路径
     */
    private String scanPackages;
    
    /**
     * 当配置中心不存在配置时,使用本地或对象的值上传并添加到配置中心(迁移)
     * 默认不上传,配置统一在后台添加上传
     */
    private boolean useLocalConfigUpload = false;

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public String getLocalConfigPath() {
        return localConfigPath;
    }

    public void setLocalConfigPath(String localConfigPath) {
        this.localConfigPath = localConfigPath;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
    
    
    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setScanPackages(String scanPackages){
        this.scanPackages = scanPackages;
    }
    
    public String getScanPackages(){
        return scanPackages;
    }
    

    public boolean getUseLocalConfigUpload() {
        return useLocalConfigUpload;
    }

    public void setUseLocalConfigUpload(boolean useLocalConfigUpload) {
        this.useLocalConfigUpload = useLocalConfigUpload;
    }

    public void checkProperties() {
        Assert.hasText(getRegistryAddress(), "registryAddress must have value.");
        Assert.hasText(getEnv(),"env must have value.");
        Assert.hasText(getAppName(),"appName must hava value.");
        Assert.hasText(getVersion(),"version must have value.");
    }

}

package com.xiangrikui.hulk.core.context;

/**
 * 创建时间：2017年3月7日
 * <p>修改时间：2017年3月7日
 * <p>类说明：接入配置中心的通用应用配置信息
 * 
 * @author jerry
 * @version 1.0
 */

public class AppConfig {
    /**
     * 应用ID唯一标识
     */
    private String appId;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 所属环境
     */
    private String env;
    /**
     * 当前版本
     */
    private String version;
    /**
     * zookeeper注册中心地址
     */
    private String regiestZkAddress;
    
    /**
     * 设置是否配置中心不存在值时用本地的值上传至配置中心
     */
    private boolean useLocalConfigUpload;
    
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
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
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getRegiestZkAddress() {
        return regiestZkAddress;
    }
    public void setRegiestZkAddress(String regiestZkAddress) {
        this.regiestZkAddress = regiestZkAddress;
    }
    public boolean isUseLocalConfigUpload() {
        return useLocalConfigUpload;
    }
    public void setUseLocalConfigUpload(boolean useLocalConfigUpload) {
        this.useLocalConfigUpload = useLocalConfigUpload;
    }
    @Override
    public String toString() {
        return "AppConfig [appId=" + appId + ", appName=" + appName + ", env=" + env + ", version=" + version + ", regiestZkAddress=" + regiestZkAddress + ",useLocalConfigUpload=" + useLocalConfigUpload + "]";
    }
    
    
    
    
}

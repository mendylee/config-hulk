package com.xiangrikui.hulk.web.vo;

/**
 * 创建时间：2017年5月18日
 * <p>修改时间：2017年5月18日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class ConfigVO {
    
    
    private Long configId;
    
    private Long appId;
    
    private String version;
    
    private Long envId;
    
    private String content;
    
    private String key;
    
    private String value;
    
    private String mode;
    
    
    
    
    
    public Long getConfigId() {
        return configId;
    }
    public void setConfigId(Long configId) {
        this.configId = configId;
    }
    public Long getAppId() {
        return appId;
    }
    public void setAppId(Long appId) {
        this.appId = appId;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public Long getEnvId() {
        return envId;
    }
    public void setEnvId(Long envId) {
        this.envId = envId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    
    
    
}

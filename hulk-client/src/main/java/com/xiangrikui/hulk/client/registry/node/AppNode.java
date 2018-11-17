package com.xiangrikui.hulk.client.registry.node;

/**
 * 创建时间：2017年3月9日
 * <p>修改时间：2017年3月9日
 * <p>类说明：集成hulk配置中心的应用节点信息
 * 
 * @author jerry
 * @version 1.0
 */
public class AppNode{
    
    //应用ID
    private String appId;
    //应用名称
    private String appName;
    //应用环境
    private String env;
    //应用版本
    private String version;
    
    //节点key
    private String key;
    
    //节点值
    private Object value;
    
    //节点完整路径信息
    private String nodeFullPath;
    

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * 获取应用节点完整路径信息
     * @return
     */
    public String getAppFullInfo(){
        if(nodeFullPath == null){
            nodeFullPath = NodeUtils.getNodeFullPath(this);
        }
        return nodeFullPath;
    }
    
}

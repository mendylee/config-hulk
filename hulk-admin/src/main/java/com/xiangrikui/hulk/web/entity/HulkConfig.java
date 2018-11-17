package com.xiangrikui.hulk.web.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 创建时间：2017年5月16日
 * <p>修改时间：2017年5月16日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
@Entity
@Table(name="hulk_config",catalog="xrk_hulk")
public class HulkConfig {

    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "config_id")
    private Long configId;
    
    @Column(name = "type")
    private int type;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "value")
    private String value;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    private HulkApp app;
    
    @Column(name = "version")
    private String version;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "env_id", referencedColumnName = "env_id")
    private HulkEnv env;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @JSONField(format="yyyy-MM-dd HH:mm:ss") 
    private Date createdAt;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @JSONField(format="yyyy-MM-dd HH:mm:ss") 
    private Date updatedAt;


    public Long getConfigId() {
        return configId;
    }


    public void setConfigId(Long configId) {
        this.configId = configId;
    }


    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }


   

    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }


    


    


    public HulkApp getApp() {
        return app;
    }


    public void setApp(HulkApp app) {
        this.app = app;
    }


    

    public HulkEnv getEnv() {
        return env;
    }


    public void setEnv(HulkEnv env) {
        this.env = env;
    }


    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
    
    
}

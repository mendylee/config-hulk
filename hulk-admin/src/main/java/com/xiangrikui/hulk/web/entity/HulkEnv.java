package com.xiangrikui.hulk.web.entity;

import static javax.persistence.GenerationType.IDENTITY;

// Generated 2017-5-2 15:58:42 by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HulkEnv generated by hbm2java
 */
@Entity
@Table(name = "hulk_env", catalog = "xrk_hulk")
public class HulkEnv
{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "env_id", unique = true, nullable = false)
	private Long envId;
    
    @Column(name = "env_name", nullable = false, length = 50)
	private String envName;
    
    @Column(name = "description", nullable = false, length = 100)
	private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, length = 19)
	private Date createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", length = 19)
	private Date updatedAt;

    public Long getEnvId() {
        return envId;
    }

    public void setEnvId(Long envId) {
        this.envId = envId;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
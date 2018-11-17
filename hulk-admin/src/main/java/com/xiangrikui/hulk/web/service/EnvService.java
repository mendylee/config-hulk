package com.xiangrikui.hulk.web.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.xiangrikui.hulk.web.common.HulkResponse;
import com.xiangrikui.hulk.web.entity.HulkEnv;

/**
 * 创建时间：2017年5月2日
 * <p>修改时间：2017年5月2日
 * <p>类说明：环境管理Service接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface EnvService {
    
    List<HulkEnv> findAll();
    
    HulkResponse<HulkEnv> findAll(final String envName,Pageable pageable);
    
    HulkEnv findById(String envId);
    
    List<HulkEnv> findByEnvName(String envName);
    
    
}

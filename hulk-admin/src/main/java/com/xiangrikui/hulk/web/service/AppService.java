package com.xiangrikui.hulk.web.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.xiangrikui.hulk.web.common.HulkResponse;
import com.xiangrikui.hulk.web.entity.HulkApp;

/**
 * 创建时间：2017年5月3日
 * <p>修改时间：2017年5月3日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public interface AppService {
    
    List<HulkApp> findAll();
    
    HulkApp findById(Long appId);
    
    HulkResponse<HulkApp> findAll(final HulkApp hulkApp,Pageable pageable);
    
    void save(HulkApp hulkApp);
    
    void delete(Long appId);
}

package com.xiangrikui.hulk.web.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.xiangrikui.hulk.web.common.HulkResponse;
import com.xiangrikui.hulk.web.entity.HulkConfig;
import com.xiangrikui.hulk.web.vo.ConfigVO;

/**
 * 创建时间：2017年5月16日
 * <p>修改时间：2017年5月16日
 * <p>类说明：配置管理业务Service接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface ConfigService {

    void save(ConfigVO configVO) throws Exception;
    
    HulkConfig findById(Long configId);
    
    List<HulkConfig> findAll();
    
    HulkResponse<HulkConfig> findAll(final ConfigVO hulkConfig,Pageable pageable);
    
    void update(ConfigVO configVo);
    
    void synData(ConfigVO configVo);
}

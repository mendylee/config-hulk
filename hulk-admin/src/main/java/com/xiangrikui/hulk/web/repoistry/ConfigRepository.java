package com.xiangrikui.hulk.web.repoistry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.xiangrikui.hulk.web.entity.HulkConfig;

/**
 * 创建时间：2017年5月16日
 * <p>修改时间：2017年5月16日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public interface ConfigRepository extends CrudRepository<HulkConfig, Long> {

    Page<HulkConfig> findAll(Specification<HulkConfig> spec,Pageable pageable);
}

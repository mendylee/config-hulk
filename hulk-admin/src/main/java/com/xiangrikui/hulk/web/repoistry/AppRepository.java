package com.xiangrikui.hulk.web.repoistry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.xiangrikui.hulk.web.entity.HulkApp;

/**
 * 创建时间：2017年5月3日
 * <p>修改时间：2017年5月3日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public interface AppRepository extends CrudRepository<HulkApp, Long> {
    
    Page<HulkApp> findAll(Specification<HulkApp> spec,Pageable pageable);
}

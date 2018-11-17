package com.xiangrikui.hulk.web.repoistry;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.xiangrikui.hulk.web.entity.HulkEnv;

/**
 * 创建时间：2017年5月2日
 * <p>修改时间：2017年5月2日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public interface EnvRepository extends CrudRepository<HulkEnv, Long>  {

    Page<HulkEnv> findAll(Specification<HulkEnv> spec, Pageable pageable);
    
    List<HulkEnv> findByEnvName(String envName);
}

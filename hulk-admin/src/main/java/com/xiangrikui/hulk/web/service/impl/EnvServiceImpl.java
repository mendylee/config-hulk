package com.xiangrikui.hulk.web.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.xiangrikui.hulk.web.common.HulkResponse;
import com.xiangrikui.hulk.web.entity.HulkEnv;
import com.xiangrikui.hulk.web.repoistry.EnvRepository;
import com.xiangrikui.hulk.web.service.BaseService;
import com.xiangrikui.hulk.web.service.EnvService;

/**
 * 创建时间：2017年5月2日
 * <p>修改时间：2017年5月2日
 * <p>类说明：环境管理Service业务实现类
 * 
 * @author jerry
 * @version 1.0
 */
@Service
@Transactional
public class EnvServiceImpl extends BaseService<HulkEnv> implements EnvService {

    @Autowired
    private EnvRepository envRepository;
    
    @Override
    public HulkEnv findById(String envId) {
        return envRepository.findOne(Long.parseLong(envId));
    }
    @Override
    public List<HulkEnv> findAll() {
        return (List<HulkEnv>) envRepository.findAll();
    }
    
    @Override
    public List<HulkEnv> findByEnvName(String envName) {
        return envRepository.findByEnvName(envName);
    }
    
    @Override
    public HulkResponse<HulkEnv> findAll(final String envName, Pageable pageable) {
        Page<HulkEnv> record = envRepository.findAll(new Specification<HulkEnv>() {
            @Override
            public Predicate toPredicate(Root<HulkEnv> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = builder.conjunction();
                if(!Strings.isNullOrEmpty(envName)){
                    predicate.getExpressions().add(builder.equal(root.<String> get("envName"), envName));
                }
                return predicate;
            }
        }, pageable);
        if(record == null){
            return null;
        }else{
            return builderResponse(record);
        }
    }
    

    

}

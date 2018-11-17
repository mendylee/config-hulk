package com.xiangrikui.hulk.web.service.impl;

import java.util.Date;
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
import com.xiangrikui.hulk.web.entity.HulkApp;
import com.xiangrikui.hulk.web.repoistry.AppRepository;
import com.xiangrikui.hulk.web.service.AppService;
import com.xiangrikui.hulk.web.service.BaseService;

/**
 * 创建时间：2017年5月3日
 * <p>修改时间：2017年5月3日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
@Service
@Transactional
public class AppServiceImpl extends BaseService<HulkApp> implements AppService {

    @Autowired
    private AppRepository appRepository;
    
    @Override
    public List<HulkApp> findAll() {
        List<HulkApp> list = (List<HulkApp>) appRepository.findAll();
        return list;
    }

    @Override
    public HulkResponse<HulkApp> findAll(final HulkApp hulkApp, Pageable pageable) {
        Page<HulkApp> record = appRepository.findAll(new Specification<HulkApp>() {
            
            @Override
            public Predicate toPredicate(Root<HulkApp> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(!Strings.isNullOrEmpty(hulkApp.getAppName())){
                    predicate.getExpressions().add(cb.equal(root.get("appName"), hulkApp.getAppName()));
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
    

   
    @Override
    public void save(HulkApp hulkApp) {
        hulkApp.setCreatedAt(new Date());
        appRepository.save(hulkApp);
    }

    
    @Override
    public void delete(Long appId) {
        appRepository.delete(appId);
    }

   
    @Override
    public HulkApp findById(Long appId) {
        return appRepository.findOne(appId);
    }

}

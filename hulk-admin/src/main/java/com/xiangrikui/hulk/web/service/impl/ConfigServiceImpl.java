package com.xiangrikui.hulk.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xiangrikui.hulk.core.zookeeper.util.ZookeeperPathUtils;
import com.xiangrikui.hulk.web.common.HulkResponse;
import com.xiangrikui.hulk.web.common.MailHelper;
import com.xiangrikui.hulk.web.entity.HulkApp;
import com.xiangrikui.hulk.web.entity.HulkConfig;
import com.xiangrikui.hulk.web.entity.HulkEnv;
import com.xiangrikui.hulk.web.repoistry.AppRepository;
import com.xiangrikui.hulk.web.repoistry.ConfigRepository;
import com.xiangrikui.hulk.web.repoistry.EnvRepository;
import com.xiangrikui.hulk.web.service.BaseService;
import com.xiangrikui.hulk.web.service.ConfigService;
import com.xiangrikui.hulk.web.vo.ConfigVO;
import com.xiangrikui.hulk.web.zookeeper.config.ZookeeperDriver;
import com.xiangrikui.hulk.web.zookeeper.service.ZookeeperService;

/**
 * 创建时间：2017年5月16日
 * <p>修改时间：2017年5月16日
 * <p>类说明：配置管理业务Service
 * 
 * @author jerry
 * @version 1.0
 */
@Service
@Transactional
public class ConfigServiceImpl extends BaseService<HulkConfig> implements ConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServiceImpl.class);
    @Autowired
    private ConfigRepository configRepository;
    
    @Autowired
    private AppRepository appRepository;
    
    @Autowired
    private EnvRepository envRepository;
    
    @Autowired
    private ZookeeperService zkService;
    
    @Autowired
    private ZookeeperDriver zkDriver;

    @Autowired
    private MailHelper mailHeleper;
   
    @Override
    public void save(ConfigVO configVO) {
        try {
            HulkConfig hulkConfig = new HulkConfig();
            HulkApp app = appRepository.findOne(configVO.getAppId());
            HulkEnv env = envRepository.findOne(configVO.getEnvId());
            hulkConfig.setApp(app);
            hulkConfig.setEnv(env);
            hulkConfig.setVersion(configVO.getVersion());
            hulkConfig.setCreatedAt(new Date());
            hulkConfig.setUpdatedAt(new Date());
            //批量文本模式
            if("否".equals(configVO.getMode())){
                batchSave(configVO);
            }else{
                hulkConfig.setName(configVO.getKey());
                hulkConfig.setValue(configVO.getValue());
                configRepository.save(hulkConfig);
                //获取zookeeper节点路径
                String nodePath = ZookeeperPathUtils.getNodeFullPath(hulkConfig.getApp().getAppName(),
                                                                     hulkConfig.getEnv().getEnvName(), hulkConfig.getVersion(), hulkConfig.getName());
                //写入到zookeeper中
                zkService.saveZookeeperConfig(nodePath, hulkConfig.getValue());
            }
            
        } catch (Exception e) {
            LOGGER.error("Save Config Fail.",e);
            throw new RuntimeException("Add Config Error.",e);
            
        }
        
    }
    
    
    private void batchSave(ConfigVO configVO){
        String[] contents = configVO.getContent().split("\r\n");
        HulkApp app = appRepository.findOne(configVO.getAppId());
        HulkEnv env = envRepository.findOne(configVO.getEnvId());
        for (String content : contents) {
            String[] value = content.split("=");
            HulkConfig hulkConfig = new HulkConfig();
            hulkConfig.setApp(app);
            hulkConfig.setEnv(env);
            hulkConfig.setVersion(configVO.getVersion());
            hulkConfig.setCreatedAt(new Date());
            hulkConfig.setUpdatedAt(new Date());
            hulkConfig.setName(value[0]);
            hulkConfig.setValue(value[1]);
            configRepository.save(hulkConfig);
            String nodePath = ZookeeperPathUtils.getNodeFullPath(hulkConfig.getApp().getAppName(),
                                                                 hulkConfig.getEnv().getEnvName(), hulkConfig.getVersion(), hulkConfig.getName());
            zkService.saveZookeeperConfig(nodePath, hulkConfig.getValue());
        }
    }

    
    @Override
    public HulkResponse<HulkConfig> findAll(final ConfigVO hulkConfig, Pageable pageable) {
        Page<HulkConfig> record = configRepository.findAll(new Specification<HulkConfig>() {
            
            @Override
            public Predicate toPredicate(Root<HulkConfig> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(hulkConfig.getAppId()!=null){
                    predicate.getExpressions().add(cb.equal(root.get("app").get("appId"),hulkConfig.getAppId()));
                }
                if(StringUtils.isNotEmpty(hulkConfig.getKey())){
                    predicate.getExpressions().add(cb.equal(root.get("name"),hulkConfig.getKey()));
                }
                if(StringUtils.isNotEmpty(hulkConfig.getVersion())){
                    predicate.getExpressions().add(cb.equal(root.get("version"),hulkConfig.getVersion()));
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
    public void update(ConfigVO configVo) {
        try {
            HulkConfig config = configRepository.findOne(configVo.getConfigId());
            String beforeValue = config.getValue();
            config.setName(configVo.getKey());
            config.setValue(configVo.getValue());
            config.setUpdatedAt(new Date());
            configRepository.save(config);
            zkService.notifyNodeChange(config.getApp().getAppName(), config.getEnv().getEnvName(), config.getVersion(), config.getName(), config.getValue());
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("time", new Date());
            data.put("appName", config.getApp().getAppName());
            data.put("envName", config.getEnv().getEnvName());
            data.put("version", config.getVersion());
            data.put("changeKey", config.getName());
            data.put("beforeValue", beforeValue);
            data.put("afterValue", config.getValue());
            mailHeleper.asyncSendMail(config.getApp().getEmails(),data);
        } catch (Exception e) {
            LOGGER.error("Update Config fail.",e);
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void synData(ConfigVO configVO) {
        
        HulkApp app = appRepository.findOne(configVO.getAppId());
        HulkEnv env = envRepository.findOne(configVO.getEnvId());
       
        String nodePath = ZookeeperPathUtils.getNodeFullPath(app.getAppName(), env.getEnvName(), configVO.getVersion(), "");
        List<Map<String, String>> result = zkDriver.getConfig(nodePath);
        if(result!=null && result.size()>0){
            for (Map<String, String> map : result) {
                for(Map.Entry<String, String> config:map.entrySet()){
                    String key = config.getKey();
                    String value = config.getValue();
                    String name = StringUtils.substringAfterLast(key, "/");
                    HulkConfig hulkConfig = new HulkConfig();
                    hulkConfig.setApp(app);
                    hulkConfig.setEnv(env);
                    hulkConfig.setVersion(configVO.getVersion());
                    hulkConfig.setName(name);
                    hulkConfig.setValue(value);
                    hulkConfig.setCreatedAt(new Date());
                    hulkConfig.setUpdatedAt(new Date());
                    configRepository.save(hulkConfig);
                }
            }
        }
    }


    
    @Override
    public HulkConfig findById(Long configId) {
        HulkConfig config = configRepository.findOne(configId);
        return config;
    }


   
    @Override
    public List<HulkConfig> findAll() {
        List<HulkConfig> list = (List<HulkConfig>) configRepository.findAll();
        return list;
    }


    
   

    
}

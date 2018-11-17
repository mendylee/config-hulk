package com.xiangrikui.hulk.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiangrikui.hulk.web.common.HulkResponse;
import com.xiangrikui.hulk.web.entity.HulkApp;
import com.xiangrikui.hulk.web.entity.HulkConfig;
import com.xiangrikui.hulk.web.entity.HulkEnv;
import com.xiangrikui.hulk.web.service.AppService;
import com.xiangrikui.hulk.web.service.ConfigService;
import com.xiangrikui.hulk.web.service.EnvService;
import com.xiangrikui.hulk.web.vo.ConfigVO;

/**
 * 创建时间：2017年5月16日
 * <p>修改时间：2017年5月16日
 * <p>类说明：配置管理Controller控制器
 * 
 * @author jerry
 * @version 1.0
 */
@Controller
public class ConfigController {
    
    @Autowired
    private ConfigService configService;
    @Autowired
    private AppService appService;
    @Autowired
    private EnvService envService;
    
    
    /**
     * 进入配置管理模块页面
     * @return
     */
    @RequestMapping("/admin/configlist")
    public String configList(ModelMap modelMap){
        List<HulkApp> appList = appService.findAll();
        modelMap.put("appList", appList);
        return "/config/config_list";
    }
    
    /**
     * 跳转到添加配置页面
     * @return
     */
    @RequestMapping("/admin/toAddConfig")
    public String addConfig(ModelMap modelMap){
        List<HulkApp> appList = appService.findAll();
        modelMap.put("appList", appList);
        List<HulkEnv> envList = envService.findAll();
        modelMap.put("envList", envList);
        return "/config/config_add";
    }
    
    /**
     * 跳转到同步zookeeper配置模块
     * @return
     */
    @RequestMapping("/admin/toSyn")
    public String toSyn(ModelMap modelMap){
        List<HulkApp> appList = appService.findAll();
        modelMap.put("appList", appList);
        List<HulkEnv> envList = envService.findAll();
        modelMap.put("envList", envList);
        return "/config/config_syn";
    }
    
    /**
     * 配置管理分页查询入口
     * @param config
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/config/list")
    public @ResponseBody String configList(ConfigVO config,Integer pageIndex,Integer pageSize){
        Sort sort = new Sort(Direction.DESC, "updatedAt");
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize,sort);
        HulkResponse<HulkConfig> hulkResponse = configService.findAll(config, pageRequest);
        String result = JSONObject.toJSONString(hulkResponse,SerializerFeature.DisableCircularReferenceDetect);
        return result;
    }
    
    /**
     * 新增配置
     * @param configVO
     * @return
     * @throws Exception 
     */
    @RequestMapping("/config/add")
    public String configAdd(ConfigVO configVO) throws Exception{
        configService.save(configVO);
        return "/admin/configlist";
    }
    
    
    /**
     * 跳转到添加配置页面
     * @return
     */
    @RequestMapping("/config/toEdit")
    public String addConfig(Long configId,ModelMap modelMap){
        HulkConfig hulkConfig = configService.findById(configId);
        modelMap.put("config", hulkConfig);
        return "/config/config_edit";
    }
    
    
    /**
     * 修改配置
     * @param configVO
     * @return
     */
    @RequestMapping("/config/edit")
    public String configEdit(ConfigVO configVO){
        configService.update(configVO);
        return "/admin/configlist";
    }
    
    /**
     * 同步zookeeper配置到配置库中
     * @param appId
     * @param envId
     * @param version
     * @return
     */
    @RequestMapping("/config/syn")
    public String configSyn(ConfigVO configVO){
        configService.synData(configVO);
        return "/admin/configlist";
    }
    
    
    
    
}

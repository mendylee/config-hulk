package com.xiangrikui.hulk.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangrikui.hulk.web.entity.HulkApp;
import com.xiangrikui.hulk.web.entity.HulkConfig;
import com.xiangrikui.hulk.web.service.AppService;
import com.xiangrikui.hulk.web.service.ConfigService;

/**
 * 创建时间：2017年4月27日
 * <p>修改时间：2017年4月27日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
@Controller
public class AdminController {
    
    @Autowired
    private AppService appService;
    
    @Autowired
    private ConfigService configService;
    
    /**
     * 后台首页
     * @return
     */
    @RequestMapping("/index")
    public String admin(){
        return "index";
    }
    
    /**
     * 后台登录
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
    /**
     * 后台期待页
     * @return
     */
    @RequestMapping("/wait")
    public String waitPage(){
        return "wait";
    }
    
    /**
     * 后台主页
     * @return
     */
    @RequestMapping("/admin/main")
    public String main(ModelMap modelMap){
        List<HulkApp> appList = appService.findAll();
        List<HulkConfig> configList = configService.findAll();
        modelMap.put("appSize", appList.size());
        modelMap.put("configSize", configList.size());
        return "main";
    }
    
    /**
     * 环境管理
     * @param model
     * @return
     */
    @RequestMapping("/admin/envlist")
    public String envList(ModelMap model){
        return "/env/env_list";
    }
    
    /**
     * 应用管理
     * @return
     */
    @RequestMapping("/admin/applist")
    public String appList(){
        return "/app/app_list";
    }
    
    /**
     * 跳转到应用模块添加应用
     * @return
     */
    @RequestMapping("/admin/toAddApp")
    public String addApp(){
        return "/app/app_add";
    }
    

    
    
    
    
    
   
    
}

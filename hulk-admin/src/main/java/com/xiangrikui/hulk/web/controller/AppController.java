package com.xiangrikui.hulk.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xiangrikui.hulk.web.common.HulkResponse;
import com.xiangrikui.hulk.web.entity.HulkApp;
import com.xiangrikui.hulk.web.service.AppService;

/**
 * 创建时间：2017年5月3日
 * <p>修改时间：2017年5月3日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
@Controller
public class AppController {
    
   @Autowired
   private AppService appService;
   
   @RequestMapping("/app/list")
   public @ResponseBody String appList(HulkApp app,Integer pageIndex,Integer pageSize){
       PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
       HulkResponse<HulkApp> hulkResponse = appService.findAll(app, pageRequest);
       String result = JSON.toJSONString(hulkResponse);
       return result;
   }
   
   @RequestMapping("/app/add")
   public String addApp(HulkApp hulkApp){
       appService.save(hulkApp);
       return "/admin/applist";
   }
   
   @RequestMapping("/app/delete")
   @ResponseBody
   public String deleteApp(String appId){
       appService.delete(Long.parseLong(appId));
       return "/admin/applist";
   }
  
    
}

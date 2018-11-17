package com.xiangrikui.hulk.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xiangrikui.hulk.web.common.HulkResponse;
import com.xiangrikui.hulk.web.entity.HulkEnv;
import com.xiangrikui.hulk.web.service.EnvService;

/**
 * 创建时间：2017年5月3日
 * <p>修改时间：2017年5月3日
 * <p>类说明：环境管理控制器
 * 
 * @author jerry
 * @version 1.0
 */
@Controller
public class EnvController {

    @Autowired
    private EnvService envService;
    
    @RequestMapping("/env/list")
    @ResponseBody
    public String appList(String envName,Integer pageIndex,Integer pageSize){
        PageRequest pageRequest =  new PageRequest(pageIndex-1, pageSize);
        HulkResponse<HulkEnv> hulkResponse = envService.findAll(envName, pageRequest);
        String result = JSON.toJSONString(hulkResponse);
        return result;
    }
}

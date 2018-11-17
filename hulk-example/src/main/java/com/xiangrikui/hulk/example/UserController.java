package com.xiangrikui.hulk.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiangrikui.hulk.client.HulkClient;
import com.xiangrikui.hulk.example.config.HulkClientExampleConfig;

/**
 * 创建时间：2017年3月9日
 * <p>修改时间：2017年3月9日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
@RestController
public class UserController {
    
    @Autowired
    private HulkClientExampleConfig config;
    
    @Autowired
    private HulkClient client;
    
    @GetMapping("/user/useAnnotation")
    public String useAnnatation(){
        String requestOut = (String) client.getConfig("request.timeout").getValue();
        System.out.println("HulkClient requestTimeOut:"+requestOut);
        System.out.println("requestTimeOut:"+config.requestTimeOut);
        return config.requestTimeOut;
    }
}

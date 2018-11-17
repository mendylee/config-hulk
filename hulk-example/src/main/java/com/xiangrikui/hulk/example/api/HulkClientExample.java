package com.xiangrikui.hulk.example.api;

import com.xiangrikui.hulk.client.HulkClient;
import com.xiangrikui.hulk.client.common.HulkClientBuilder;

/**
 * 创建时间：2017年3月15日
 * <p>修改时间：2017年3月15日
 * <p>类说明：Hulk配置中心API方式的Example示例
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkClientExample {
    
    public static void main(String[] args) throws InterruptedException {
        HulkClient hulkClient = new HulkClientBuilder().
                                 setProperteisLocations("hulk.properties")
                                 .build();
        hulkClient.start();
        String name = (String) hulkClient.getConfig("name").getValue();
        System.out.println(name);
    }
}

package com.xiangrikui.hulk.client.test.scan.model;

import com.xiangrikui.hulk.client.common.annotation.HulkConfigItem;

/**
 * 创建时间：2017年3月20日
 * <p>修改时间：2017年3月20日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class User {

    @HulkConfigItem(key = "userName", name = "userName")
    private String userName = "2000";
    
    private String address;

    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}

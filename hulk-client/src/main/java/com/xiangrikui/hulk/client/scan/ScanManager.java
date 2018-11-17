package com.xiangrikui.hulk.client.scan;

import java.util.List;

/**
 * 创建时间：2017年3月9日
 * <p>修改时间：2017年3月9日
 * <p>类说明：配置中心扫描管理接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface ScanManager {
    
    /**
     * 扫描包路径下的配置注解
     * @param scanPackages
     */
    void scan(List<String> scanPackages);
}

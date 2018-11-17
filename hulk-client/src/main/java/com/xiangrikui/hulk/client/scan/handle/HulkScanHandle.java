package com.xiangrikui.hulk.client.scan.handle;

import com.xiangrikui.hulk.client.scan.model.ScanModel;

/**
 * 创建时间：2017年3月17日
 * <p>修改时间：2017年3月17日
 * <p>类说明：配置中心扫描处理器接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface HulkScanHandle {
    
    /**
     * 扫描数据并存储到配置中心
     */
    void scanDataStore(ScanModel scanModel);
}

package com.xiangrikui.hulk.client.scan;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangrikui.hulk.client.common.HulkAppContext;
import com.xiangrikui.hulk.client.scan.handle.HulkScanHandle;
import com.xiangrikui.hulk.client.scan.handle.impl.HulkScanItemHandler;
import com.xiangrikui.hulk.client.scan.model.ScanModel;
import com.xiangrikui.hulk.client.scan.strategy.ReflectionsScanStrategy;
import com.xiangrikui.hulk.client.scan.strategy.ScanStrategy;

/**
 * 创建时间：2017年3月17日
 * <p>修改时间：2017年3月17日
 * <p>类说明：配置中心默认扫描服务实现类
 * 
 * @author jerry
 * @version 1.0
 */
public class DefaultScanManagerImpl implements ScanManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultScanManagerImpl.class);
    
    //配置中心扫描处理器集合列表
    private List<HulkScanHandle> scanHandles = new ArrayList<HulkScanHandle>();
    
    //配置中心扫描实体信息类
    private volatile ScanModel scanModel = null;
    
    //注解配置扫描策略
    private ScanStrategy scanStrategy = new ReflectionsScanStrategy();
   
    public DefaultScanManagerImpl(HulkAppContext context) {
        scanHandles.add(new HulkScanItemHandler(context));
    }
    
    @Override
    public void scan(List<String> scanPackages) {
        LOGGER.info("Hulk Start Scan Package:{}",scanPackages.toString());
        //通过扫描策略进行扫描
        scanModel = scanStrategy.scan(scanPackages);
        //将扫描的信息存储到配置中心
        for (HulkScanHandle hulkScanHandle : scanHandles) {
            hulkScanHandle.scanDataStore(scanModel);
        }
    }

}

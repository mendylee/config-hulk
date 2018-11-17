package com.xiangrikui.hulk.client.scan.strategy;

import java.util.List;

import com.xiangrikui.hulk.client.scan.model.ScanModel;

/**
 * 创建时间：2017年3月20日
 * <p>修改时间：2017年3月20日
 * <p>类说明：Hulk配置扫描策略接口
 * 
 * @author jerry
 * @version 1.0
 */
public interface ScanStrategy {

    ScanModel scan(List<String> scanPackages);
}

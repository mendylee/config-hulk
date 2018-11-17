package com.xiangrikui.hulk.client.scan.strategy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.google.common.base.Predicate;
import com.xiangrikui.hulk.client.common.annotation.HulkConfigItem;
import com.xiangrikui.hulk.client.common.constans.Constants;
import com.xiangrikui.hulk.client.scan.model.ScanModel;

/**
 * 创建时间：2017年3月20日
 * <p>修改时间：2017年3月20日
 * <p>类说明：基于java运行时元数据的注解扫描策略
 * 
 * @author jerry
 * @version 1.0
 */
public class ReflectionsScanStrategy implements ScanStrategy{

    
    @Override
    public ScanModel scan(List<String> scanPackages) {
        ScanModel scanModel = scanAnnotationInfo(scanPackages);
        return scanModel;
    }
    
    private Reflections getReflection(List<String> scanPackages){
        FilterBuilder filterBuilder = new FilterBuilder().includePackage(Constants.DEFAULT_PACKAGE);
        for (String packageName : scanPackages) {
            filterBuilder.includePackage(packageName);
        }
        Predicate<String> filter = filterBuilder;
        Collection<URL> urls = new ArrayList<URL>();
        for (String packageName : scanPackages) {
            Collection<URL> packageUrl =  ClasspathHelper.forPackage(packageName);
            urls.addAll(packageUrl);
        }
        
        Reflections reflections = new Reflections(new ConfigurationBuilder().filterInputsBy(filter)
                                                  .setScanners(new SubTypesScanner().filterResultsBy(filter),
                                                                   new TypeAnnotationsScanner()
                                                                       .filterResultsBy(filter),
                                                                   new FieldAnnotationsScanner()
                                                                       .filterResultsBy(filter),
                                                                   new MethodAnnotationsScanner()
                                                                       .filterResultsBy(filter),
                                                                   new MethodParameterScanner()).setUrls(urls));
        
        return reflections;
        
    }
    
    private ScanModel scanAnnotationInfo(List<String> scanPackages){
        ScanModel scanModel = new ScanModel();
        
        //获取reflections实例进行扫描
        Reflections reflections = getReflection(scanPackages);
        scanModel.setReflections(reflections);
        
        //获取HulkConfigItem注解的Method
        Set<Method> hulkConfigItemMethod = reflections.getMethodsAnnotatedWith(HulkConfigItem.class);
        scanModel.setHulkConfigItemMethod(hulkConfigItemMethod);
        //获取HulkConfigItem注解的Field
        Set<Field> hulkConfigItemField = reflections.getFieldsAnnotatedWith(HulkConfigItem.class);
        scanModel.setHulkConfigItemField(hulkConfigItemField);
        return scanModel;
    }

}

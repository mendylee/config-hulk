package com.xiangrikui.hulk.client.test.scan;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
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
import com.xiangrikui.hulk.client.test.scan.model.User;
import com.xiangrikui.hulk.client.test.scan.model.UserSun;

/**
 * 创建时间：2017年3月20日
 * <p>修改时间：2017年3月20日
 * <p>类说明：包扫描注解单元测试类
 * 
 * @author jerry
 * @version 1.0
 */
public class ScanPackageTest {
 
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        String[] scanPackages = {"com.xiangrikui"};
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
        
        Set<Field> field = reflections.getFieldsAnnotatedWith(HulkConfigItem.class);
        for (Field fieldName : field) {
            fieldName.setAccessible(true);
            User user = new User();
            System.out.println(fieldName.get(user));
            fieldName.set(user, "8000");
            System.out.println(fieldName.get(user));
            UserSun sun = new UserSun();
            System.out.println(fieldName.get(sun));
        }
        
    }
}

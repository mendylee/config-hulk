package com.xiangrikui.hulk.core.common.util;

import java.net.URL;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

/**
 * 创建时间：2017年4月10日
 * <p>修改时间：2017年4月10日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class ClassLoaderUtil {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(ClassLoaderUtil.class);
    
    private static ClassLoader loader = Thread.currentThread().getContextClassLoader();
    
    private static String classPath = "";
    
    static{
        if(loader == null){
            loader = ClassLoader.getSystemClassLoader();
        }
        URL url = loader.getResource("");
        try {
            if(url != null){
                classPath = url.getPath();
                classPath = URLDecoder.decode(classPath, "UTF-8");
            }
            if(Strings.isNullOrEmpty(classPath) || classPath.contains(".jar")){
                classPath = System.getProperty("user.dir");
            }
        } catch (Exception e) {
            classPath = System.getProperty("user.dir");
        }
    }
    
    public static String getClassPath(){
        return classPath;
    }
}

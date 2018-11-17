package com.xiangrikui.hulk.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 创建时间：2017年3月28日
 * <p>修改时间：2017年3月28日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class PropertiesTest {

    private static String param1; 
    private static String param2; 

    static { 
        Properties prop = new Properties(); 
        InputStream in = Object.class.getResourceAsStream("/hulk.properties"); 
        try { 
            prop.load(in); 
            param1 = prop.getProperty("hulk.registryAddress").trim(); 
            param2 = prop.getProperty("hulk.registryAddress").trim(); 
            System.out.println(param1);
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    }
    
    public static void main(String[] args) {
        
    }
}

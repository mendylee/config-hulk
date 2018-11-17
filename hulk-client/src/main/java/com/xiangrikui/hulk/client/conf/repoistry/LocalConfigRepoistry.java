package com.xiangrikui.hulk.client.conf.repoistry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiangrikui.hulk.core.common.util.ClassLoaderUtil;
import com.xiangrikui.hulk.core.context.AppConfig;
import com.xiangrikui.hulk.core.exception.HulkException;

/**
 * 创建时间：2017年4月7日
 * <p>修改时间：2017年4月7日
 * <p>类说明：本地配置文件存储仓库
 * 
 * @author jerry
 * @version 1.0
 */
public class LocalConfigRepoistry {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalConfigRepoistry.class);
    //private int maxRetryCount = 10;
    //private int retrySleepTime = 500;
    
    private AppConfig appConfig;
    
    public LocalConfigRepoistry(AppConfig appConfig){
        this.appConfig = appConfig;
    }
    
    public void storeConfigReplicas(String dirPath,String configJson) throws IOException{
        
        String configFilePath =  this.getAppFileName(dirPath);
        File dir  = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File tempFile = File.createTempFile("config", ".tmp", dir);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(tempFile);
            fos.write(configJson.getBytes("UTF-8"));
            fos.flush();
            fos.close();
            while(true){
                //TODO 未做重试处理
                Files.move(tempFile.toPath(), new File(configFilePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                LOGGER.info("Sava Local Config File Success! File Path:{}"+ClassLoaderUtil.getClassPath());
                break;
            }
            
        } catch (Exception e) {
            LOGGER.error("Save Local Config File Error! File Path:{}",configFilePath, e);
        }finally{
            if(fos!=null){
                fos.close();
                if(tempFile.exists()){
                    tempFile.delete();
                }
            }
        }
        
    }
    
    public Map<String, String> loadLocalConfig() throws IOException{
        String configFilePath = this.getAppFileName(ClassLoaderUtil.getClassPath());
        File file = new File(configFilePath);
        if(file.exists()){
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                String jsonConfig = new String(data, "UTF-8");
                Map<String, String> localConfigFileMap = JSON.parseObject(jsonConfig, new TypeReference<Map<String, String>>(){});
                return localConfigFileMap;
            }finally{
                if(fis != null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        throw new HulkException("close file error!" + configFilePath, e);
                    }
                }
            } 
        }else{
            LOGGER.warn("can not found local hulk config file! configFilePath:" + configFilePath);
            return null;
        }
    }
    
    private String getAppFileName(String dirPath){
        StringBuilder path = new StringBuilder();
        path.append(dirPath + File.separator);
        path.append(appConfig.getAppName());
        
        path.append("_");
        path.append(appConfig.getEnv());
        
        path.append("_");
        path.append(appConfig.getVersion());
        
        path.append(".json");
        return path.toString();
    }
}

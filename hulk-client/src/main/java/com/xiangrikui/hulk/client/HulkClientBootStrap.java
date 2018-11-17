package com.xiangrikui.hulk.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiangrikui.hulk.client.common.AppConfigFactory;
import com.xiangrikui.hulk.client.common.HulkAppContext;
import com.xiangrikui.hulk.client.common.constans.Constants;
import com.xiangrikui.hulk.client.conf.model.HulkConfigItemModel;
import com.xiangrikui.hulk.client.conf.repoistry.LocalConfigRepoistry;
import com.xiangrikui.hulk.client.conf.store.HulkConfigCache;
import com.xiangrikui.hulk.client.registry.AbstractRegistry;
import com.xiangrikui.hulk.client.registry.NoticeEvent;
import com.xiangrikui.hulk.client.registry.NoticeListener;
import com.xiangrikui.hulk.client.registry.Registry;
import com.xiangrikui.hulk.client.registry.RegistryFactory;
import com.xiangrikui.hulk.client.registry.node.AppNode;
import com.xiangrikui.hulk.client.registry.node.AppNodeFactory;
import com.xiangrikui.hulk.client.scan.DefaultScanManagerImpl;
import com.xiangrikui.hulk.client.scan.ScanManager;
import com.xiangrikui.hulk.client.scan.reflect.ReflectBeanFactory;
import com.xiangrikui.hulk.client.scan.reflect.ReflectBeanService;
import com.xiangrikui.hulk.client.scan.support.HulkScanHelper;
import com.xiangrikui.hulk.core.common.util.ClassLoaderUtil;
import com.xiangrikui.hulk.core.common.util.StringUtils;
import com.xiangrikui.hulk.core.context.AppConfig;
import com.xiangrikui.hulk.core.exception.HulkException;

/**
 * 创建时间：2017年3月8日
 * <p>修改时间：2017年3月8日
 * <p>类说明：配置中心客户端核心启动入口类
 * 
 * @author jerry
 * @version 1.0
 */
public abstract class HulkClientBootStrap implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(HulkClientBootStrap.class);
    
    private ApplicationContext applicationContext;
    
    /**
     * 注册服务
     */
    private Registry registry;
    
    /**
     * 
     */
    private LocalConfigRepoistry localConfigRepoistry;
    
    /**
     * 应用节点信息
     */
    private AppNode app;
    
    /**
     * 配置中心应用上下文
     */
    protected HulkAppContext context;
    
    /**
     * 配置中心通用配置信息
     */
    protected AppConfig appConfig;
    
    /**
     * 配置变更监听器列表
     */
    private List<ConfigChangeLisener> configChangeLiseners;
    
    /**
     * 配置扫描管理器实例
     */
    private ScanManager scanManager;
    
    /**
     * 扫描包路径列表
     */
    private String scanPackages;
    
    /**
     * 启动标识
     */
    protected AtomicBoolean started = new AtomicBoolean(false);
    
        
    
    public HulkClientBootStrap(){
        context  = new HulkAppContext();
        appConfig = AppConfigFactory.getAppConfig();
        context.setAppConfig(appConfig);
        configChangeLiseners = new ArrayList<ConfigChangeLisener>();
        
    }
    
   
    
    
    final public void start(){
        try {
            if(started.compareAndSet(false, true)){
                
                //1.初始化配置中心系统配置
                initSysConfig();
                
                //2.载入本地副本配置信息
                localConfigRepoistry.loadLocalConfig();
                
                //2.初始化配置中心注册服务
                initRegistry();
                
                //3.初始化扫描管理器
                initScanManager();
                
                //4.将应用的父节点注册到配置中心
                registry.register(app);
                
                //5.判断是否开启了本地扫描配置注解并存储
                if(StringUtils.isNotEmpty(scanPackages)){
                    List<String> scanPackageList = HulkScanHelper.parseStringToStringList(scanPackages, Constants.SCAN_SPLIT_TOKEN);
                    scanManager.scan(scanPackageList);
                }
                
                //6.注册节点配置信息变更监听
                registry.subscribe(app, new NoticeListener() {
                    
                    public void notice(NoticeEvent event, String key,Object value) {
                        for (ConfigChangeLisener configChangeLisener : configChangeLiseners) {
                            configChangeLisener.configChange(event, key,value);
                        }
                    }
                });
                Thread.sleep(1000);
                
                //7.打印应用加载成功后的配置信息,并检测配置是否存在null异常
                Map<String, Object> localConfigFileMap = new HashMap<String,Object>();
                for (Map.Entry<String, HulkConfigItemModel> map:HulkConfigCache.getInstance().getAllConfigItem().entrySet()) {
                    if(Objects.isNull(map.getValue().getValue())){
                        throw new HulkException("hulk config value is null: key:{} "+map.getKey());
                    }
                    localConfigFileMap.put(map.getKey(), map.getValue().getValue());
                    LOGGER.info("Load Hulk Config Key:{},Value:{}",map.getKey(),map.getValue().getValue());
                }
                
                //8.保存配置到本地副本中
                saveLocalConfigFile(localConfigFileMap);
            }
            LOGGER.info("Started HulkClient Success,App={}",app.getAppFullInfo());
        } catch (Throwable e) {
            LOGGER.error("Failed to initialize HulkClient",e);
            throw new HulkException("Failed to initialize HulkClient :", e);
            
        }
        
    }

    public void stop() {
        if(started.compareAndSet(true, false)){
            //当前还未有在停止时需要的操作,此入口随未来特性增加而扩展
            LOGGER.info("Stop HulkClient Success, App={}",app.getAppFullInfo());
        }
    }
    
    public void destory() {
        if(started.compareAndSet(true, false)){
            try {
                registry.destory();
                LOGGER.info("Destory HulkClient Success, App={}",app.getAppFullInfo()); 
            } catch (Exception e) {
                LOGGER.error("Destory HulkClient Success, App={}",app.getAppFullInfo());
            }
        }
    }
    
    protected void initSysConfig(){
       this.context.setAppConfig(appConfig);
       ReflectBeanService reflectBeanService = ReflectBeanFactory.getReflectBeanService(applicationContext);
       this.context.setReflectBeanService(reflectBeanService);
       this.app = AppNodeFactory.create(AppNode.class, appConfig);
       LOGGER.info("Current App Config :{}",appConfig);
       this.localConfigRepoistry = new LocalConfigRepoistry(appConfig);
       AppSubcribeManager subscribeAppManager = new AppSubcribeManager(context);
       configChangeLiseners.add(subscribeAppManager);
       
        
    }
    
    protected void initScanManager(){
        scanManager = new DefaultScanManagerImpl(context);
    }
    
    protected void saveLocalConfigFile(Map<String, Object> configMap){
        try {
            String configJson = JSONObject.toJSONString(configMap,SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.PrettyFormat);
            localConfigRepoistry.storeConfigReplicas(ClassLoaderUtil.getClassPath(),configJson);
        } catch (IOException e) {
            LOGGER.error("Save Local ConfigFile Fail:{}",e);
        }
    }
    
    protected void initRegistry(){
        //从注册工厂获取当前的注册服务实例
        registry = RegistryFactory.getRegistry(context);
        //将当前的应用节点设置到注册服务实例
        if(registry instanceof AbstractRegistry){
            ((AbstractRegistry)registry).setAppNode(app);
        }
        context.setRegistry(registry);
    }
    
    /**
     * 设置配置中心zookeeper地址
     * @param registryAddress
     */
    public void setRegistryAddress(String registryAddress){
        appConfig.setRegiestZkAddress(registryAddress);
    }
    
    /**
     * 设置应用ID
     * @param appId
     */
    public void setAppId(String appId){
        appConfig.setAppId(appId);
    }
    
    /**
     * 设置应用名称
     * @param appName
     */
    public void setAppName(String appName){
        appConfig.setAppName(appName);
    }
    
    /**
     * 设置环境信息
     * @param env
     */
    public void setEnv(String env){
        appConfig.setEnv(env);
    }
    
    /**
     * 设置版本信息
     * @param version
     */
    public void setVersion(String version){
        appConfig.setVersion(version);
    }

    /**
     * 设置包扫描注解信息
     * @param scanPackages
     */
    public void setScanPackages(String scanPackages) {
        this.scanPackages = scanPackages;
    }
    
    /**
     * 设置是否配置中心不存在值时用本地的值上传至配置中心
     * @param useLocalConfigUpload
     */
    public void setUseLocalConfigUpload(boolean useLocalConfigUpload){
        appConfig.setUseLocalConfigUpload(useLocalConfigUpload);
    }
    
    /**
     * 设置应用上下文
     */
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
    

    
}

/*package com.xiangrikui.hulk.core;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

*//**
 * Created by dqh on 2017/4/21.
 *
 * @author xiangrikui
 *//*
public class InitConfig {

	@Test
	public void initDevConfig() throws Exception {
		Map<String,String> configMap = new HashMap<>();
		configMap.put("hezhong.url","http://106.38.60.141:8082/partner/Insure.do");
		configMap.put("trans_source","57");
		configMap.put("md5Key","8C829879E92841679E0F13D1214XRK");

		String version = "1.0.0";
		String appname = "xrk-malladapter-hezhong";
		String env = "dev";
		String ROOT_PATH = "/hulk/"+appname+"_"+env+"_"+version;

		initConfig(ROOT_PATH,configMap);
	}

	@Test
	public void initTest1Config() throws Exception {
		Map<String,String> configMap = new HashMap<>();
		configMap.put("hezhong.url","http://106.38.60.141:8082/partner/Insure.do");
		configMap.put("trans_source","57");
		configMap.put("md5Key","8C829879E92841679E0F13D1214XRK");

		String version = "1.0.0";
		String appname = "xrk-malladapter-hezhong";
		String env = "test";
		String ROOT_PATH = "/hulk/"+appname+"_"+env+"_"+version;

		initConfig(ROOT_PATH,configMap);
	}

	@Test
	public void initTest2Config() throws Exception {
		Map<String,String> configMap = new HashMap<>();
		configMap.put("redis.hostname","192.168.9.16");
        configMap.put("redis.hostname","192.168.9.283");
        

		String version = "1.0.0";
		String appname = "hulk-example";
		String env = "test";
		String ROOT_PATH = "/hulk/"+appname+"_"+env+"_"+version;

		initConfig(ROOT_PATH,configMap);
	}



	@Test
	public void initStagingConfig() throws Exception {
		Map<String,String> configMap = new HashMap<>();
		configMap.put("hezhong.url","https://opartner.unionlife.com.cn/partner/Insure.do");
		configMap.put("trans_source","57");
		configMap.put("md5Key","8C829879E92841679E0F13D1214XRK");

		String version = "1.0.0";
		String appname = "xrk-malladapter-hezhong";
		String env = "staging";
		String ROOT_PATH = "/hulk/"+appname+"_"+env+"_"+version;

		initConfig(ROOT_PATH,configMap);
	}


	@Test
	public void initProductConfig() throws Exception {
		Map<String,String> configMap = new HashMap<>();
		configMap.put("hezhong.url","https://opartner.unionlife.com.cn/partner/Insure.do");
		configMap.put("trans_source","57");
		configMap.put("md5Key","8C829879E92841679E0F13D1214XRK");

		String version = "1.0.0";
		String appname = "xrk-malladapter-hezhong";
		String env = "product";
		String ROOT_PATH = "/hulk/"+appname+"_"+env+"_"+version;

		initConfig(ROOT_PATH,configMap);
	}


	private CuratorFramework getclient(){
		return  CuratorFrameworkFactory.builder()
				.connectString("192.168.6.62:2181")
				.connectionTimeoutMs(5000)
				.retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
				.build();
	}

	private void writeData(String rootpath,String keyName,byte[] data) throws Exception {
		CuratorFramework client = getclient();
		client.start();
		Object rootobj = client.checkExists().forPath(rootpath);
		if(rootobj==null){
			client.create().forPath(rootpath);
		}

		String path =rootpath+"/"+keyName;
		Object obj = client.checkExists().forPath(path);
		if(obj==null){
			client.create().forPath(path);
		}
		client.setData().forPath(path, data);
		client.close();
	}

	private void writeData(String rootPath,String path,String data) throws Exception {
		writeData(rootPath,path,data.getBytes("UTF-8"));
	}

	private String getData(String path) throws Exception {
		CuratorFramework client = getclient();
		client.start();
		byte[] data  = client.getData().forPath(path);
		client.close();
		return new String(data,"utf-8");
	}

	public void deleteBode(String path,String node) throws Exception {
		CuratorFramework client = getclient();
		client.start();
		client.delete().forPath(path+"/"+node);
		client.close();
	}

	private void initConfig(String ROOT_PATH,Map<String,String> configMap) throws Exception {
		for(Map.Entry<String,String> kv:configMap.entrySet()){
			writeData(ROOT_PATH,kv.getKey(),kv.getValue());
			String getUrl = getData(ROOT_PATH+"/"+kv.getKey());
			Assert.assertEquals(getUrl,kv.getValue());
		}
	}
}
*/
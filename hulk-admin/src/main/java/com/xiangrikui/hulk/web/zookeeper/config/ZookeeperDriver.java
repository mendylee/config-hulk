package com.xiangrikui.hulk.web.zookeeper.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiangrikui.hulk.web.zookeeper.ZookeeperManager;
import com.xiangrikui.hulk.web.zookeeper.ZookeeperServer;
import com.xiangrikui.hulk.web.zookeeper.node.TreeNode;

@Service
public class ZookeeperDriver implements ZookeeperServer {
    

   
    
    
    @Override
    public void notifyNodeChange(String app, String env, String version, String kye, String value) {
        
    }
    
    
   
    @Override
    public  List<Map<String, String>> getConfig(String rootPath) {
        ZookeeperManager zkManager =  ZookeeperManager.getInstance();
        TreeNode treeNode = new TreeNode(rootPath, "");
        List<Map<String, String>> configList = new ArrayList<Map<String,String>>();
        getConfig(zkManager, treeNode,configList);
        return configList;
        
    }
    
    
    private void getConfig(ZookeeperManager zkManager,TreeNode treeNode,List<Map<String, String>> configList){
        
        //获取当前节点的路径
        String nodePath = treeNode.getNodeKey();
        
        StringBuffer sb = new StringBuffer();
        int pathLength = StringUtils.countMatches(nodePath, "/");
        for (int i = 0; i < pathLength - 1; ++i) {
            sb.append("\t");
        }
        
        
        if(!"/hulk".equals(nodePath)){
            String node = StringUtils.substringAfterLast(nodePath, "/");
            sb.append("|----" + node);
            //获取内容
            Object data = zkManager.getData(nodePath);
            if (data != null ) {
                Map<String,String> result = new HashMap<String, String>();
                result.put(nodePath, data.toString());
                configList.add(result);
                sb.append("\t" + data);
            }
        }else{
            sb.append(nodePath);
        }
        
        System.out.println(sb.toString());
        
        List<String> children = zkManager.getConf(nodePath);
        for (String child : children) {
           TreeNode childTreeNode = new TreeNode();
           childTreeNode.setNodeKey(nodePath+"/"+child);
           childTreeNode.setParentNodeKey(nodePath);
           getConfig(zkManager,childTreeNode,configList);
           
        }
        
    }
    
    
    
    private void getConfig(ZookeeperManager zkManager,String groupName, Map<String, Map<String,String>> result){
        /*if(StringUtils.isNotEmpty(path)){
            rootPath = rootPath+"/"+path;
        }
        List<String> children = zkManager.getConf(rootPath);
        if(children!=null && children.size()>0){
            for (String childPath : children) {
                if(result.containsKey(childPath)){
                    Map<String, String> value = result.get(childPath);
                }else{
                    result.put(childPath, new HashMap<String, String>());
                }
            }
        }
        return result;*/
        StringBuffer sb = new StringBuffer();
        int pathLength = StringUtils.countMatches(groupName, "/");
        for (int i = 0; i < pathLength - 1; ++i) {
            sb.append("\t");
        }

        if (groupName != "/" && !"/hulk".equals(groupName)) {
            String node = StringUtils.substringAfterLast(groupName, "/");
            sb.append("|----" + node);
            Object data = zkManager.getData(groupName);

            if (data != null) {
                sb.append("\t" + data);
            }
            
        } else {
            sb.append(groupName);
        }

        System.out.println(sb.toString());

        /*List<String> children = zkManager.getConf(groupName);
        for (String child : children) {
            if (groupName != "/") {
                getConfig(zkManager, groupName + "/" + child, path, result);
            } else {
                getConfig(zkManager, groupName + child, path, result);
            }
        }*/
    }
    
    




    
   


   
    

  
    
    
    
}

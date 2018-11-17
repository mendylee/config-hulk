package com.xiangrikui.hulk.web.zookeeper.node;

/**
 * 创建时间：2017年5月23日
 * <p>修改时间：2017年5月23日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class TreeNode {
    
    private String nodeKey;
    
    private String parentNodeKey;
    
    private String content;
    
    public TreeNode(){
    }
    
    public TreeNode(String nodeKey){
        this.nodeKey = nodeKey;
    }
    
    public TreeNode(String nodeKey,String parentNodeKey){
        this.nodeKey = nodeKey;
        this.parentNodeKey = nodeKey;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public String getParentNodeKey() {
        return parentNodeKey;
    }

    public void setParentNodeKey(String parentNodeKey) {
        this.parentNodeKey = parentNodeKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}

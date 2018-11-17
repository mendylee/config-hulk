package com.xiangrikui.hulk.web.zookeeper.node;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2017年5月23日
 * <p>修改时间：2017年5月23日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class ManyTreeNode {

    private TreeNode data;
    
    private List<ManyTreeNode> childList;

    public ManyTreeNode(TreeNode treeNode){
        this.data = treeNode;
        this.childList = new ArrayList<ManyTreeNode>();
    }
    
    public ManyTreeNode(){}
    
    
    public TreeNode getData() {
        return data;
    }

    public void setData(TreeNode treeNode) {
        this.data = treeNode;
    }

    public List<ManyTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<ManyTreeNode> childList) {
        this.childList = childList;
    }
    
    
}

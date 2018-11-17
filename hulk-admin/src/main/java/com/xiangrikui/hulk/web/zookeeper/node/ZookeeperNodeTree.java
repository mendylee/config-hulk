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
public class ZookeeperNodeTree {

    private ManyTreeNode root;
    
    
    /** 
     * 构造函数 
     */  
    public ZookeeperNodeTree()  
    {  
        root = new ManyTreeNode(new TreeNode("/hulk"));  
    } 
    
    public ZookeeperNodeTree createTree(List<TreeNode> treeNodeList){
        if(treeNodeList == null || treeNodeList.size()<0){
            return null;
        }
        ZookeeperNodeTree zkTreeNode = new ZookeeperNodeTree();
        for (TreeNode treeNode : treeNodeList) {
            if(treeNode.getParentNodeKey().equals("/hulk")){
                //在根路径添加一个节点
                zkTreeNode.getRoot().getChildList().add(new ManyTreeNode(treeNode));
            }else{
                addChild(zkTreeNode.getRoot(), treeNode);
            }
        }
        return zkTreeNode;
    }
    
    public void addChild(ManyTreeNode manyTreeNode,TreeNode child){
        for (ManyTreeNode item : manyTreeNode.getChildList()) {
            if(item.getData().getNodeKey().equals(child.getParentNodeKey())){
                item.getChildList().add(new ManyTreeNode(child));
                break;
            }else{
                if(item.getChildList()!=null && item.getChildList().size()>0){
                    addChild(item, child);
                }
            }
        }
    }
    
    /** 
     * 遍历多叉树  
     *  
     * @param manyTreeNode 多叉树节点 
     * @return  
     */  
    public String iteratorTree(ManyTreeNode manyTreeNode)  
    {  
        StringBuilder buffer = new StringBuilder();  
        buffer.append("\n");  
          
        if(manyTreeNode != null)   
        {     
            for (ManyTreeNode index : manyTreeNode.getChildList())   
            {  
                buffer.append(index.getData().getNodeKey()+ ",");  
                  
                if (index.getChildList() != null && index.getChildList().size() > 0 )   
                {     
                    buffer.append(iteratorTree(index));  
                }  
            }  
        }  
          
        buffer.append("\n");  
          
        return buffer.toString();  
    }  

    public ManyTreeNode getRoot() {
        return root;
    }

    public void setRoot(ManyTreeNode root) {
        this.root = root;
    }
    
    public static void main(String[] args) {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();  
        treeNodes.add(new TreeNode("系统权限管理", "/hulk"));  
        treeNodes.add(new TreeNode("用户管理", "系统权限管理"));  
        treeNodes.add(new TreeNode("角色管理", "系统权限管理"));  
        treeNodes.add(new TreeNode("组管理", "系统权限管理"));  
        treeNodes.add(new TreeNode("用户菜单管理", "系统权限管理"));  
        treeNodes.add(new TreeNode("角色菜单管理", "系统权限管理"));  
        treeNodes.add(new TreeNode("用户权限管理", "系统权限管理"));  
        treeNodes.add(new TreeNode("站内信", "/hulk"));  
        treeNodes.add(new TreeNode("写信", "站内信"));  
        treeNodes.add(new TreeNode("收信", "站内信"));  
        treeNodes.add(new TreeNode("草稿", "站内信"));  
          
        ZookeeperNodeTree tree = new ZookeeperNodeTree();  
          
        System.out.println(tree.iteratorTree(tree.createTree(treeNodes).getRoot()));  
    }
    
}

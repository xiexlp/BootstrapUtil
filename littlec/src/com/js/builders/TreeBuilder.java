package com.js.builders;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Enumeration;

/**
 * Created by Administrator on 2017-10-14.
 */
public class TreeBuilder {

    public static JTree GetATree(){

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("示例程序");
        DefaultMutableTreeNode group1 = new DefaultMutableTreeNode("简单程序");

        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("赋值语句");
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("条件语句");
        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("循环语句");

        //node1.setName("王雨");
        //node1.setNickName("漫天飞舞");
        group1.add(node1);
        group1.add(node2);
        group1.add(node3);
        top.add(group1);


        JTree tree = new JTree(top);
        ecTreeTest(tree);
        return tree;
    }


    public static void ecTreeTest(JTree tree) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        expandTree(tree, new TreePath(root));
    }

    private static void expandTree(JTree tree, TreePath parent) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandTree(tree, path);
            }
        }
        tree.expandPath(parent);
    }

}

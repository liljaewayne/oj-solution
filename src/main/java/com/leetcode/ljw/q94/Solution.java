package com.leetcode.ljw.q94;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * 难度: 易(递归) / 中(非递归)
 * <p>
 * 给定一个二叉树, 返回它的中序遍历结果, 返回值为List。
 */
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inTraversal(root, result);// 开始处理根节点
        return result;
    }

    private void inTraversal(TreeNode node, List<Integer> list) {
        if (node != null) {// 递归结束条件
            inTraversal(node.left, list);// 先处理左孩子
            list.add(node.val);// 在把当前节点处理( val放到结果集 )
            inTraversal(node.right, list);// 然后处理右孩子
        }
    }
    /*
     * 非递归算法需要借助其他线性表(栈), 实现较复杂, 还没调试通
     */
}
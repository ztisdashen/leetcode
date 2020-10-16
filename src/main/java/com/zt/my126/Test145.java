package com.zt.my126;

import java.util.*;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-09-29 10:47
 */
public class Test145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res=  new ArrayList<>();
        func(root,res);
        return res;
    }
    private void func(TreeNode node, List<Integer> res){
        if (node == null)return;
        func(node.left,res);
        func(node.right,res);
        res.add(node.val);
    }

    /**
     *    1
     *     \
     *      2
     *     /
     *    3
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        Test145_1 t = new Test145_1();
        System.out.println(t.postorderTraversal(root));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}

/**
 * 非递归版
 */
class Test145_1{
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res=  new ArrayList<>();
        if (root == null)return res;
        // 处于栈顶的一定是某棵子树最左边的节点 但不能保证他的右子树空的
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode,Boolean> map = new HashMap<>();
        func(root,stack);
        while (!stack.isEmpty()){
            TreeNode inResult = stack.peek();
            if (inResult.right!=null && !map.getOrDefault(inResult.right,false)){
                func(inResult.right,stack);
            }else {
                TreeNode pop = stack.pop();
                map.put(pop,true);
                res.add(inResult.val);
            }
        }
        return res;
    }

    public void func(TreeNode node, Stack<TreeNode> stack){
        stack.push(node);
        if (node.left == null) {
        }
        else {
            func(node.left,stack);
        }
    }
}

package com.wuxp.tdd.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归遍历二叉树，递归实现
 *
 * @author wuxp
 */
public class TreeForEachRecursion {


    /**
     * 树的先序遍历 （根左右）
     */
    public Integer[] preorder(BinaryTree<Integer> tree) {
        final List<Integer> result = new ArrayList<>();
        preorder(tree.getHead(), result);
        return result.toArray(new Integer[0]);
    }

    private void preorder(final BinaryTree.Node<Integer> node, final List<Integer> elements) {
        if (node == null) {
            return;
        }
        elements.add(node.getValue());
        preorder(node.getLeft(), elements);
        preorder(node.getRight(), elements);
    }

    /**
     * 树的中序遍历 （左根右）
     */
    public Integer[] middleOrder(BinaryTree<Integer> tree) {
        final List<Integer> result = new ArrayList<>();
        middleOrder(tree.getHead(), result);
        return result.toArray(new Integer[0]);
    }

    private void middleOrder(final BinaryTree.Node<Integer> node, final List<Integer> elements) {
        if (node == null) {
            return;
        }
        middleOrder(node.getLeft(), elements);
        elements.add(node.getValue());
        middleOrder(node.getRight(), elements);
    }

    /**
     * 树的后序遍历 （左右根）
     */
    public Integer[] postOrder(BinaryTree<Integer> tree) {
        final List<Integer> result = new ArrayList<>();
        postOrder(tree.getHead(), result);
        return result.toArray(new Integer[0]);
    }

    private void postOrder(final BinaryTree.Node<Integer> node, final List<Integer> elements) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft(), elements);
        postOrder(node.getRight(), elements);
        elements.add(node.getValue());
    }


}

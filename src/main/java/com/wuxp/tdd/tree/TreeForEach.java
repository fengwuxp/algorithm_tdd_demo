package com.wuxp.tdd.tree;

import java.util.*;

/**
 * 递归遍历二叉树，非递归实现
 *
 * @author wuxp
 */
public class TreeForEach {


    /**
     * 树的先序遍历 （根左右）
     */
    public Integer[] preorder(BinaryTree<Integer> tree) {
        final List<Integer> result = new ArrayList<>();
        Stack<BinaryTree.Node<Integer>> stack = new Stack<>();
        stack.push(tree.getHead());
        while (!stack.isEmpty()) {
            BinaryTree.Node<Integer> node = stack.pop();
            result.add(node.getValue());
            pushNodeToStack(stack, node.getRight());
            pushNodeToStack(stack, node.getLeft());
        }
        return result.toArray(new Integer[0]);
    }

    private void pushNodeToStack(Stack<BinaryTree.Node<Integer>> stack, BinaryTree.Node<Integer> node) {
        if (node != null) {
            stack.push(node);
        }
    }


    /**
     * 树的中序遍历 （左根右）
     */
    public Integer[] middleOrder(BinaryTree<Integer> tree) {
        final List<Integer> result = new ArrayList<>();
        Stack<BinaryTree.Node<Integer>> stack = new Stack<>();
        BinaryTree.Node<Integer> node = tree.getHead();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                result.add(node.getValue());
                node = node.getRight();
            }
        }
        return result.toArray(new Integer[0]);
    }


    /**
     * 树的后序遍历 （左右根）
     */
    public Integer[] postOrder(BinaryTree<Integer> tree) {
        Stack<BinaryTree.Node<Integer>> stack = new Stack<>();
        Stack<BinaryTree.Node<Integer>> nodes = new Stack<>();
        stack.push(tree.getHead());
        while (!stack.isEmpty()) {
            BinaryTree.Node<Integer> node = stack.pop();
            nodes.push(node);
            pushNodeToStack(stack, node.getLeft());
            pushNodeToStack(stack, node.getRight());
        }
        final List<Integer> result = new ArrayList<>();
        while (!nodes.isEmpty()) {
            result.add(nodes.pop().getValue());
        }
        return result.toArray(new Integer[0]);
    }

    /**
     * 树的后序遍历 （左右根）
     */
    public Integer[] postOrder2(BinaryTree<Integer> tree) {
        final List<Integer> result = new ArrayList<>();
        Stack<BinaryTree.Node<Integer>> nodes = new Stack<>();
        nodes.add(tree.getHead());
        BinaryTree.Node<Integer> prevProcessNode = null;
        while (!nodes.isEmpty()) {
            BinaryTree.Node<Integer> peekNode = nodes.peek();
            if (peekNode.getLeft() != null && isNotPrevProcessNode(prevProcessNode, peekNode)) {
                nodes.push(peekNode.getLeft());
            } else if (peekNode.getRight() != null && isNotPrevProcessRightNode(prevProcessNode, peekNode)) {
                nodes.push(peekNode.getRight());
            } else {
                prevProcessNode = nodes.pop();
                result.add(prevProcessNode.getValue());
            }
        }
        return result.toArray(new Integer[0]);
    }

    private boolean isNotPrevProcessNode(BinaryTree.Node<Integer> prevProcessNode, BinaryTree.Node<Integer> peekNode) {
        return peekNode.getLeft() != prevProcessNode && isNotPrevProcessRightNode(prevProcessNode, peekNode);
    }

    private boolean isNotPrevProcessRightNode(BinaryTree.Node<Integer> prevProcessNode, BinaryTree.Node<Integer> peekNode) {
        return peekNode.getRight() != prevProcessNode;
    }

    /**
     * 树的宽度遍历 （按层）
     */
    public Integer[] byLayer(BinaryTree<Integer> tree) {
        final List<Integer> result = new ArrayList<>();
        byLayer(tree.getHead(), result);
        return result.toArray(new Integer[0]);
    }

    private void byLayer(final BinaryTree.Node<Integer> head, final List<Integer> elements) {
        if (head == null) {
            return;
        }
        Queue<BinaryTree.Node<Integer>> nodes = new ArrayDeque<>();
        nodes.add(head);
        while (!nodes.isEmpty()) {
            BinaryTree.Node<Integer> node = nodes.poll();
            elements.add(node.getValue());
            pushNodeToQueue(nodes, node.getLeft());
            pushNodeToQueue(nodes, node.getRight());
        }
    }

    private void pushNodeToQueue(Queue<BinaryTree.Node<Integer>> nodes, BinaryTree.Node<Integer> node) {
        if (node != null) {
            nodes.add(node);
        }
    }
}

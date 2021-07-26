package com.wuxp.tdd.tree;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 二叉树
 *
 * @author wuxp
 */
public class BinaryTree<T extends Serializable> {

    private final Node<T> head;

    public BinaryTree(Node<T> head) {
        this.head = head;
    }

    public Node<T> getHead() {
        return head;
    }

    public static <E extends Serializable> BinaryTree<E> form(E... elements) {
        return form(Arrays.asList(elements));
    }

    /**
     * List<E> to 二叉树
     * <p>
     * 如果头结点存储在下标为 i 的位置，则左子节点下标为 2 * i + 1，右子节点下标为 2 * i + 2
     */
    public static <E extends Serializable> BinaryTree<E> form(List<E> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("elements must not empty");
        }
        Node<E> head = new Node<>(elements.get(0));
        buildChildNodes(elements, head, 0);
        return new BinaryTree<>(head);
    }

    private static <E extends Serializable> void buildChildNodes(List<E> elements, Node<E> parent, int parentIndex) {
        int leftIndex = 2 * parentIndex + 1;
        parent.left = buildChildNode(elements, leftIndex);
        parent.right = buildChildNode(elements, leftIndex + 1);
    }

    private static <E extends Serializable> Node<E> buildChildNode(List<E> elements, int index) {
        Node<E> result = buildNode(elements, index);
        if (result != null) {
            buildChildNodes(elements, result, index);
        }
        return result;
    }

    private static <E extends Serializable> Node<E> buildNode(List<E> elements, int index) {
        if (index < elements.size()) {
            return new Node<>(elements.get(index));
        }
        return null;
    }


    public static class Node<T extends Serializable> {

        private final T value;

        private Node<T> left;

        private Node<T> right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value) && Objects.equals(left, node.left) && Objects.equals(right, node.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, left, right);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

}

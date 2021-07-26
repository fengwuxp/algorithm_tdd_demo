package com.wuxp.tdd.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author wuxp
 */
class TreeForEachTests {

    private final TreeForEach treeForEach = new TreeForEach();

    @Test
    void testPreorder() {
        BinaryTree<Integer> tree = BinaryTree.form(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer[] result = treeForEach.preorder(tree);
        System.out.println(Arrays.toString(result));
        Assertions.assertTrue(Arrays.deepEquals(new Integer[]{1, 2, 4, 8, 9, 5, 3, 6, 7}, result));
    }

    @Test
    void testMiddleOrder() {
        BinaryTree<Integer> tree = BinaryTree.form(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer[] result = treeForEach.middleOrder(tree);
        System.out.println(Arrays.toString(result));
        Assertions.assertTrue(Arrays.deepEquals(new Integer[]{8, 4, 9, 2, 5, 1, 6, 3, 7}, result));
    }

    @Test
    void testPostOrder() {
        BinaryTree<Integer> tree = BinaryTree.form(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer[] result = treeForEach.postOrder(tree);
        System.out.println(Arrays.toString(result));
        Assertions.assertTrue(Arrays.deepEquals(new Integer[]{8, 9, 4, 5, 2, 6, 7, 3, 1}, result));
    }

    @Test
    void testPostOrder2() {
        BinaryTree<Integer> tree = BinaryTree.form(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer[] result = treeForEach.postOrder2(tree);
        System.out.println(Arrays.toString(result));
        Assertions.assertTrue(Arrays.deepEquals(new Integer[]{8, 9, 4, 5, 2, 6, 7, 3, 1}, result));
    }

    @Test
    void testByLayer() {
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 3};
        BinaryTree<Integer> tree = BinaryTree.form(integers);
        Integer[] result = treeForEach.byLayer(tree);
        System.out.println(Arrays.toString(result));
        Assertions.assertTrue(Arrays.deepEquals(integers, result));
    }


}

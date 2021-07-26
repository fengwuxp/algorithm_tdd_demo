package com.wuxp.tdd.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author wuxp
 */
class TreeForEachRecursionTests {

    private final TreeForEachRecursion treeForEach = new TreeForEachRecursion();

    @Test
    void testPreorder() {
        BinaryTree<Integer> tree = BinaryTree.form(1, 2, 3, 4, 5, 6, 7, 8);
        Integer[] result = treeForEach.preorder(tree);
        Assertions.assertTrue(Arrays.deepEquals(new Integer[]{1, 2, 4, 8, 5, 3, 6, 7}, result));
    }

    @Test
    void testMiddleOrder() {
        BinaryTree<Integer> tree = BinaryTree.form(1, 2, 3, 4, 5, 6, 7, 8);
        Integer[] result = treeForEach.middleOrder(tree);
        Assertions.assertTrue(Arrays.deepEquals(new Integer[]{8, 4, 2, 5, 1, 6, 3, 7}, result));
    }

    @Test
    void testPostOrder() {
        BinaryTree<Integer> tree = BinaryTree.form(1, 2, 3, 4, 5, 6, 7, 8);
        Integer[] result = treeForEach.postOrder(tree);
        Assertions.assertTrue(Arrays.deepEquals(new Integer[]{8, 4, 5, 2, 6, 7, 3, 1}, result));
    }


}

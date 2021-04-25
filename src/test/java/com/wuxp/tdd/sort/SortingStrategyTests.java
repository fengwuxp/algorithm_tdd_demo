package com.wuxp.tdd.sort;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SortingStrategyTests {


    @Test
    void testBubbleSort() {
        Integer[] sources = Arrays.asList(0, 98, 32, 8, 293, 8871, 10, 2, 1).toArray(new Integer[0]);
        SortingStrategy<Integer> bubble = new BubbleSortingStrategy<>();
        bubble.sort(sources);
        Assertions.assertArrayEquals(new Integer[]{0, 1, 2, 8, 10, 32, 98, 293, 8871}, sources);
    }

    @Test
    void testSelectSort() {
        testSortByComparator(new SelectSortingStrategy<>(), new BubbleSortingStrategy<>());
    }

    @Test
    void testInsertionSort() {
        testSortByComparator(new InsertionSortingStrategy<>(), new BubbleSortingStrategy<>());
    }

    @Test
    void testMergeSort() {
        testSortByComparator(1, new MergeSortingStrategy<>(), new BubbleSortingStrategy<>());
    }

    @Test
    void testQuickSortV1() {
        testSortByComparator(new QuickV1SortingStrategy<>(), new BubbleSortingStrategy<>());
    }

    @Test
    void testQuickSortV2() {
        testSortByComparator(new QuickV2SortingStrategy<>(), new BubbleSortingStrategy<>());
    }

    @Test
    void testQuickSortV3() {
        testSortByComparator(new QuickV3SortingStrategy<>(), new BubbleSortingStrategy<>());
    }

    @Test
    void testHeapSort(){
        testSortByComparator(new HeapSortingStrategy<>(), new BubbleSortingStrategy<>());
    }

    private static void testSortByComparator(SortingStrategy<Integer> strategy, SortingStrategy<Integer> comparator) {
        testSortByComparator(RandomUtil.randomInt(1, 10), strategy, comparator);
    }

    private static void testSortByComparator(int testTimes, SortingStrategy<Integer> strategy, SortingStrategy<Integer> comparator) {
        Assertions.assertTrue(testTimes > 0);
        while (testTimes-- > 0) {
            int[] numbers = RandomUtil.randomInts(RandomUtil.randomInt(1, 10000));
            Integer[] sources1 = Arrays.stream(numbers)
                    .boxed()
                    .toArray(Integer[]::new);
            Integer[] sources2 = Arrays.copyOf(sources1, sources1.length);
            strategy.sort(sources1);
            comparator.sort(sources2);
            Assertions.assertArrayEquals(sources2, sources1);
        }
    }


}

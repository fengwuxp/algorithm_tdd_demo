package com.wuxp.tdd.sort;


/**
 * 选择排序
 *
 * @param <T>
 * @author wuxp
 */
public class SelectSortingStrategy<T extends Comparable<T>> extends AbstractSortingStrategy<T> {

    /**
     * 0 ~ N-1  找到最小值，在哪，放到0 位置上
     * 1 ~ n-1  找到最小值，在哪，放到1 位置上
     * 2 ~ n-1  找到最小值，在哪，放到2 位置上
     *
     * @param sources 需要排序的内容
     */
    @Override
    protected void internalSort(T[] sources) {
        int length = sources.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                // i ~ N-1 上找最小值的下标
                if (sources[minIndex].compareTo(sources[j]) > 0) {
                    minIndex = j;
                }
            }
            swap(sources, i, minIndex);
        }
    }
}

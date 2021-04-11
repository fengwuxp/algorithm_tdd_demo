package com.wuxp.tdd.sort;

/**
 * 冒泡排序
 *
 * @author wuxp
 */
public class BubbleSortingStrategy<T extends Comparable<T>> extends AbstractSortingStrategy<T> {

    @Override
    protected void internalSort(T[] sources) {
        int length = sources.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (sources[i].compareTo(sources[j]) > 0) {
                    swap(sources, i, j);
                }
            }
        }
    }
}

package com.wuxp.tdd.sort;

/**
 * 排序策略
 *
 * @author wuxp
 */
public interface SortingStrategy<T extends Comparable<T>> {


    /**
     * 排序
     *
     * @param sources 需要排序的内容
     */
    void sort(T[] sources);
}

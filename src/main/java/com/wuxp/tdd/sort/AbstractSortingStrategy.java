package com.wuxp.tdd.sort;


/**
 * @author wuxp
 */
public abstract class AbstractSortingStrategy<T extends Comparable<T>> implements SortingStrategy<T> {

    @Override
    public void sort(T[] sources) {
        if (supportSort(sources)) {
            internalSort(sources);
        }
    }

    /**
     * @param sources 需要排序的内容
     */
    protected abstract void internalSort(T[] sources);

    /**
     * 交换arr的i和j位置上的值
     *
     * @param sources
     * @param i
     * @param j
     */
    protected void swap(T[] sources, int i, int j) {
        T temp = sources[i];
        sources[i] = sources[j];
        sources[j] = temp;
    }

    protected boolean supportSort(T[] sources) {
        return sources != null && sources.length > 1;
    }
}

package com.wuxp.tdd.sort;

/**
 * 插入排序：
 * <p>
 * 插入排序是指在待排序的元素中，假设前面n-1(其中n>=2)个数已经是排好顺序的，
 * 现将第n个数插到前面已经排好的序列中，然后找到合适自己的位置，使得插入第n个数的这个序列也是排好顺序的。
 * 按照此法对所有元素进行插入，直到整个序列排为有序的过程，称为插入排序
 * </p>
 *
 * @author wuxp
 */
public class InsertionSortingStrategy<T extends Comparable<T>> extends AbstractSortingStrategy<T> {


    /**
     * 将传入的数组{@param sources} 分为2块，一块是已经排好序的有序块，一块是需要插入到有序块中的插入数据源块，这样
     * 做是为了减少空间复杂度。
     * <p>
     * 一开始我们认为有序块的长度为0，记为 orderedLength，数据源块的长度为数组的长度，当我们循环向有序块插入数据
     * 时，需要一个nextInsertIndex指向下一个用于插入到有序块的变量，且 nextInsertIndex = orderedLength -1。
     * <p>
     * 数据插入有序块的逻辑{@link #insertNext(T[], int)}
     *
     * @param sources 需要排序的内容
     */
    @Override
    protected void internalSort(T[] sources) {
        for (int i = 1; i < sources.length; i++) {
            // 遍历向通过被 orderedLen 变量标记的数组插入数据
            insertNext(sources, i);
        }
    }

    /**
     * 插入一个数据到 sources 的有序块中，有序块的范围为 0 ~ (nextInsertIndex-1)，
     * 当数据插入成功后，有序块的长度+1
     * <p>
     * <b>注意</b>：这里实现的插入逻辑和直觉上的不太一样，假设期望将数据插入到有序块的某个位置,记为 index，则需要
     * 将 index ~ orderLength 的数据复制到 (index + 1) ~ (orderLength + 1) 中，然后将原本 nextInsertIndex
     * 位置的数据插入到 index，这样代码实现太过复杂。所以在每次比较时发现 nextInsertIndex 位置的数据比当前位置的数据要小就进行交换。
     * </p>
     *
     * @param sources         需要排序的内容
     * @param nextInsertIndex 在 sources 要用于插入到有序块中的下标
     */
    private void insertNext(T[] sources, int nextInsertIndex) {
        for (int i = 0; i < nextInsertIndex; i++) {
            // 比较插入的数据，如果小于则交换，
            if (sources[nextInsertIndex].compareTo(sources[i]) < 0) {
                swap(sources, i, nextInsertIndex);
            }
        }
    }
}

package com.wuxp.tdd.sort;

import cn.hutool.core.util.RandomUtil;

/**
 * 堆排序
 * <p>
 * 通过将传入的数组构建层一个大根堆，完成排序
 * </p>
 * <p>
 * 堆是一个完全二叉树，通过数组表示时，对应任意位置 i 节点存在如下关系
 * 左孩子 = 2 * i + 1
 * 右孩子 = 2 * i + 2 ==> 2  * (i + 1)
 * 父节点 = (i - 1) / 2
 *
 * @param <T>
 * @author wuxp
 */
public class HeapSortingStrategy<T extends Comparable<T>> extends AbstractSortingStrategy<T> {

    @Override
    protected void internalSort(T[] sources) {
        int heapSize = sources.length;
        if (RandomUtil.randomBoolean()) {
            /**
             * 常规写法，模拟向堆插入数据，构建大根堆
             */
            for (int i = 0; i < heapSize; i++) {
                internInsert(sources, i, 1);
            }
        } else {
            /**
             *  优化手段
             *  通过 反向地调整一颗颗子树为大根堆，最后得到一个大根堆
             */
            int maxParentIndex = (heapSize - 1) >> 1;
            for (int i = maxParentIndex; i >= 0; i--) {
                internHeapify(sources, i, heapSize, 1);
            }
        }

        while (--heapSize >= 0) {
            swap(sources, 0, heapSize);
            internHeapify(sources, 0, heapSize, 1);
        }
    }

    /**
     * 新加进来的数，现在停在了 index 位置，请依次往上移动，移动到堆顶（数组下标0）位置，或者干不掉自己的父亲了，停！
     *
     * @param eqVal 大于或小于的标记
     */
    private void internInsert(T[] sources, int index, int eqVal) {
        while (sources[index].compareTo(sources[getParentIndex(index)]) == eqVal) {
            int nextIndex = getParentIndex(index);
            swap(sources, index, nextIndex);
            index = nextIndex;
        }
    }

    /**
     * 堆的 heapify
     * 从 index 位置，往下看，不断的下沉。
     * 停止条件：较大(小)的孩子都不再比index位置的数大(小)，或者已经没孩子了
     */
    private void internHeapify(T[] sources, int parentIndex, int heapSize, int eqVal) {
        int leftIndex = getLeftIndex(parentIndex);
        // 如果有左孩子，有没有右孩子，可能有可能没有！
        while (leftIndex < heapSize) {
            int eqValIndex = leftIndex;
            // 如果右孩子比左孩子大
            int rightIndex = leftIndex + 1;
            if (rightIndex < heapSize) {
                eqValIndex = compareValueReturnEqIndex(sources, leftIndex, rightIndex, eqVal);
            }
            // 比较左右孩子较大的值和 index 位置的值
            eqValIndex = compareValueReturnEqIndex(sources, parentIndex, eqValIndex, eqVal);
            if (eqValIndex == parentIndex) {
                break;
            }
            // index和较大(小)孩子，要互换
            swap(sources, eqValIndex, parentIndex);
            parentIndex = eqValIndex;
            leftIndex = getLeftIndex(parentIndex);
        }
    }

    private int compareValueReturnEqIndex(T[] sources, int v1Index, int v2Index, int eqVal) {
        return sources[v2Index].compareTo(sources[v1Index]) == eqVal ? v2Index : v1Index;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftIndex(int index) {
        return 2 * index + 1;
    }
}

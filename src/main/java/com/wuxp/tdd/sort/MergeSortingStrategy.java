package com.wuxp.tdd.sort;

/**
 * 归并排序
 * 归并排序（Merge Sort）是建立在归并操作上的一种有效，稳定的排序算法，该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。
 *
 * @author wuxp
 */
public class MergeSortingStrategy<T extends Comparable<T>> extends AbstractSortingStrategy<T> {

    /**
     * 假定函数 f(L,R)，对数组 L~R 范围的数据进行排序，那么，我们将 sources 数组进行层层二分，二分的结果记为 sourcesL、sourcesR，
     * 每次二分的过程记为 f(L',R')，直到L'=R' 为止。每次二分排序后 sourcesL，sourcesR 的数据都有序了，则进行合并记为 merge(sourcesL,sourcesR)，
     * 将 sourcesR 合并到 sourcesL 上。
     *
     * @param sources
     */
    @Override
    protected void internalSort(T[] sources) {

        dichotomy(sources, 0, sources.length - 1);
    }

    /**
     * 二分数组
     * left和right 用于标记此次二分动作在 sources 操作的范围。
     *
     * @param sources 原始排序的数据
     * @param left    参与二分的左侧边界
     * @param right   参与二分的右侧边界
     */
    private void dichotomy(T[] sources, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        dichotomy(sources, left, mid);
        dichotomy(sources, mid + 1, right);
        merge(sources, left, mid, right);
    }

    /**
     * 合并数组
     * left和right 用于标记此次合并动作在 sources 操作的范围
     *
     * @param sources 原始排序的数据
     * @param left    参与合并的左侧边界
     * @param mid     用于区分此次合并操作的左右2个数组
     * @param right   参与合并的右侧边界
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void merge(Comparable[] sources, int left, int mid, int right) {

        Comparable[] temps = new Comparable[right - left + 1];
        int tempLen = 0;
        int leftNext = left;
        int rightNext = mid + 1;
        while (leftNext <= mid && rightNext <= right) {
            boolean isCopyRight = sources[leftNext].compareTo(sources[rightNext]) > 0;
            // 左侧数据大于右侧数据，使用右侧，否则使用左侧
            if (isCopyRight) {
                temps[tempLen++] = sources[rightNext];
                rightNext++;
            } else {
                temps[tempLen++] = sources[leftNext];
                leftNext++;
            }
        }
        while (leftNext <= mid) {
            temps[tempLen++] = sources[leftNext++];
        }
        while (rightNext <= right) {
            temps[tempLen++] = sources[rightNext++];
        }
        System.arraycopy(temps, 0, sources, left, temps.length);
    }

}


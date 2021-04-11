package com.wuxp.tdd.sort;

/**
 * 快速排序 1.0版本
 * <p>
 * 将数组 L~R 范围的数据按照在该范围内的某个数据 X 进行分区，小于等于X的放到数组左边，大于X在右边。
 * X 所在的位置记为 M，然后对 L~M，M + 1 ~ R 上的数据分别按照上述逻辑重复（递归）处理
 * <p>
 * <b>缺点</b>：每次{@link #process(Comparable[], int, int)} 只能排好一个位置的数据，时间复杂度为 O(N²)
 *
 * @author wuxp
 */
public class QuickV1SortingStrategy<T extends Comparable<T>> extends AbstractSortingStrategy<T> {

    @Override
    protected void internalSort(T[] sources) {
        process(sources, 0, sources.length - 1);
    }

    private void process(T[] sources, int left, int right) {
        if (left >= right) {
            return;
        }
        // M 位置的数据以及排好序了
        int m = partition(sources, left, right);
        process(sources, left, m - 1);
        process(sources, m + 1, right);

    }

    /**
     * 数据分区
     * left和right 用于标记此次二分动作在 sources 操作的范围。
     * 固定使用 sources[right] 上的数据用做分区，已简化逻辑处理
     * <p>
     * 假定一开始 小于等于 X 的数据区域为 left - 1 记为 M，当遍历数据时有以下2种情况
     * 1：sources[index] 大于 X , 继续遍历
     * 2：sources[index] 小于等于 X , 交换 index 和 M + 1 位置的数据，然后 M + 1
     * </p>
     * 直到遍历的下标 index = right - 1时 结束循环
     * <p>
     * example:
     * X = 4 , M = -1 ,i = 0
     * 1 3 12 2  6 3 9 11
     * ==> M = 2 , i = 3 , 12 > 4 ， i + 1
     * ==> M = 2 , i = 4 , 2 < 4 ， 交换 12 和 2， M + 1 = 3
     * 1 3 2 12  6 3 9 11
     * </p>
     *
     * @param sources 原始排序的数据
     * @param left    参与分区的左侧边界
     * @param right   参与分区的右侧边界
     * @return 用作分区的数据 X 所在数组的位置，该位置的数据已经在 left ~ right 的范围上排好序了
     */
    private int partition(T[] sources, int left, int right) {
        int M = left - 1;
        int i = left;
        while (i < right) {
            // 数据小于等于用作分区的值 X , 进行交换
            if (sources[right].compareTo(sources[i]) > 0) {
                swap(sources, ++M, i);
            }
            i++;
        }
        swap(sources, ++M, right);
        return M;
    }
}

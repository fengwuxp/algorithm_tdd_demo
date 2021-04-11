package com.wuxp.tdd.sort;

/**
 * 快速排序 2.0 版本
 * 在1.0 版本{@link QuickV1SortingStrategy} 每次只能排好一个数据的情况下进行优化，每次{@link #process(Comparable[], int, int)}
 * 在 L~R 的范围上处理分区时，将和用于分区的数据 X 值相同的一组数据排好序，也就是每次处理可能将多个值的顺序排好（如果 X 的值存在多个的话）
 * <b>缺点</b>：由于每次都是用于分区的数值选择都是固定的，最坏的情况下可能每次都是选中的值用于分区出来的结果都是最差的，即：数据都都分布在
 * 同一侧，都在左侧或都在右侧
 *
 * @author wuxp
 */
public class QuickV2SortingStrategy<T extends Comparable<T>> extends AbstractSortingStrategy<T> {

    @Override
    protected void internalSort(T[] sources) {
        process(sources, 0, sources.length - 1);
    }

    /**
     * 选择用于分区的数据
     *
     * @param sources 原始排序的数据
     * @param left    参与分区的左侧边界
     * @param right   参与分区的右侧边界
     * @return 用于分区的数据
     */
    protected T choosePartitionVal(T[] sources, int left, int right) {
        return sources[right];
    }

    private void process(T[] sources, int left, int right) {
        if (left >= right) {
            return;
        }
        // M 位置的数据以及排好序了
        int[] eqRang = partition(sources, left, right);
        process(sources, left, eqRang[0] - 1);
        process(sources, eqRang[1] + 1, right);
    }

    /**
     * 分区处理采用荷兰国旗问题的解决方式
     * 将 left~right上的数据 分为 小于 X 、等于 X 、大于 X 的3快，大致如下
     * <pre>
     *      Lt              Eq            Gt
     * left ~ M         M + 1 ~ N     N + 1 ~ right
     * </pre>
     * 小于 X 的区域右边界记作 M
     * 大于 X 的区域左边界记作 N
     * <p>
     * 1：sources[i] 小于 X，交换 i 和 M + 1 位置的数据，M = M + 1 (将数据移动到小于区域) i = i + 1
     * 2：sources[i] 等于 X，i + 1
     * 3：sources[i] 大于 X，交换 i 和 N - 1 位置的数据，N = N - 1 (将数据移动到大于区域)，i 保持不变，因为交换过来的数据
     * 还没有进行处理
     * </p>
     * 当 i = N 时处理完成
     *
     * @param sources 原始排序的数据
     * @param left    参与分区的左侧边界
     * @param right   参与分区的右侧边界
     * @return 等于 X 的数据在sources的范围，该范围上的数据已经在 left ~ right 的范围上排好序了
     */
    private int[] partition(T[] sources, int left, int right) {
        int i = left;
        // 小于区右边界
        int M = left - 1;
        // 大于区左边界
        int N = right + 1;
        T eq = choosePartitionVal(sources, left, right);
        while (i < N) {
            int flag = sources[i].compareTo(eq);
            if (flag == 0) {
                i++;
            } else if (flag < 0) {
                swap(sources, i++, ++M);
            } else {
                swap(sources, i, --N);
            }
        }
        return new int[]{M + 1, N - 1};
    }
}

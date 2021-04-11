package com.wuxp.tdd.sort;

import cn.hutool.core.util.RandomUtil;

/**
 * 快速排序 3.0
 * 由于在 2.0版本 {@link QuickV2SortingStrategy} 中，选择用于分区的数据策略为固定的方式会导致在最坏的情况下每次命中最差的情况，导致
 * 时间复杂度为O(N²)，那么我们只要把固定选择用于分区的数据改为随机选择就可以将出现最坏的情况降低到 1/N，时间复杂度的求数学期望值将变成
 * O(N * logN)
 *
 * @author wuxp
 */
public class QuickV3SortingStrategy<T extends Comparable<T>> extends QuickV2SortingStrategy<T> {

    @Override
    protected T choosePartitionVal(T[] sources, int left, int right) {
        // 随机从 left ~ right 的范围上获取一个数据用作分区
        return sources[RandomUtil.randomInt(left, right)];
    }
}

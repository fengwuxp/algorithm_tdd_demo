package com.wuxp.tdd;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 滑动时间窗口实现
 * // TODO 是否还有优化空间
 */
public final class SlidingTimeWindow {

    private final double windowMilli;

    private final LongAdder[] buckets;

    private final AtomicLong prevWindowSize;

    public SlidingTimeWindow(int windowTotalMilli, int bucketSize) {
        this.windowMilli = windowTotalMilli * 1.0d / bucketSize;
        this.buckets = initBuckets(bucketSize);
        this.prevWindowSize = new AtomicLong(0);
    }

    public long getTotal() {
        calculateIndexAndResetBucket();
        return Arrays.stream(buckets).mapToLong(LongAdder::longValue).sum();
    }

    public void increase() {
        buckets[calculateIndexAndResetBucket()].increment();
    }

    private LongAdder[] initBuckets(int bucketSize) {
        LongAdder[] result = new LongAdder[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            result[i] = new LongAdder();
        }
        return result;
    }

    /**
     * @return 窗口当前 index = 当前时间 / 滑动窗口大小 % 窗口个数
     */
    private int calculateIndexAndResetBucket() {
        // 当前时间可以划分的窗口个数
        final long windowSize = (long) Math.floor(getCurrentTimeMillis() / windowMilli);
        // 通过取余计算得到当前时间命中的窗口
        final int result = (int) (windowSize % buckets.length);
        resetBucketValue(windowSize, result);
        prevWindowSize.set(windowSize);
        return result;
    }

    /**
     * 尝试重置滑动窗口 bucket 的值，可能会重置0个或多个
     *
     * @param windowSize         当前时间除以 {@link #windowMilli} 得到的窗口个数
     * @param currentBucketIndex 通过当前时间计算得到的 bucket index
     */
    private void resetBucketValue(long windowSize, int currentBucketIndex) {
        // 当前时间和上一次命中的窗口距离
        final long windowDistance = windowSize - prevWindowSize.longValue();
        final int bucketLength = buckets.length;
        // 滑动了一个或多个窗口，最多重置 bucketSize 个窗口的值
        final long restBucketTotal = windowDistance > bucketLength ? bucketLength : windowDistance;
        for (int i = 0; i < restBucketTotal; i++) {
            int index = currentBucketIndex - i;
            if (index < 0) {
                // 循环，从数组末尾开始重置
                index = index + bucketLength;
            }
            buckets[index].reset();
        }
    }

    private long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}

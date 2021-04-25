package com.wuxp.tdd.datastructure;

/**
 * 使用数组实现堆结构
 *
 * @author wuxp
 */
public class HeapExample<T extends Comparable<T>> {

    private Comparable[] sources;

    private int size;

    private int arrayLength;

    private final boolean big;

    public HeapExample() {
        this(16);
    }

    public HeapExample(int length) {
        this(length, true);
    }

    public HeapExample(int length, boolean big) {
        if (length < 0) {
            throw new IllegalArgumentException("heap size must greater than 0");
        }
        this.sources = new Comparable[length];
        this.size = 0;
        this.arrayLength = length;
        this.big = big;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(T val) {
        if (this.size == this.arrayLength) {
            resize();
        }
        this.sources[size] = val;
        if (big) {
            this.internInsert(size, 1);
        } else {
            this.internInsert(size, -1);
        }
        this.size++;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) {
            throw new UnsupportedOperationException("heap is empty");
        }
        Comparable<?> val = sources[0];
        swap(0, --size);
        heapify(0);
        return (T) val;
    }

    @SuppressWarnings("rawtypes")
    private void resize() {
        Comparable[] prevValues = this.sources;
        int newLength = arrayLength << 1;
        this.sources = new Comparable[newLength];
        System.arraycopy(prevValues, 0, this.sources, 0, arrayLength);
        this.arrayLength = newLength;
    }


    /**
     * 新加进来的数，现在停在了 index 位置，请依次往上移动，移动到堆顶（数组下标0）位置，或者干不掉自己的父亲了，停！
     *
     * @param index 新加入数据的位置，当前数组的长度
     * @param eqVal 大于或小于的标记
     */
    @SuppressWarnings("unchecked")
    private void internInsert(int index, int eqVal) {
        while (sources[index].compareTo(sources[getParentIndex(index)]) == eqVal) {
            int nextIndex = getParentIndex(index);
            swap(index, nextIndex);
            index = nextIndex;
        }
    }

    private void heapify(int index) {
        if (big) {
            internHeapify(index, 1);
        } else {
            internHeapify(index, -1);
        }
    }

    /**
     * 堆的 heapify
     * 从index位置，往下看，不断的下沉。
     * 停止条件：较大(小)的孩子都不再比index位置的数大(小)，或者已经没孩子了
     */
    private void internHeapify(int index, int eqVal) {
        int leftIndex = getLeftIndex(index);
        // 如果有左孩子，有没有右孩子，可能有可能没有！
        while (leftIndex < size) {
            int eqValIndex = leftIndex;
            // 如果右孩子比左孩子大
            int rightIndex = leftIndex + 1;
            if (rightIndex < size) {
                eqValIndex = compareValueReturnEqIndex(leftIndex, rightIndex, eqVal);
            }
            // 比较左右孩子较大的值和 index 位置的值
            eqValIndex = compareValueReturnEqIndex(index, eqValIndex, eqVal);
            if (eqValIndex == index) {
                break;
            }
            // index和较大(小)孩子，要互换
            swap(eqValIndex, index);
            index = eqValIndex;
            leftIndex = getLeftIndex(index);
        }
    }

    @SuppressWarnings("unchecked")
    private int compareValueReturnEqIndex(int v1Index, int v2Index, int eqVal) {
        return sources[v2Index].compareTo(sources[v1Index]) == eqVal ? v2Index : v1Index;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftIndex(int index) {
        return 2 * index + 1;
    }

    /**
     * 交换arr的i和j位置上的值
     *
     * @param i
     * @param j
     */
    protected void swap(int i, int j) {
        Comparable temp = sources[i];
        sources[i] = sources[j];
        sources[j] = temp;
    }
}

package com.wuxp.tdd.datastructure;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeapExampleTests {

    private HeapExample<Integer> heap;

    @BeforeEach
    void setup() {
        heap = new HeapExample<>();
    }

    @Test
    void isEmpty() {
        Assertions.assertTrue(heap.isEmpty());
        heap.insert(1);
        Assertions.assertFalse(heap.isEmpty());
    }

    @Test
    void size() {
        Assertions.assertEquals(0, heap.size());
        heap.insert(1);
        Assertions.assertEquals(1, heap.size());
    }

    @Test
    void popEmpty() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> heap.pop());
    }

    @Test
    void bigHeapInsert() {
        int size = 1024;
        while (size-- > 0) {
            heap.insert(RandomUtil.randomInt(0, 1024));
        }
        heap.insert(1025);
        heap.insert(1028);
        heap.insert(1029);
        heap.insert(1026);
        heap.insert(1027);
        Assertions.assertEquals(1029, heap.pop());
        Assertions.assertEquals(1028, heap.pop());
        Assertions.assertEquals(1027, heap.pop());
        Assertions.assertEquals(1026, heap.pop());
        Assertions.assertEquals(1025, heap.pop());
    }

    @Test
    void littleHeapInsert() {
        HeapExample<Integer> littleHeap = new HeapExample<>(16, false);
        int size = 1024;
        while (size-- > 0) {
            littleHeap.insert(RandomUtil.randomInt(0, 1024));
        }
        littleHeap.insert(-1);
        littleHeap.insert(-3);
        littleHeap.insert(-5);
        littleHeap.insert(-4);
        littleHeap.insert(-2);
        Assertions.assertEquals(-5, littleHeap.pop());
        Assertions.assertEquals(-4, littleHeap.pop());
        Assertions.assertEquals(-3, littleHeap.pop());
        Assertions.assertEquals(-2, littleHeap.pop());
        Assertions.assertEquals(-1, littleHeap.pop());
    }
}
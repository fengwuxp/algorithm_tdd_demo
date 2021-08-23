package com.wuxp.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SlidingTimeWindowTests {

    private final SlidingTimeWindow timeWindow = new SlidingTimeWindow(100, 5);

    @Test
    void test() throws Exception {
        int maxTestTotal = 4;
        int eachCount = 900_000;
        CountDownLatch countDownLatch = new CountDownLatch(maxTestTotal);
        ExecutorService executorService = Executors.newFixedThreadPool(maxTestTotal);
        for (int i = 0; i < maxTestTotal; i++) {
            executorService.submit(() -> {
                mockCount(eachCount);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.print(timeWindow.getTotal());
        Assertions.assertTrue(timeWindow.getTotal() <= maxTestTotal * eachCount);
    }

    private void mockCount(int total) {
        for (int i = 0; i < total; i++) {
            timeWindow.increase();
        }
    }
}

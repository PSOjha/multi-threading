package com.company;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImpExecutorService {
    private void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        executorService.submit(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                System.out.println(i);
            }
        });

        var v = executorService.submit(() -> {
            System.out.println("working");
            Thread.sleep(3000);
            return 6;
        });
        executorService.shutdown();
        try {
            System.out.println(v.get().intValue());//block main thread until get end
            System.out.println("Done");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

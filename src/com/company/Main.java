package com.company;

import com.company.download_status.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        var time = LocalDateTime.now().toLocalTime();
//        ExecutorService executorService= Executors.newFixedThreadPool(4);
//        executorService.submit(() -> {
//            for (int i = 0; i < Integer.MAX_VALUE; i++) {
//                System.out.println(i);
//            }
//        });
//        executorService.submit(() -> {
//            for (int i = Integer.MAX_VALUE; i >Integer.MIN_VALUE ; i--) {
//                System.out.println(i);
//            }
//        });


        List<Downloader> downloaders = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        IDownloadStatus status = new DownloadStatusSynchronized();
        for (int i = 0; i < 100; i++) {
            downloaders.add(new Downloader(status));
            Thread thread = new Thread(downloaders.get(i));
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            try {
                thread.join();//pause main thread until other threads to end
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Thread thread = new Thread(() -> {
            while (!status.isDone()) {
                synchronized (status) {
                    try {
                        status.wait();//stop this thread until notify
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("done from lock " + status.getTotalBytes());
            System.out.println((LocalDateTime.now().toLocalTime().toNanoOfDay()-time.toNanoOfDay())/1000_000);
        });
        thread.start();
        var sumCounterInsideThreads = downloaders.stream().map(Downloader::getCount).reduce(0, Integer::sum);


        System.out.println("done from counter inside thread " + sumCounterInsideThreads);


    }
}

package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Downloader> downloaders = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        DownloadStatus status = new DownloadStatus();
        for (int i = 0; i < 10; i++) {
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

        });
        thread.start();
        var sumCounterInsideThreads = downloaders.stream().map(Downloader::getCount).reduce(0, Integer::sum);


        System.out.println("done from counter inside thread " + sumCounterInsideThreads);
    }
}

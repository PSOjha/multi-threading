package com.company.download;

import com.company.download.download_status.IDownloadStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Download {
    private final IDownloadStatus status;

    public Download(IDownloadStatus status) {
        this.status = status;
    }

    public void startDownload() {
        var threadsAndDownloaders = getThreadsAndDownloaders(status);
        List<Thread> threads = new ArrayList<>(threadsAndDownloaders.keySet());
        joinMainThreadUntilThreadsToEnd(threads);
        threadToBrodCastIfDownloadDone();
        var sumCounterInsideThreads = threadsAndDownloaders.values().stream().map(Downloader::getCount).reduce(0, Integer::sum);
        System.out.println("done from counter inside thread " + sumCounterInsideThreads);
    }

    private void threadToBrodCastIfDownloadDone() {
        new Thread(() -> {
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
        }).start();
    }

    private void joinMainThreadUntilThreadsToEnd(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();//pause main thread until other threads to end
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<Thread, Downloader> getThreadsAndDownloaders(IDownloadStatus status) {
        final Map<Thread, Downloader> threadsAndDownloaders = new HashMap<>();
        final List<Downloader> downloaders = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            downloaders.add(new Downloader(status));
            final Thread thread = new Thread(downloaders.get(i));
            thread.start();
            threadsAndDownloaders.put(thread, downloaders.get(i));
        }
        return threadsAndDownloaders;
    }
}

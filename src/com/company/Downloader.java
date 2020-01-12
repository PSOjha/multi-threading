package com.company;

public class Downloader implements Runnable {
    private int count;
    private final DownloadStatus status;

    Downloader(DownloadStatus status) {
        this.status = status;
    }

    @Override
    public void run() {
        System.out.println("Downloading  " + Thread.currentThread().getName());
        for (int i = 0; i < 9999999; i++) {
            status.increment();
            count++;//faster
        }
        System.out.println("Downloaded  " + Thread.currentThread().getName());
        status.done();
        synchronized (status) {
            status.notifyAll();
        }
    }

    public int getCount() {
        return count;
    }

    public DownloadStatus getStatus() {
        return status;
    }
}
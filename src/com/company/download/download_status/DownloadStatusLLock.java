package com.company.download.download_status;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatusLLock extends DownloadStatus {
    private Lock lock = new ReentrantLock();
    private int totalBytes = 0;
  //30 time
    @Override
    public void increment() {
        lock.lock();
        try {
            totalBytes++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getTotalBytes() {
        return totalBytes;
    }
}

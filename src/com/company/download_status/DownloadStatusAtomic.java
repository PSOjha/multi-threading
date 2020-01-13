package com.company.download_status;

import java.util.concurrent.atomic.AtomicInteger;

public class DownloadStatusAtomic extends DownloadStatus {
    private AtomicInteger totalBytes = new AtomicInteger(0);//non-blocking algorithms

    //20 time
    @Override
    public void increment() {
        totalBytes.incrementAndGet();
    }

    @Override
    public int getTotalBytes() {
        return totalBytes.get();
    }

}

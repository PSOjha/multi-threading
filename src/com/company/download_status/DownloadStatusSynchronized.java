package com.company.download_status;

public class DownloadStatusSynchronized extends DownloadStatus {
    private int totalBytes = 0;
    private final Object totalBytesLock = new Object();
    //40 time
    @Override
    public void increment() {
        synchronized (totalBytesLock/* u can replace [totalBytesLock] with [this] if one method work with multiThreading*/) {
            //synchronized is a way to make this method safe with multiThreading
            totalBytes++;
        }
    }

    @Override
    public int getTotalBytes() {
        return totalBytes;
    }
}

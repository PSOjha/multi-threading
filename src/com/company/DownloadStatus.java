package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatus {
    private Lock lock = new ReentrantLock();
    private int totalBytes;
    private final Object totalBytesLock = new Object();
    private volatile boolean isDone;/* volatile it is means every time read this obj from memory not from cash*/

    public void increment() {
        synchronized (totalBytesLock/* u can replace [totalBytesLock] with [this] if one method work with multiThreading*/) { //synchronized is a way to make this method safe with multiThreading
            lock.lock();
            try {
                totalBytes++;
            } finally {
                lock.unlock();
            }
        }
    }

    public int getTotalBytes() {
        return totalBytes;
    }

    public boolean isDone() {
        return isDone;
    }

    public void done() {
        isDone = true;
    }
}
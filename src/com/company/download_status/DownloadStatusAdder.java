package com.company.download_status;

import java.util.concurrent.atomic.LongAdder;

public class DownloadStatusAdder extends DownloadStatus {
    private LongAdder totalBytes = new LongAdder();
    //that like make a lot of array in memory and in the end sum all sum([]+[]+[])
    //5 time
    @Override
    public void increment() {
        totalBytes.increment();
    }

    @Override
    public int getTotalBytes() {
        return totalBytes.intValue();
    }
}

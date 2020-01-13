package com.company.download.download_status;

public interface IDownloadStatus {
    void increment();

    int getTotalBytes();

    boolean isDone();

    void done();
}

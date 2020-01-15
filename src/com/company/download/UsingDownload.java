package com.company.download;

import com.company.download.download_status.DownloadStatusSynchronized;
import com.company.download.download_status.IDownloadStatus;

public class UsingDownload {
    private void test() {
        IDownloadStatus status = new DownloadStatusSynchronized();
        Download download = new Download(status);
        download.startDownload();
    }
}

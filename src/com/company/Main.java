package com.company;

import com.company.download.Download;
import com.company.download.download_status.DownloadStatusSynchronized;
import com.company.download.download_status.IDownloadStatus;

public class Main {

    public static void main(String[] args) {
        IDownloadStatus status = new DownloadStatusSynchronized();
        Download download = new Download(status);
        download.startDownload();
//        ExecutorService executorService= Executors.newFixedThreadPool(4);
//        executorService.submit(() -> {
//            for (int i = 0; i < Integer.MAX_VALUE; i++) {
//                System.out.println(i);
//            }
//        });
//        executorService.submit(() -> {
//            for (int i = Integer.MAX_VALUE; i >Integer.MIN_VALUE ; i--) {
//                System.out.println(i);
//            }
//        });
    }
}

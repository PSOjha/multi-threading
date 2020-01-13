package com.company.download_status;

public abstract class DownloadStatus implements IDownloadStatus {
    private volatile boolean isDone;
    /* volatile it is means every time read this obj from shared cash not local cash of thread it solve visibility problem*/

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void done() {
        isDone = true;
    }
}
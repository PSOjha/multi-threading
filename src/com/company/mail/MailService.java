package com.company.mail;

import java.util.concurrent.CompletableFuture;

public class MailService {
    public void send() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Mail Send");
        }
    }

    public CompletableFuture<Void> sendAsync() {
        return CompletableFuture.runAsync(this::send);
    }

    public CompletableFuture<String> loginAsync(String userName) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.supplyAsync(() -> "Login To " + userName);
    }

    public CompletableFuture<Void> LogoutWithException() {
        throw new IllegalStateException();
    }

}

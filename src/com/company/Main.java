package com.company;

import com.company.library.LibraryService;
import com.company.utail.LongTask;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        var startTime = LocalDateTime.now();

        LibraryService libraryService = new LibraryService();
//        libraryService.getBookPrice("Clean Code").thenAccept(System.out::println);


        var booksFuture = libraryService.getBookSPrice()
                .map(bookCompletableFuture -> bookCompletableFuture
                        .thenAccept(System.out::println))
                .collect(Collectors.toList());

        CompletableFuture.allOf(booksFuture.toArray(new CompletableFuture[0])).thenRun(() -> {
            System.out.println("End In " + Duration.between(startTime, /*endTime*/ LocalDateTime.now()).toMillis() + "mSec");
        });

        LongTask.start(15_000);
    }
}

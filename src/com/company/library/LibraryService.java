package com.company.library;

import com.company.utail.LongTask;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class LibraryService {
    private final Random random = new Random();
    //    private List<String> listOfBooks = List.of("Clean Code", "Software Engineering", "Clean Agile", "DS and Algo");//returns immutable
    private List<String> listOfBooks = Arrays.asList("Clean Code", "Software Engineering", "Clean Agile", "DS and Algo");//returns mutable

    public CompletableFuture<Book> getBookPrice(String name) {
        System.out.println("Getting " + name + " Price...");
        return CompletableFuture.supplyAsync(() -> {
            LongTask.start(random.nextInt(3000) + 2000);
            var price = 100 + random.nextInt(100);
            return new Book(name, price + "$");
        });
    }

    public Stream<CompletableFuture<Book>> getBookSPrice() {
        Collections.shuffle(listOfBooks);

        return listOfBooks.stream()
                .map(this::getBookPrice);
//                .collect(Collectors.toList());
    }
}


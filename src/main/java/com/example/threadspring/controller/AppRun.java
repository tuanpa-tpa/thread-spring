package com.example.threadspring.controller;

import com.example.threadspring.model.User;
import com.example.threadspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component @Slf4j @RequiredArgsConstructor
public class AppRun implements CommandLineRunner {
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        CompletableFuture<User> f1 = userService.findUser(1);
        CompletableFuture<User> f2 = userService.findUser(2);
        CompletableFuture<User> f3 = userService.findUser(3);

//        đợi cho đến khi các luồng chạy xong
        CompletableFuture.allOf(f1, f2, f3).join();

//        in ra thời gian chạy
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("->>" + f1.get());
        log.info("->>" + f2.get());
        log.info("->>" + f3.get());
    }
}

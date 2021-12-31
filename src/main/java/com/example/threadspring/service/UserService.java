package com.example.threadspring.service;

import com.example.threadspring.model.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    public static List<User> list = new ArrayList<>();

    static {
        list.add(new User(1, "tuan1"));
        list.add(new User(2, "tuan2"));
        list.add(new User(3, "tuan3"));
    }

    @Async
    public CompletableFuture<User> findUser(int id) throws InterruptedException {

        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };
//        tìm kiếm user theo id -> trả về vị trí trong list
        int index = Collections.binarySearch(list, new User(id, null), comparator);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(list.get(index));
    }
}

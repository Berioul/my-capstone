package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    public Item creatItem(Item item) {

        return todoRepository.save(item);


    }
}

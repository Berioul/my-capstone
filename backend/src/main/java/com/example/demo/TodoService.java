package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public void deleteById(String id) {

        todoRepository.deleteById(id);
    }


    public Item creatItem(Item item) {

        return todoRepository.save(item);


    }

    public List<Item> findAll() {
        return todoRepository.findAll();
    }
}

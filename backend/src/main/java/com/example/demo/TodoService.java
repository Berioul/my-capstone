package com.example.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public void deleteById(String id) {
        todoRepository.deleteByIdAndPrivatFalse(id);

    }

    public Item creatItem(Item item) {
        return todoRepository.save(item);
    }

    public List<Item> findAll() {
        return todoRepository.findAll();
    }

    /*public void completItem(String id) {
        todoRepository.findById(id).ifPresent(item -> {
            item.setDone(true);
            todoRepository.save(item);
        });
    }

     */

    public void changeItem(String id, Item changeItem) {
        Item item = todoRepository.findById(id).orElseThrow();

        item.setSubject(changeItem.getSubject());
        item.setDescription(changeItem.getDescription());
        item.setCategory(changeItem.getCategory());
        item.setDone(changeItem.isDone());

        todoRepository.save(item);

    }
}


package com.example.demo;

import com.example.demo.categories.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public void completItem(String id) {
        todoRepository.findById(id).ifPresent(item -> {
            item.setDone(true);
            todoRepository.save(item);
        });
    }
}


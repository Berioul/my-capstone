package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public void createItem(@RequestBody ItemDTO item){
    todoService.creatItem(item.toItem());

    }
}

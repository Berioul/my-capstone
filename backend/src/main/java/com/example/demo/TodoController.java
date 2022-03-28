package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO item){
    return ResponseEntity
            .status(201)
            .body(ItemDTO.of(todoService.creatItem(item.toItem())));

    }
    @GetMapping
    public List<ItemDTO> getItems(){
        return todoService.findAll().stream()
                .map(ItemDTO::of)
                .toList();
    }
}

package com.example.demo;

import com.example.demo.categories.CategoryDTO;
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
    private CategoryDTO categoryDTO;

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO item) {
        return ResponseEntity
                .status(201)
                .body(ItemDTO.of(todoService.creatItem(item.toItem())));

    }

    @GetMapping
    public List<ItemDTO> getItems() {
        return todoService.findAll().stream()
                .map(ItemDTO::of)
                .toList();
    }

    @DeleteMapping("/{id}")
    public List<ItemDTO> deleteItem(@PathVariable String id) {
        todoService.deleteById(id);

        return todoService.findAll().stream()
                .map(ItemDTO::of)
                .toList();

    }

    @PutMapping("/{id}")
    public void itemDone(@PathVariable String id) {
        todoService.completItem(id);
    }

}

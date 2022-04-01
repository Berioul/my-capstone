package com.example.demo.categories;

import com.example.demo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
@RequiredArgsConstructor
public class CategoryController {

    private final TodoService todoService;
    private final CategoryService categoryService;


    @GetMapping
    public List<CategoryDTO> getCategories() {
        return categoryService.change(todoService.findAll());

    }
}

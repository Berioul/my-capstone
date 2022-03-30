package com.example.demo.categories;

import com.example.demo.Item;
import com.example.demo.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryService {

    public List<CategoryDTO> change(List<Item> items) {
        return items.stream()
                .map(item -> ItemDTO.of(item))
                .collect(Collectors.groupingBy(item -> item.getCategory()))
                .entrySet().stream()
                .map(entry -> new CategoryDTO(entry.getKey(), entry.getValue()))
                .toList();


    }
}

package com.example.demo.categories;

import com.example.demo.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String name;
    private List<ItemDTO> items;
}
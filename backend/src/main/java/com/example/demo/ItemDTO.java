package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private String subject;
    private String description;
    private String category;

    public static ItemDTO of(Item item) {

        return new ItemDTO(item.getSubject(), item.getDescription(), item.getCategory());
    }

    public Item toItem(){
        return new Item(null,subject,description,category);
    }
}

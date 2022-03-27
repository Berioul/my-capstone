package com.example.demo;

import lombok.Data;

@Data
public class ItemDTO {

    private String subject;
    private String description;
    private String category;

    public Item toItem(){
        return new Item(null,subject,description,category);
    }
}

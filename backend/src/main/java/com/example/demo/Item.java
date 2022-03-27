package com.example.demo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    private String id;
    private String subject;
    private String description;
    private String category;
}


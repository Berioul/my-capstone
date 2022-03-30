package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private String subject;
    private String description;
    private String category;
    private List<Link> links;


    public static ItemDTO of(Item item) {

        List<Link> links = List.of(
                Link.of("/api/todos/"+ item.getId(),"self")

        );

        return new ItemDTO(item.getSubject(), item.getDescription(), item.getCategory(),links);
    }

    public Item toItem(){
        return new Item(null,subject,description,category);
    }
}

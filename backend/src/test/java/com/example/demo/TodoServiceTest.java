package com.example.demo;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;


class TodoServiceTest {

    @Test
    void shouldCreateItem(){
        Item item = new Item(null,"oualid","after capstone","urlaub");
        Item savedItem = new Item("200","oualid","after capstone","urlaub");
        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        Mockito.when(todoRepository.save(item)).thenReturn(savedItem);

        TodoService todoService = new TodoService(todoRepository);
        Item actual = todoService.creatItem(item);

        Assertions.assertThat(actual).isEqualTo(savedItem);

    }
    @Test
    void shouldGetAllItems(){
        Item item = new Item("123","oualid","after capstone","urlaub");
        Item item1 = new Item("1234","passport","capstone","urlaub");
        Item item2 = new Item("12345","telephone","befor capstone","urlaub");

        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        Mockito.when(todoRepository.findAll()).thenReturn(List.of(item, item1, item2));

        TodoService todoService = new TodoService(todoRepository);
        List<Item> actual = todoService.findAll();


        Assertions.assertThat(actual).isEqualTo(List.of(item, item1, item2));
    }
}
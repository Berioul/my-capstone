package com.example.demo;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


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
}
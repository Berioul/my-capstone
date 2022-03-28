import React, {useEffect, useState} from 'react';
import TodoForm from "./TodoForm/TodoForm";
import TodoList from "./TodoList/TodoList";
import {Item} from "./TodoList/model";

function App() {

    const [items, setItems] = useState([] as Array<Item>)
    const fetchAll = () => {
        fetch('/api/todos')
            .then(response => response.json())
            .then((responseBody: Array<Item>) => setItems(responseBody));

    }

    useEffect(() => {
        fetchAll()
    }, [])


    const itemCreated = () => {
        fetchAll()
    }
    return (
        <div className="App">
            <TodoForm onItemCreate={itemCreated}/>
            <TodoList items={items}/>
        </div>
    );
}

export default App;

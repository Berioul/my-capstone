import React, {useEffect, useState} from 'react';
import TodoForm from "./TodoForm/TodoForm";
import TodoList from "./TodoList/TodoList";
import {Category} from "./TodoList/model";


function App() {

    const [categories, setCategories] = useState([] as Array<Category>)
    const fetchAll = () => {
        fetch('/api/categories')
            .then(response => response.json())
            .then((responseBody: Array<Category>) => setCategories(responseBody));

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
            {categories.length > 0 && <TodoList categories={categories} onItemListChange={fetchAll}/>}
        </div>
    );
}

export default App;

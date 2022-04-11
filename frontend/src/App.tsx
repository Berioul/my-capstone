import React, {useCallback, useEffect, useState} from 'react';
import TodoForm from "./TodoForm/TodoForm";
import TodoList from "./TodoList/TodoList";
import {Category} from "./TodoList/model";
import {useAuth} from "./auth/AuthProvider";
import Map from "./Map/Map";
import './App.css'



function App() {

    const [categories, setCategories] = useState([] as Array<Category>)

    const {token, logout} = useAuth()

    const fetchAll = useCallback (() => {
        fetch('/api/categories', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => response.json())
            .then((responseBody: Array<Category>) => setCategories(responseBody));

    },[token])

    useEffect(() => {
        fetchAll()
    }, [fetchAll])


    const itemCreated = () => {
        fetchAll()
    }
    return (
<div>
        <div className="App">

            <TodoForm onItemCreate={itemCreated}/>
            {categories.length > 0 && <TodoList categories={categories} onItemListChange={fetchAll}/>}
        </div>
    <div><Map/></div>
    <nav><button className="Logout" onClick={() => logout()}>Logout</button></nav>
</div>
    );
}

export default App;

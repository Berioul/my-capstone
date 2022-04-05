import React, {useEffect, useState} from 'react';
import TodoForm from "./TodoForm/TodoForm";
import TodoList from "./TodoList/TodoList";
import {Category} from "./TodoList/model";
import {useAuth} from "./auth/AuthProvider";



function App() {

    const [categories, setCategories] = useState([] as Array<Category>)

    const {token, logout} = useAuth()

    const fetchAll = () => {
        fetch('/api/categories', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
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
            <nav>
                <ul>
                    <li><span className="clickable" onClick={() => logout()}>Logout</span></li>
                </ul>
            </nav>
            <TodoForm onItemCreate={itemCreated}/>
            {categories.length > 0 && <TodoList categories={categories} onItemListChange={fetchAll}/>}
        </div>
    );
}

export default App;

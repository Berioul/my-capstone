import {Category} from "./model";
import './TodoList.css'
import TodoCategory from "./TodoCategory";
import React from "react";

interface TodoListProps{
    categories: Array<Category>
    onItemListChange: () => void;
}
export default function TodoList (props:TodoListProps){



    return(
        <div className='Categ'>
            {props.categories.map((category,index) => <div key={`${category.name}-${index}`}>
                <TodoCategory category={category}  onItemListChange={props.onItemListChange} />
            </div>) }

        </div>
    )
}
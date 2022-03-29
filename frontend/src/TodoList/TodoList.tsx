import {Item} from "./model";
import TodoItem from "./TodoItem";
import {useState} from "react";
interface TodoListProps{
    items: Array<Item>
}
export default function TodoList (props:TodoListProps){

    const [actualItems, setActualItems] = useState(props.items)

    return(
        <div>
            {actualItems.map((item,index) => <div key={`${item.subject}-${index}`}><TodoItem item ={item} onItemDeleted={setActualItems} /></div>)}
        </div>
    )
}
import {Item} from "./model";
import TodoItem from "./TodoItem";
interface TodoListProps{
    items: Array<Item>
}
export default function TodoList (props:TodoListProps){

    return(
        <div>
            {props.items.map((item,index) => <div key={`${item.subject}-${index}`}><TodoItem item ={item} /></div>)}
        </div>
    )
}
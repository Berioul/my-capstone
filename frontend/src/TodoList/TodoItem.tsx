import {Item} from "./model";
import './TodoItem.css'
interface TodoItemProps{
    item: Item;
    onItemDeleted: (items: Array<Item>) => void;

}
export default function TodoItem(props: TodoItemProps){

    const deleteItem = () => {
        fetch(`${props.item.links.find(l => l.rel ==='self')?.href}`,{
            method: 'DELETE'
        })
            .then(response => response.json())
            .then((items: Array<Item>) => props.onItemDeleted(items) )
    };

    const toggle = () =>{

        fetch(`${props.item.links.find(l => l.rel ==='self')?.href}`,{
            method: 'PUT'
        })

    };

    return(
        <div>
            <input type='radio' className={props.item.done ? 'selected':''} onClick={toggle}/>
            {props.item.subject} | {props.item.description}
            {!props.item.privat &&  <button className='deleteButton' onClick={deleteItem}>Delete</button>}

        </div>
    )

}
import {Item} from "./model";

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

    return(
        <div>
            {props.item.subject}
            <button onClick={deleteItem}>Delete</button>
        </div>
    )

}
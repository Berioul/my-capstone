import {Item} from "./model";
import './TodoItem.css'
import {useState} from "react";
import {useAuth} from "../auth/AuthProvider";

interface TodoItemProps {
    item: Item;
    onItemListChange: (items: Array<Item>) => void;


}


export default function TodoItem(props: TodoItemProps) {
    const [subjectToEdit, setSubjectToEdit] = useState(props.item.subject);
    const [descriptionToEdit, setDescriptionToEdit] = useState(props.item.description);
    const [editMode, setEditMode] = useState(false);
    const { token } = useAuth()
    const deleteItem = () => {
        fetch(`${props.item.links.find(l => l.rel === 'self')?.href}`, {
            method: 'DELETE',
            headers: {

                'Authorization':  `Bearer ${token}`,
            },

        })
            .then(response => response.json())
            .then((items: Array<Item>) => props.onItemListChange(items))
    };

    const editItem = (item: { subject: string; description: string; category: string }) => {
        fetch(`${props.item.links.find(l => l.rel === 'self')?.href}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization':  `Bearer ${token}`,
            },
            body: JSON.stringify(item)
        })
            .then(response => response.json())
            .then((items: Array<Item>) => {
                props.onItemListChange(items)
                setEditMode(false);
            });

    }
    const ediTodo = () =>{
        editItem({
            subject:subjectToEdit,
            description:descriptionToEdit,
            category: props.item.category

        });

    }

    const toggle = () => {

        const toggleItem = props.item;
        toggleItem.done = !toggleItem.done;

        fetch(`${props.item.links.find(l => l.rel === 'self')?.href}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization':  `Bearer ${token}`

            },
            body: JSON.stringify(toggleItem)
        })
            .then(response => response.json())
            .then((items: Array<Item>) => props.onItemListChange(items))

    };

    return (

        <div>
            {
                editMode
                    ?

                    <div>

                        <input type="text" value={subjectToEdit}
                               onChange={ev => setSubjectToEdit(ev.target.value)}/>

                        <input type="text" value={descriptionToEdit}
                               onChange={ev => setDescriptionToEdit(ev.target.value)}
                               onKeyUp={ev => {if(ev.keyCode === 13){ediTodo();}}}/>

                        <button onClick={ediTodo}>Change</button>

                    </div>
                    :

                    <div className={props.item.done ? 'selected' : ''} >
                        <input type='checkbox' onClick={toggle}/>
                        {props.item.subject} {props.item.description}
                        {!props.item.privat && <button className='deleteButton' onClick={deleteItem}>Delete</button>}
                        {!props.item.privat && <button onClick={() => setEditMode(true)}>Edit</button>}
                    </div>
            }
        </div>
    )

}
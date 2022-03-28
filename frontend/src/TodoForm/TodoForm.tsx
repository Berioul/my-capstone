import {useState} from "react";

export default function TodoForm() {
    const[subject,setSubject]= useState('');
    const[description,setDescription]= useState('');
    const[category,setCategory]= useState('');

    const saveTodoSubject = () => {
        fetch('/api/todos',{
            method:'post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                subject: subject,
                description: description,
                category: category

            })
        })
    }

    return (

        <div>
            <input type='text' placeholder='Title' value={subject} onChange={ev => setSubject(ev.target.value)} />
            <input type='text' placeholder='Description' value={description} onChange={ev => setDescription(ev.target.value)}/>
            <input type='text' placeholder='Category' value={category} onChange={ev => setCategory(ev.target.value)} />
            <button onClick={() => saveTodoSubject()}>Add</button>
        </div>
    )
}
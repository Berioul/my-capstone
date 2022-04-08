import {useState} from "react";
import './TodoForm.css'
import {useAuth} from "../auth/AuthProvider";
interface TodoFormProps{
    onItemCreate: () => void;
}
export default function TodoForm(props: TodoFormProps) {
    const[subject,setSubject]= useState('');
    const[description,setDescription]= useState('');
    const[category,setCategory]= useState('Avant le depart');

    const {token} = useAuth()
    const saveTodoSubject = () => {
        fetch('/api/todos',{
            method:'post',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({
                subject: subject,
                description: description,
                category: category

            })
        })
            .then(() => props.onItemCreate())
    }

    const logo1 = require('./Panama+San+Blas_9.jpg');
    return (

        <div>
            <h1 className='titre'>Check-List de voyage</h1>
            <div>
                <img className='photo1' src={logo1} alt=" Catamaran dans les îles San Blas "/>
            </div>
            <div className='formPlaces'>
            <input type='text' placeholder='Title' value={subject} onChange={ev => setSubject(ev.target.value)} />
            <input type='text' placeholder='Description' value={description} onChange={ev => setDescription(ev.target.value)}/>
            </div>
                <div>
                <select className="select" value={category} onChange={ev => setCategory(ev.target.value)}>

                    <option value="Avant le depart">Avant le depart</option>
                    <option value="Navigation">Navigation</option>
                    <option value="Multicoques">Multicoques</option>
                    <option value="pharmacie">pharmacie</option>
                    <option value="Document">Document</option>
                </select>
                <button  className='buttonAdd' onClick={() => saveTodoSubject()}>Add</button>
            </div>
        </div>

    )
}
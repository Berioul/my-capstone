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
    const logo = require('./photo.JPG');
    const logo1 = require('./Panama+San+Blas_9.jpg');
    return (

        <div>
            <h1 className='titre'>Voyage en Voilier</h1>
            <div>
                <img className='photo1' src={logo1} alt=" Catamaran dans les îles San Blas "/>
                <img className={'photo'} src={logo} alt='plage en tunisie'/>
            </div>
            <div className='formPlaces'>
            <input type='text' placeholder='Titre' value={subject} onChange={ev => setSubject(ev.target.value)} />
            <input className='formPlaces1' type='text' placeholder='Description' value={description} onChange={ev => setDescription(ev.target.value)}/>
            </div>
                <div>
                <select className="select" value={category} onChange={ev => setCategory(ev.target.value)}>
                    <option value="Avant le depart">Avant le depart</option>
                    <option value="Todo">Todo</option>
                    <option value="Navigation">Navigation</option>
                    <option value="Voilier">Voilier</option>
                    <option value="Pharmacie">Pharmacie</option>
                    <option value="Document">Document</option>
                </select>
                <button className='add' onClick={() => saveTodoSubject()}>Ajouter</button>
            </div>
        </div>

    )
}
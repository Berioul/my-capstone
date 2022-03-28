import {Item} from "./model";

interface TodoItemProps{
    item: Item
}
export default function TodoItem(props: TodoItemProps){
    return(
        <div>{props.item.subject}</div>
    )

}
import {Category} from "./model";
import TodoItem from "./TodoItem";

interface TodoCategoryProps{
    category: Category;
    onItemListChange: () => void;
}
export default function TodoCategory(props: TodoCategoryProps){

    return(
        <div>
        <h3>{props.category.name}</h3>
            {props.category.items.map((item,index) => <div key={`${item.subject}-${index}`}>
                <TodoItem item ={item} onItemListChange={props.onItemListChange} />
            </div>) }
        </div>
    )
}
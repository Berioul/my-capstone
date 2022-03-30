import {Category} from "./model";
import TodoCategory from "./TodoCategory";
interface TodoListProps{
    categories: Array<Category>
    onItemDeleted: () => void;
}
export default function TodoList (props:TodoListProps){



    return(
        <div>
            {props.categories.map((category,index) => <div key={`${category.name}-${index}`}>
                <TodoCategory category={category}  onItemDeleted={props.onItemDeleted} />
            </div>) }
        </div>
    )
}
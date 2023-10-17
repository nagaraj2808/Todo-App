package mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ToDoList")
@Getter
@AllArgsConstructor
public class ToDo {
    String id;
    String userId;
    String whatToDo;
}

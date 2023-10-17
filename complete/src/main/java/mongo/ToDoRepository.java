package mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
    @Query("{userId:'?0'}")
    List<ToDo> toDoList(String username);
}

package mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<UserItem, String> {

    @Query("{username:'?0'}")
    UserItem findUserByUsername(String username);

    @Query(value="{name:'?0'}")
    List<UserItem> findUserByFName(String name);



}
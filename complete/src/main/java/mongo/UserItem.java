package mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserItem {
    private String name;
    private String password;
    private String username;
    private long id;
}

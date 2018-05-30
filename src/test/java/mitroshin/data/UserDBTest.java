package mitroshin.data;

import mitroshin.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDBTest {
    @Test
    public void insert() throws Exception {
        User user = new User("Ivan", "Ivanov", "mymail@imaginarymail.commm");
        UserDB.insert(user);
        assertTrue(UserDB.emailExists(user.getEmail()));
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void selectUser() throws Exception {
    }

    @Test
    public void emailExists() throws Exception {
    }

}
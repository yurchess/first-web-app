package mitroshin.data;

import mitroshin.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDBTest {
    private User user = new User("Ivan", "Ivanov", "mymail@imaginarymail.commm");

    @Test
    public void insert() throws Exception {
        UserDB.insert(user);
        assertTrue(UserDB.emailExists(user.getEmail()));
        UserDB.delete(user);
        assertFalse(UserDB.emailExists(user.getEmail()));
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
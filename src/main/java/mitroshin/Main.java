package mitroshin;

import mitroshin.data.UserDB;
import mitroshin.entities.User;

public class Main {
    public static void main(String[] args) {
        User user = new User("g", "m", "g@m.ru");
        UserDB.insert(user);
    }
}

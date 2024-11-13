package bankingapp;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private List<User> users;

    public DataStore() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
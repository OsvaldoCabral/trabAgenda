package io.osvaldocabral.agenda;

import java.util.ArrayList;

public class UserSingleton {

    private static UserSingleton instance = new UserSingleton();

    public ArrayList<User> userList = new ArrayList();
    public User currentUser;
    public int userIndex;

    private UserSingleton() {}

    public static UserSingleton getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        currentUser = userList.get(userIndex);
        return currentUser;
    }

}

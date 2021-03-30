package io.osvaldocabral.agenda;

import java.util.ArrayList;

public class UserSingleton {

    private static UserSingleton instance = new UserSingleton();

    public ArrayList<User> userDetails = new ArrayList();
    public int userIndex;

    private UserSingleton() {
        userIndex = 0;
        userDetails.add(new User("admin", "admin@123"));
    }

    public User getCurrentUser() {
        return userDetails.get(userIndex);
    }

    public String getIndexString() {
        return "" + (userIndex+1) + "/" + userDetails.size();
    }

    public static UserSingleton getInstance() {
        return instance;
    }
}

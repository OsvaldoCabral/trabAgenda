package io.osvaldocabral.agenda;

import java.util.ArrayList;

public class UserSingleton {

    private static UserSingleton instance = new UserSingleton();

    public ArrayList<User> userList = new ArrayList();
    public User currentUser;
    public int userIndex;

    private UserSingleton() {}

    public UserSingleton(ArrayList<User> userList, User currentUser, int userIndex) {
        this.userList = userList;
        this.currentUser = currentUser;
        this.userIndex = userIndex;
    }

    public static UserSingleton getInstance() {
        return instance;
    }

    public void addUserList(User user) {
        userList.add(user);
    }

    public User getCurrentUser() {
        currentUser = userList.get(userIndex);
        return currentUser;
    }

}

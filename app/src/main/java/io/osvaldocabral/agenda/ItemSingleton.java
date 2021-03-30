package io.osvaldocabral.agenda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ItemSingleton {

    private static ItemSingleton instace = new ItemSingleton();

    public ArrayList<Item> listItems = new ArrayList<>();

    public int itemIndex;

    public static ItemSingleton getInstance() {
        return instace;
    }

    public void createItem(Item item) {
        listItems.add(item);
    }

    public void updateItem(Item item) {
        listItems.set(itemIndex, item);
    }

    public ArrayList<Item> getListItem() {

        ArrayList<Item> list = new ArrayList<>();

        int userIndex = UserSingleton.getInstance().userIndex;
        for(Item item : listItems) {
            if(item.getIdUser() == userIndex) {
                list.add(item);
            }
        }

        return list;
    }

}

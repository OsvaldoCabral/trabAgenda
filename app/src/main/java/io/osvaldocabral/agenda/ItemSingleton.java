package io.osvaldocabral.agenda;

import java.util.ArrayList;

public class ItemSingleton {

    private static ItemSingleton instace = new ItemSingleton();

    public ArrayList<Item> listItems = new ArrayList<>();

    public static ItemSingleton getInstance() {
        return instace;
    }

    public void createItem(Item item) {
        listItems.add(item);
    }

    public ArrayList<Item> getListItem() {
        return listItems;
    }

}

package io.osvaldocabral.agenda;

import java.io.OutputStream;
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

    public ArrayList<String> getFilteredListItem() {

        ArrayList<String> list = new ArrayList<>();

        int userIndex = UserSingleton.getInstance().userIndex;
        for(Item item : listItems) {
            if(item.getIdUser() == userIndex) {
                list.add(item.getName());
            }
        }

        return list;
    }

    public int getPositionUpdate(int positionListView) {

        int contByUser = -1;
        int contByFile = -1;
        int positionUpdate = -1;
        int userIndex = UserSingleton.getInstance().userIndex;

        for(Item item : listItems) {
            contByFile++;
            if(item.getIdUser() == userIndex) {
                contByUser++;
                if(contByUser == positionListView) {
                    positionUpdate = contByFile;
                    break;
                }
            }
        }

        return positionUpdate;

    }
}

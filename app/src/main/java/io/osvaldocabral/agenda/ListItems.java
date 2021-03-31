package io.osvaldocabral.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ListItems extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        listView = findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemSingleton.getInstance().itemIndex = ItemSingleton.getInstance().getPositionUpdate(position);
                Intent intent = new Intent(ListItems.this, InfoItem.class);
                startActivity(intent);
            }
        });

        updateList();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        updateList();
    }


    public void buttonAddClicked(View view) {
        ItemSingleton.getInstance().itemIndex = -1;
        Intent intent = new Intent(ListItems.this, InfoItem.class);
        startActivity(intent);
    }


    public void updateList() {
        loadItemsFromFile();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                ListItems.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                ItemSingleton.getInstance().getFilteredListItem()
        );

        listView.setAdapter(adapter);
    }


    public void loadItemsFromFile() {
        try {
            InputStream stream = openFileInput("listItems.txt");
            InputStreamReader streamReader = new InputStreamReader(stream);

            BufferedReader reader = new BufferedReader(streamReader);
            String line;

            ArrayList<Item> item = new ArrayList<>();
            String[] aux;

            while((line = reader.readLine()) != null) {
                aux = line.split(";");
                item.add(new Item(Integer.parseInt(aux[0]), aux[1], aux[2], aux[3], aux[4]));
            }

            ItemSingleton.getInstance().listItems = item;

            reader.close();
            streamReader.close();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
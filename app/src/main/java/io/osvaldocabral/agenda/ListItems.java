package io.osvaldocabral.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
                ItemSingleton.getInstance().itemIndex = position;
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
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(
                ListItems.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                ItemSingleton.getInstance().getListItem()
        );

        listView.setAdapter(adapter);
    }

}
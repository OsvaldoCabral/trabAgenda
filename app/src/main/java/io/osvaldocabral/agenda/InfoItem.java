package io.osvaldocabral.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class InfoItem extends AppCompatActivity {

    EditText editTextName;
    EditText editTextAddress;
    EditText editTextPhone;
    Spinner spinnerInfo;

    Button btnSalveInfo;

    boolean isUpdate;
    int positionToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item);

        btnSalveInfo = findViewById(R.id.btnSaveInfo);

        editTextName = findViewById(R.id.editTextNameInfo);
        editTextAddress = findViewById(R.id.editTextAddressInfo);
        editTextPhone = findViewById(R.id.editTextPhoneInfo);
        spinnerInfo = findViewById(R.id.spinnerInfo);

        if (ItemSingleton.getInstance().itemIndex != -1) {
            isUpdate = true;
            positionToUpdate = ItemSingleton.getInstance().itemIndex;
            Item item = ItemSingleton.getInstance().listItems.get(positionToUpdate);
            fillFieldsUpdate(item);
        }
    }


    public void btnSalveInfoClick(View view) {
        Item newItem = newItem();
        if (isUpdate) ItemSingleton.getInstance().updateItem(newItem);
        else ItemSingleton.getInstance().createItem(newItem);

        saveItemToFile();

        Toast.makeText(InfoItem.this, R.string.alert_success_item_created_updated, Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }


    public void saveItemToFile() {
        try {
            OutputStream stream = InfoItem.this.openFileOutput("listItems.txt", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(stream);

            for (int i = 0; i < ItemSingleton.getInstance().listItems.size(); i++) {
                writer.write(ItemSingleton.getInstance().listItems.get(i).toString());
                writer.write("\n");
            }

            writer.flush();
            writer.close();

            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Item newItem() {
        Item item = new Item();
        item.setIdUser(UserSingleton.getInstance().userIndex);
        item.setName(editTextName.getText().toString());
        item.setAddress(editTextAddress.getText().toString());
        item.setPhone(editTextPhone.getText().toString());
        item.setType(spinnerInfo.getSelectedItem().toString());
        return item;
    }


    public void fillFieldsUpdate(Item item) {
        editTextName.setText(item.getName());
        editTextAddress.setText(item.getAddress());
        editTextPhone.setText(item.getPhone());

        String spinnerValue = item.getType();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInfo.setAdapter(adapter);

        int spinnerPosition = adapter.getPosition(spinnerValue);
        spinnerInfo.setSelection(spinnerPosition);
    }

}
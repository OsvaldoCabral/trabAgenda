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
            fillFieldsUpdate(item, positionToUpdate);
        }
    }


    public void fillFieldsUpdate(Item item, int positionToUpdate) {
        editTextName.setText(item.getName());
        editTextAddress.setText(item.getAddress());
        editTextPhone.setText(item.getPhone());
        spinnerInfo.setSelection(positionToUpdate);
    }


    public void btnSalveInfoClick(View view) {
        Item newItem = newItem();
        if(isUpdate) ItemSingleton.getInstance().updateItem(newItem);
        else ItemSingleton.getInstance().createItem(newItem);

        Toast.makeText(InfoItem.this, R.string.alert_success_item_created_updated, Toast.LENGTH_SHORT).show();
        super.onBackPressed();
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

}
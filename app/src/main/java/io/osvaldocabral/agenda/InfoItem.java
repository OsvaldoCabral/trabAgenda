package io.osvaldocabral.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item);

        btnSalveInfo = findViewById(R.id.btnSaveInfo);

        editTextName = findViewById(R.id.editTextNameInfo);
        editTextAddress = findViewById(R.id.editTextAddressInfo);
        editTextPhone = findViewById(R.id.editTextPhoneInfo);
        spinnerInfo = findViewById(R.id.spinnerInfo);
    }


    public void btnSalveInfoClick(View view) {
        Item newItem = newItem();
        ItemSingleton.getInstance().createItem(newItem);
        Toast.makeText(this, R.string.alert_success_item_created, Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    public Item newItem() {
        Item item = new Item();
        item.setName(editTextName.getText().toString());
        item.setAddress(editTextAddress.getText().toString());
        item.setPhone(editTextPhone.getText().toString());
        item.setType(spinnerInfo.getSelectedItem().toString());
        return item;
    }

}
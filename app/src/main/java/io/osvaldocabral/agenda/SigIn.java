package io.osvaldocabral.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SigIn extends AppCompatActivity {

    EditText editTextNameSigIn;
    EditText editTextPasswordSigIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_in);

        editTextNameSigIn = findViewById(R.id.editTextNameSigIn);
        editTextPasswordSigIn = findViewById(R.id.editTextPasswordSigIn);
    }

    public void saveUserToFile(View view) {
        try {
            OutputStream stream = SigIn.this.openFileOutput("listUser.txt", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(stream);

            String name = editTextNameSigIn.getText().toString();
            String password = editTextPasswordSigIn.getText().toString();

            for(int i = UserSingleton.getInstance().userList.size(); i>0; i--) {
                writer.write(UserSingleton.getInstance().userList.get(i-1).toString());
                writer.write("\n");
            }

            writer.write(name + ";" + password);
            writer.write("\n");

            UserSingleton.getInstance().addUserList(new User(name, password));

            writer.flush();
            writer.close();
            writer.close();

            Toast.makeText(SigIn.this, R.string.alert_success_user_created, Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
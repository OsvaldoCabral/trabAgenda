package io.osvaldocabral.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPassword;

    Button buttonLogIn;
    Button buttonSigIn;

    String name;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonSigIn = findViewById(R.id.buttonSigIn);
    }


    public void buttonLogInClicked(View view) {
        if (isValidAccess()) {
            redirectToList(view);
        } else {
            showAlertNotAuthSuccess();
        }
    }


    public void buttonSigInClicked(View view) {
        Intent intent = new Intent(MainActivity.this, SigIn.class);
        startActivity(intent);
    }


    public void redirectToList(View view) {
        Intent intent = new Intent(MainActivity.this, ListItems.class);
        startActivity(intent);
    }


    public boolean isValidAccess() {

        boolean isValidAccess = false;
        name = editTextName.getText().toString();
        password = editTextPassword.getText().toString();

        try {

            InputStream stream = openFileInput("listUser.txt");
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            String[] aux;

            int cont = -1;
            while ((line = reader.readLine()) != null) {
                cont++;
                aux = line.split(";");
                if (aux[0].equals(name) && aux[1].equals(password)) {
                    isValidAccess = true;
                    UserSingleton.getInstance().userIndex = cont;
                }
            }

            reader.close();
            streamReader.close();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return isValidAccess;
        }

    }


    public void showAlertNotAuthSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(android.R.string.dialog_alert_title);
        builder.setMessage(R.string.alert_error_auth);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTextName.setText("");
                        editTextPassword.setText("");
                    }
                }
        );

        builder.create().show();
    }
}
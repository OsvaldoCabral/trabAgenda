package io.osvaldocabral.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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

            writer.write("admin;admin@123");

            writer.flush();
            writer.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
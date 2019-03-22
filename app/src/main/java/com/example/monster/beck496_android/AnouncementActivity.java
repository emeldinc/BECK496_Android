package com.example.monster.beck496_android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnouncementActivity extends AppCompatActivity {
    String baslik, aciklama;
    EditText baslikInput;
    EditText aciklamaInput;
    Button kaydetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anouncement);

        baslikInput = (EditText) findViewById(R.id.baslikInput);
        aciklamaInput = (EditText) findViewById(R.id.aciklamaInput);
        kaydetButton = (Button) findViewById(R.id.kaydetButton);
        kaydetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                baslik = baslikInput.getText().toString();
                aciklama = aciklamaInput.getText().toString();
                String text = baslik + "\n"+ aciklama;
                showToast(text);

            }

        });
    }
    private void showToast(String message){
        Toast.makeText(AnouncementActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

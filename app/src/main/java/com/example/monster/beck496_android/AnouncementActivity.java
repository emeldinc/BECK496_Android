package com.example.monster.beck496_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AnouncementActivity extends AppCompatActivity {
    String baslik, aciklama;
    EditText baslikInput;
    EditText aciklamaInput;
    Button kaydetButton;
    Bundle bundle;
    private Button geriButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anouncement);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("user");
        User user = new User();
        String username = user.setUsername(bundle.getString("username"));
        String firstname = user.setFirstname(bundle.getString("firstname"));
        String lastname = user.setLastname(bundle.getString("lastname"));
        String role = user.setRole(bundle.getString("role"));
        ArrayList<String> house_ids = user.setHouse_ids(bundle.getStringArrayList("house_ids"));
        ArrayList<String> house_nos = user.setHouse_nos(bundle.getStringArrayList("house_nos"));

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        baslikInput = (EditText) findViewById(R.id.baslikInput);
        aciklamaInput = (EditText) findViewById(R.id.aciklamaInput);
        kaydetButton = (Button) findViewById(R.id.kaydetButton);
        geriButton = (Button) findViewById(R.id.button_geri);
        geriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnouncementActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        kaydetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                baslik = baslikInput.getText().toString();
                aciklama = aciklamaInput.getText().toString();
                String text = baslik + "\n"+ aciklama;

            }

        });
    }
//    private void post(String title, String body){
//        String url = Config.DUYURU_URL + "?username=" + username + "&password=" + password;
//        Log.d("url: ", url);
//        new LoginActivity.GetJSONTask().execute(url);
//        Log.d(TAG, "response : " + response);
//    }
}

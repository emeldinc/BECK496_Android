package com.example.monster.beck496_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowHouseInfo extends AppCompatActivity {

    Bundle bundle;
    Button geriButton;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_house_info);
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("duyuru");
        String fullname = bundle.getString("fullname");
        String title = bundle.getString("title");
        String description = bundle.getString("description");
        String date= bundle.getString("date");

        textView1 = (TextView) findViewById(R.id.fullname);
        textView2 = (TextView) findViewById(R.id.title);
        textView3 = (TextView) findViewById(R.id.description);
        textView4 = (TextView) findViewById(R.id.date);

        textView1.setText(fullname);
        textView1.setText(title);
        textView1.setText(description);
        textView1.setText(date);

        geriButton = (Button) findViewById(R.id.button);
        geriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowHouseInfo.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

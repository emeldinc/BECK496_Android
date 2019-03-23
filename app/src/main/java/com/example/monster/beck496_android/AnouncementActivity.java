package com.example.monster.beck496_android;

import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnouncementActivity extends AppCompatActivity {
    private static final String TAG = "Anouncement Activity";
    String baslik, aciklama;
    EditText baslikInput;
    EditText aciklamaInput;
    Button kaydetButton;
    Bundle bundle;
    private Button geriButton;
    String response;

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
        String user_id = bundle.getString("user_id");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        String house_id =intent.getStringExtra("houseId");
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

        //post(house_id);



        geriButton = (Button) findViewById(R.id.button_geri);
        geriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnouncementActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
//    private void post(String house_id){
//        //String url = Config.DUYURU_URL + "?house_id=" + house_id + "&user_id=" + user_id;
//        Log.d("url: ", url);
//        new AnouncementActivity.GetJSONTask().execute(url);
//        Log.d(TAG, "response : " + response);
//    }
    private void get(String house_id){
        String url = Config.DUYURU_URL + "?duyuru=" + house_id;
        Log.d("url: ", url);
        new AnouncementActivity.GetJSONTask().execute(url);
        Log.d(TAG, "response : " + response);
    }
    private void setResponse(String response) {
        this.response = response;
    }
    private class GetJSONTask extends AsyncTask<String, Void, String> {

        // onPreExecute called before the doInBackgroud start for display
        // progress dialog.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            Log.d(TAG, "jsontask url = " + urls[0]);

            try {
                setResponse(HttpHandler.makeServiceCall(urls[0]));
                return HttpHandler.makeServiceCall(urls[0]);
            } catch (Exception e) {
                return "Unable to retrieve data. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the doInBackgroud and also we
        // can hide progress dialog.
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "result= " + result);
            try {
                JSONObject json = new JSONObject(result);
                if(!json.getBoolean("error")) {
                    Bundle bundle = new Bundle();

                    Duyuru duyuru = new Duyuru();
                    String fullname = duyuru.setFullname(json.getString("fullname"));
                    String title = duyuru.setTitle(json.getString("title"));
                    String description = duyuru.setDescription(json.getString("description"));
                    String date = duyuru.setDate(json.getString("date"));


                    bundle.putString("fullname", fullname);
                    bundle.putString("title", title);
                    bundle.putString("description", description);
                    bundle.putString("date", date);
                    Intent intent = new Intent(AnouncementActivity.this, ShowHouseInfo.class);
                    intent.putExtra("duyuru", bundle);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AnouncementActivity.this, "Daire bulunamadı.", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void showToast(String message){
        Toast.makeText(AnouncementActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

package com.example.monster.beck496_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Defining views
    private EditText editTextUsername;
    private EditText editTextPassword;
    private AppCompatButton buttonLogin;
    private final String TAG = "Login Activity";
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing views
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (AppCompatButton) findViewById(R.id.buttonLogin);

        //Adding click listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });
    }



    private void login(){
        //Getting values from edit texts
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        String url = Config.LOGIN_URL + "?username=" + username + "&password=" + password;
        Log.d("url: ", url);
        new GetJSONTask().execute(url);
        Log.d(TAG, "response : " + response);
    }

    private void setResponse(String response) {
        this.response = response;
    }

    @Override
    public void onClick(View v) {
        //Calling the login function
        login();
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
                Bundle bundle = new Bundle();

                User user = new User();
                String username = user.setUsername(json.getString("username"));
                String firstname = user.setFirstname(json.getString("firstname"));
                String lastname = user.setLastname(json.getString("lastname"));
                String role = user.setRole(json.getString("role"));
                JSONArray arr = json.getJSONArray("house_id");
                String[] array = new String[arr.length()];
                for (int i = 0; i < array.length; i++) {
                    array[i] = (String)arr.get(i).toString();
                }
                user.setHouses(array);
                ArrayList<String> house_nos = user.getHouse_nos();
                ArrayList<String> house_ids = user.getHouse_ids();

                bundle.putString("username", username);
                bundle.putString("firstname", firstname);
                bundle.putString("lastname", lastname);
                bundle.putString("role", role);
                bundle.putStringArrayList("house_nos", house_nos);
                bundle.putStringArrayList("house_ids", house_ids);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("user", bundle);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
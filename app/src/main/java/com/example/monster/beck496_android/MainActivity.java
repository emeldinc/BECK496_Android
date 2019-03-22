package com.example.monster.beck496_android;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private TextView textView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Initializing textview
        textView = (TextView) findViewById(R.id.userView);
        Intent intent = getIntent();
        //Fetching email from shared preferences
        Bundle bundle = intent.getBundleExtra("user");
        User user = new User();
        String username = user.setUsername(bundle.getString("username"));
        String firstname = user.setFirstname(bundle.getString("firstname"));
        String lastname = user.setLastname(bundle.getString("lastname"));
        String role = user.setRole(bundle.getString("role"));
        String house_id = user.setHouse_id(bundle.getString("house_id"));

        //Showing the current logged in email to textview
        textView.setText(firstname + " " + lastname);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main_page) {

        } else if (id == R.id.nav_messages) {
            Intent intent = new Intent(MainActivity.this, MessageActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_anouncements) {
            Intent intent = new Intent(MainActivity.this, AnouncementActivity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_votes) {
            Intent intent = new Intent(MainActivity.this, VoteActivity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_finance) {
            Intent intent = new Intent(MainActivity.this, FinanceActivity.class);
            startActivity(intent);
            return true;

        }
        else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_exit) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure you want to logout?");
            alertDialogBuilder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {


                            //Starting login activity
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });

            alertDialogBuilder.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });

            //Showing the alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

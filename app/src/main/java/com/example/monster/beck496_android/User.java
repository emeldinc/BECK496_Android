package com.example.monster.beck496_android;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class User {
    private String user_id;
    private String username;
    private String firstname;
    private String lastname;

    public String getUser_id() {
        return user_id;
    }

    public String setUser_id(String user_id) {
        this.user_id = user_id;
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        this.username = username;
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String setFirstname(String firstname) {
        this.firstname = firstname;
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String setLastname(String lastname) {
        this.lastname = lastname;
        return lastname;
    }

    public String getRole() {
        return role;
    }

    public String setRole(String role) {
        this.role = role;
        return role;
    }

    public ArrayList<String> getHouse_ids() {
        return house_ids;
    }


    public ArrayList<String> getHouse_nos() {
        return house_nos;
    }


    private String role;

    public ArrayList<String> setHouse_ids(ArrayList<String> house_ids) {
        this.house_ids = house_ids;
        return house_ids;
    }

    public ArrayList<String> setHouse_nos(ArrayList<String> house_nos) {
        this.house_nos = house_nos;
        return house_nos;
    }

    private ArrayList<String> house_ids;


    private ArrayList<String> house_nos;

}

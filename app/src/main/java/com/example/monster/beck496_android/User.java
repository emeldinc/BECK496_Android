package com.example.monster.beck496_android;

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

    public String getHouse_id() {
        return house_id;
    }

    public String setHouse_id(String house_id) {
        this.house_id = house_id;
        return house_id;
    }

    private String role;
    private String house_id;

}

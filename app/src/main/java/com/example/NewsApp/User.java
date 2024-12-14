package com.example.NewsApp;

import android.net.Uri;

public class User {
    public String id, name,  email, password, image_id, status;

    public User(String id, String name, String email, String password, Uri imageUri, String status){}


    public User(String id, String name, String email, String password, String image_id, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image_id = image_id;
        this.status = "";
    }
}




package com.example.NewsApp;
import  java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Demien implements Serializable {

    public Demien(String upload_uri, String name) {
        this.upload_uri = upload_uri;
        this.name = name;
    }

    public String getUpload_uri() {
        return upload_uri;
    }

    public void setUpload_uri(double id) {
        this.upload_uri = upload_uri;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String upload_uri;
    private String name;
}
package com.example.laba4;

import java.io.Serializable;

public class Contact implements Serializable{
    private String id;
    private String email;
    private String location;
    private String number;
    private String link;


    Contact(String id, String email, String location, String  number, String link){
        this.id = id;
        this.email = email;
        this.location = location;
        this.number = number;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public String getLocation(){
        return location;
    }

    public  String  getNumber() {
        return number;
    }

    public String getLink() {
        return link;
    }
    @Override
    public  String toString(){
        return id + " ("+location + ")";
    }
}

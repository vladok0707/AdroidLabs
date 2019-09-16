package com.example.laba3;

import java.util.Date;

public class ItemCalendar {
    private Date date;
    private String text;

    ItemCalendar(Date date, String text){
        this.date = date;
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

package com.example.weirdo.myapp1;

/**
 * Created by Weirdo on 28-04-2016.
 */
public class Item{
    private String date,time,item, price;

    public Item() {
    }

    public Item(String item, String price ,String Date, String Time) {
        this.item = item;
        this.price = price;
        this.date = Date;
        this.time = Time;

    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
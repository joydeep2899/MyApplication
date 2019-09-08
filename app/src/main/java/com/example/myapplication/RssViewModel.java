package com.example.myapplication;

public class RssViewModel {

    public String title;
    public String link;
    public String description;
    public String imgsrc;

    public RssViewModel(String title, String link, String description,String imagesrc) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.imgsrc=imagesrc;
    }
}
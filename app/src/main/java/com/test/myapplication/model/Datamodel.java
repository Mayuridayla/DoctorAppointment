package com.test.myapplication.model;

public class Datamodel {
    private String title, specailist, location;

    public Datamodel() {
    }

    public Datamodel(String title, String specailist, String location) {
        this.title = title;
        this.specailist = specailist;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecailist() {
        return specailist;
    }

    public void setSpecailist(String specailist) {
        this.specailist = specailist;
    }

}
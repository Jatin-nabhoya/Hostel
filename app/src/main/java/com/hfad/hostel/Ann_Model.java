package com.hfad.hostel;

public class Ann_Model {
    String title,disc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public Ann_Model(String title, String disc) {
        this.title = title;
        this.disc = disc;
    }

    public Ann_Model() {
    }
}

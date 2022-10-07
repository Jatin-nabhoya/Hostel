package com.hfad.hostel;

public class HelperClass {
    String title , disc ;
    String roomNo, fname , complain;




    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public HelperClass(String roomNo, String fname, String complain) {
        this.roomNo = roomNo;
        this.fname = fname;
        this.complain = complain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getDisc() {
        return disc;
    }

    public HelperClass() {
    }

    public HelperClass(String title, String disc) {
        this.title = title;
        this.disc = disc;
    }
}

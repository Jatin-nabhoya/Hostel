package com.hfad.hostel;

public class Com_Model {
    String roomNo , fname , complain;

    public Com_Model(String roomNo, String fname, String complain) {
        this.roomNo = roomNo;
        this.fname = fname;
        this.complain = complain;
    }

    public Com_Model() {
    }

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
}

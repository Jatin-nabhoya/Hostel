package com.hfad.hostel;

public class soac_Model {
    String fadvisor,fees,link,omember,oname,oobj,otype;

    public String getFadvisor() {
        return fadvisor;
    }

    public void setFadvisor(String fadvisor) {
        this.fadvisor = fadvisor;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getOmember() {
        return omember;
    }

    public void setOmember(String omember) {
        this.omember = omember;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOobj() {
        return oobj;
    }

    public void setOobj(String oobj) {
        this.oobj = oobj;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public soac_Model(String fadvisor, String fees, String link, String omember, String oname, String oobj, String otype) {
        this.fadvisor = fadvisor;
        this.fees = fees;
        this.link = link;
        this.omember = omember;
        this.oname = oname;
        this.oobj = oobj;
        this.otype = otype;
    }
}

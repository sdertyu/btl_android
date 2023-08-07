package com.example.simplereviewfilm.ListPhim;

public class userCMT {

    private String ten, cmt;

    public userCMT(){}

    public userCMT(String ten, String cmt) {
        this.ten = ten;
        this.cmt = cmt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
}

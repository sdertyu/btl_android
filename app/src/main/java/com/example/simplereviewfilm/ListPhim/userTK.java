package com.example.simplereviewfilm.ListPhim;

import java.io.Serializable;

public class userTK implements Serializable {

    private String sdt, matkhau, email, hoten;

    public userTK() {
    }

    public userTK(String sdt, String matkhau, String email, String hoten) {
        this.sdt = sdt;
        this.matkhau = matkhau;
        this.email = email;
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}

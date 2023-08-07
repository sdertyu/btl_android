package com.example.simplereviewfilm.ListPhim;

import java.io.Serializable;
import java.util.Date;

public class userFilm implements Serializable {

    private String idPhim;
    private String tenPhim;
    private byte[] imgSrc;
    private String sNgayChieu;
    private String sThoiLuong;
    private String sTheLoai;
    private String sDaoDien;
    private String sDoTuoi;
    private String sDienVien;
    private String sGioiThieu;
    private String sLoaiPhim;

    public userFilm() {
    }

    public userFilm(String idPhim, String tenPhim, byte[] imgSrc, String sNgayChieu,
                    String sThoiLuong, String sTheLoai, String sDaoDien, String sDoTuoi,
                    String sDienVien, String sGioiThieu, String sLoaiPhim) {
        this.idPhim = idPhim;
        this.tenPhim = tenPhim;
        this.imgSrc = imgSrc;
        this.sNgayChieu = sNgayChieu;
        this.sThoiLuong = sThoiLuong;
        this.sTheLoai = sTheLoai;
        this.sDaoDien = sDaoDien;
        this.sDoTuoi = sDoTuoi;
        this.sDienVien = sDienVien;
        this.sGioiThieu = sGioiThieu;
        this.sLoaiPhim = sLoaiPhim;
    }

    public String getIdPhim() {
        return idPhim;
    }

    public void setIdPhim(String idPhim) {
        this.idPhim = idPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public byte[] getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(byte[] imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getsNgayChieu() {
        return sNgayChieu;
    }

    public void setsNgayChieu(String sNgayChieu) {
        this.sNgayChieu = sNgayChieu;
    }

    public String getsThoiLuong() {
        return sThoiLuong;
    }

    public void setsThoiLuong(String sThoiLuong) {
        this.sThoiLuong = sThoiLuong;
    }

    public String getsTheLoai() {
        return sTheLoai;
    }

    public void setsTheLoai(String sTheLoai) {
        this.sTheLoai = sTheLoai;
    }

    public String getsDaoDien() {
        return sDaoDien;
    }

    public void setsDaoDien(String sDaoDien) {
        this.sDaoDien = sDaoDien;
    }

    public String getsDoTuoi() {
        return sDoTuoi;
    }

    public void setsDoTuoi(String sDoTuoi) {
        this.sDoTuoi = sDoTuoi;
    }

    public String getsDienVien() {
        return sDienVien;
    }

    public void setsDienVien(String sDienVien) {
        this.sDienVien = sDienVien;
    }

    public String getsGioiThieu() {
        return sGioiThieu;
    }

    public void setsGioiThieu(String sGioiThieu) {
        this.sGioiThieu = sGioiThieu;
    }

    public String getsLoaiPhim() {
        return sLoaiPhim;
    }

    public void setsLoaiPhim(String sLoaiPhim) {
        this.sLoaiPhim = sLoaiPhim;
    }
}

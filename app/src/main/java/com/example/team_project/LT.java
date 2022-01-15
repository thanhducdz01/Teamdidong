package com.example.team_project;

public class LT {
    private String msv;
    private String idMH;
    private String idPhong;
    private int idHK;
    private String ngaythi;
    private String giobatdau;

    public LT(String msv, String idMH, String idPhong, int idHK, String ngaythi, String giobatdau) {
        this.msv = msv;
        this.idMH = idMH;
        this.idPhong = idPhong;
        this.idHK = idHK;
        this.ngaythi = ngaythi;
        this.giobatdau = giobatdau;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getIdMH() {
        return idMH;
    }

    public void setIdMH(String idMH) {
        this.idMH = idMH;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public int getIdHK() {
        return idHK;
    }

    public void setIdHK(int idHK) {
        this.idHK = idHK;
    }

    public String getNgaythi() {
        return ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        this.ngaythi = ngaythi;
    }

    public String getGiobatdau() {
        return giobatdau;
    }

    public void setGiobatdau(String giobatdau) {
        this.giobatdau = giobatdau;
    }
}

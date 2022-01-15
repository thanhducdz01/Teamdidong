package com.example.team_project;

public class tkb {
    private String idTKB;
    private String tietBD;
    private String tietKT;
    private String idPhong;
    private String Monhoc;

    public tkb(String idTKB, String tietBD, String tietKT, String idPhong, String monhoc) {
        this.idTKB = idTKB;
        this.tietBD = tietBD;
        this.tietKT = tietKT;
        this.idPhong = idPhong;
        this.Monhoc = monhoc;
    }

    public String getIdTKB() {
        return idTKB;
    }

    public void setIdTKB(String idTKB) {
        this.idTKB = idTKB;
    }

    public String getTietBD() {
        return tietBD;
    }

    public void setTietBD(String tietBD) {
        this.tietBD = tietBD;
    }

    public String getTietKT() {
        return tietKT;
    }

    public void setTietKT(String tietKT) {
        this.tietKT = tietKT;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public String getMonhoc() {
        return Monhoc;
    }

    public void setMonhoc(String monhoc) {
        Monhoc = monhoc;
    }
}




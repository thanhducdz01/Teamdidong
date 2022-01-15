package com.example.team_project.NotificationModule;

import java.io.Serializable;

public class notification implements Serializable {
    private String Id;
    private String NguoiGui;
    private String TieuDe;
    private String NoiDung;
    private String NgayGui;

    public notification(String id, String nguoiGui, String tieuDe, String noiDung, String ngayGui) {
        this.Id = id;
        this.NguoiGui = nguoiGui;
        this.TieuDe = tieuDe;
        this.NoiDung = noiDung;
        this.NgayGui = ngayGui;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getNguoiGui() {
        return NguoiGui;
    }

    public void setNguoiGui(String nguoiGui) {
        this.NguoiGui = nguoiGui;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.TieuDe = tieuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        this.NoiDung = noiDung;
    }

    public String getNgayGui() {
        return NgayGui;
    }

    public void setNgayGui(String ngayGui) {
        this.NgayGui = ngayGui;
    }
}

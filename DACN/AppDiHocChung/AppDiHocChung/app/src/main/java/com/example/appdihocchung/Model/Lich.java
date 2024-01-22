package com.example.appdihocchung.Model;

public class Lich {
    public String getNgayThu() {
        return ngayThu;
    }

    public void setNgayThu(String ngayThu) {
        this.ngayThu = ngayThu;
    }

    public String getDiemHen() {
        return diemHen;
    }

    public void setDiemHen(String diemHen) {
        this.diemHen = diemHen;
    }

    public String getThoiGianHen() {
        return thoiGianHen;
    }

    public void setThoiGianHen(String thoiGianHen) {
        this.thoiGianHen = thoiGianHen;
    }

    public String getThongTinLienLac() {
        return thongTinLienLac;
    }

    public void setThongTinLienLac(String thongTinLienLac) {
        this.thongTinLienLac = thongTinLienLac;
    }

    public Lich(String ngayThu, String diemHen, String thoiGianHen, String thongTinLienLac) {
        this.ngayThu = ngayThu;
        this.diemHen = diemHen;
        this.thoiGianHen = thoiGianHen;
        this.thongTinLienLac = thongTinLienLac;
    }

    public Lich() {
    }

    private String ngayThu;
    private String diemHen;
    private String thoiGianHen;
    private String thongTinLienLac;

}

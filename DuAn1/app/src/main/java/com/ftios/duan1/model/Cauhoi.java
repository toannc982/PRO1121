package com.ftios.duan1.model;

import java.io.Serializable;

public class Cauhoi implements Serializable {
    private int id;
    private  String cauhoi;
    private String trloi_a;
    private String trloi_b;
    private String trloi_c;
    private String trloi_d;
    private String dapan;
    private String hinhanh;
    private int sode;
    private String monhoc;
    private String traloi = "";
    public  int luachonID = -1;   //hỗ trợ check ID của radiogroup

    public String getTraloi() {
        return traloi;
    }

    public void setTraloi(String traloi) {
        this.traloi = traloi;
    }

    public Cauhoi(int id, String cauhoi, String trloi_a, String trloi_b, String trloi_c, String trloi_d, String dapan, int sode, String monhoc, String hinhanh, String traloi) {
        this.id = id;
        this.cauhoi = cauhoi;
        this.trloi_a = trloi_a;
        this.trloi_b = trloi_b;
        this.trloi_c = trloi_c;
        this.trloi_d = trloi_d;
        this.dapan = dapan;
        this.hinhanh = hinhanh;
        this.sode = sode;
        this.monhoc = monhoc;
        this.traloi = traloi;
    }

    public Cauhoi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getTrloi_a() {
        return trloi_a;
    }

    public void setTrloi_a(String trloi_a) {
        this.trloi_a = trloi_a;
    }

    public String getTrloi_b() {
        return trloi_b;
    }

    public void setTrloi_b(String trloi_b) {
        this.trloi_b = trloi_b;
    }

    public String getTrloi_c() {
        return trloi_c;
    }

    public void setTrloi_c(String trloi_c) {
        this.trloi_c = trloi_c;
    }

    public String getTrloi_d() {
        return trloi_d;
    }

    public void setTrloi_d(String trloi_d) {
        this.trloi_d = trloi_d;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSode() {
        return sode;
    }

    public void setSode(int sode) {
        this.sode = sode;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }

    @Override
    public String toString() {
        return "Cauhoi{" +
                "id=" + id +
                ", cauhoi='" + cauhoi + '\'' +
                ", trloi_a='" + trloi_a + '\'' +
                ", trloi_b='" + trloi_b + '\'' +
                ", trloi_c='" + trloi_c + '\'' +
                ", trloi_d='" + trloi_d + '\'' +
                ", dapan='" + dapan + '\'' +
                ", hinhanh='" + hinhanh + '\'' +
                ", sode=" + sode +
                ", monhoc='" + monhoc + '\'' +
                ", traloi='" + traloi + '\'' +
                ", luachonID=" + luachonID +
                '}';
    }
}
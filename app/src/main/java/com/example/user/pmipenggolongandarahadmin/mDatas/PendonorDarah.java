package com.example.user.pmipenggolongandarahadmin.mDatas;

/**
 * Created by USER on 15/08/2017.
 */

public class PendonorDarah {
    private String nama;
    private String alamat;
    private String gol_darah;
    private String no_hp;
    private String jenis_kelamin;
    private String pekerjaan;
    private String tanggal_daftar;
    private String tanggal_lahir;
    private String berat_badan;
    private String tinggi_badan;

    public PendonorDarah() {}

    public PendonorDarah(String nama, String alamat, String gol_darah, String no_hp, String jenis_kelamin, String pekerjaan, String tanggal_daftar, String tanggal_lahir, String berat_badan, String tinggi_badan) {
        this.nama = nama;
        this.alamat = alamat;
        this.gol_darah = gol_darah;
        this.no_hp = no_hp;
        this.jenis_kelamin = jenis_kelamin;
        this.pekerjaan = pekerjaan;
        this.tanggal_daftar = tanggal_daftar;
        this.tanggal_lahir = tanggal_lahir;
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getGol_darah() {
        return gol_darah;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public String getTanggal_daftar() {
        return tanggal_daftar;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getBerat_badan() {
        return berat_badan;
    }

    public String getTinggi_badan() {
        return tinggi_badan;
    }
}

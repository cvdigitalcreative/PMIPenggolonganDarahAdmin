package com.example.user.pmipenggolongandarahadmin.mDatas;

/**
 * Created by USER on 16/09/2017.
 */

public class StokDarahKeluar {
    String nama;
    String umur;
    String harga_darah;

    public StokDarahKeluar() {
    }

    public StokDarahKeluar(String nama, String umur, String harga_darah) {
        this.nama = nama;
        this.umur = umur;
        this.harga_darah = harga_darah;
    }

    public String getNama() {
        return nama;
    }

    public String getUmur() {
        return umur;
    }

    public String getHarga() {
        return harga_darah;
    }
}

package com.example.user.pmipenggolongandarahadmin.mDatas;

/**
 * Created by USER on 15/08/2017.
 */

public class StokDarah {
    private String golongan_darah;
    private String jumlah;
    private String stok_darahmasuk;
    private String stok_darahkeluar;

    public StokDarah() {}

    public StokDarah(String golongan_darah, String jumlah, String stok_darahmasuk, String stok_darahkeluar) {
        this.golongan_darah = golongan_darah;
        this.jumlah = jumlah;
        this.stok_darahmasuk = stok_darahmasuk;
        this.stok_darahkeluar = stok_darahkeluar;
    }

    public String getGolongan_darah() {
        return golongan_darah;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getStok_darahmasuk() {
        return stok_darahmasuk;
    }

    public String getStok_darahkeluar() {
        return stok_darahkeluar;
    }
}

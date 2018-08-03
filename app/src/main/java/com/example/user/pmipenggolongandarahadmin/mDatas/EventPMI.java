package com.example.user.pmipenggolongandarahadmin.mDatas;

/**
 * Created by USER on 15/08/2017.
 */

public class EventPMI {
    private String tanggal;
    private String tempat;
    private String jam;

    public EventPMI() {}

    public EventPMI(String tanggal, String tempat, String jam) {
        this.tanggal = tanggal;
        this.tempat = tempat;
        this.jam = jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getTempat() {
        return tempat;
    }

    public String getJam() {
        return jam;
    }
}

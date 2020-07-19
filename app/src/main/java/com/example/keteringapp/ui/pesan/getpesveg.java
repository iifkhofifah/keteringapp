package com.example.keteringapp.ui.pesan;

public class getpesveg {
    String pemvegId;
    String pemvegNama;
    String pemvegNohp;
    String pemvegAlamat;
    String pemvegSpinner;
    String pemvegTanggal;
    String pemvegPorsi;
    int pemvegTotal;

    public getpesveg(){
    }

    public getpesveg(String pemvegId, String pemvegNama, String pemvegNohp, String pemvegAlamat, String pemvegSpinner, String pemvegTanggal, String pemvegPorsi, int pemvegTotal) {
        this.pemvegId = pemvegId;
        this.pemvegNama = pemvegNama;
        this.pemvegNohp = pemvegNohp;
        this.pemvegAlamat = pemvegAlamat;
        this.pemvegSpinner = pemvegSpinner;
        this.pemvegTanggal = pemvegTanggal;
        this.pemvegPorsi = pemvegPorsi;
        this.pemvegTotal = pemvegTotal;
    }

    public String getPemvegId() {
        return pemvegId;
    }

    public String getPemvegNama() {
        return pemvegNama;
    }

    public String getPemvegNohp() {
        return pemvegNohp;
    }

    public String getPemvegAlamat() {
        return pemvegAlamat;
    }

    public String getPemvegSpinner() {
        return pemvegSpinner;
    }

    public String getPemvegTanggal() {
        return pemvegTanggal;
    }

    public String getPemvegPorsi() {
        return pemvegPorsi;
    }

    public int getPemvegTotal (){ return pemvegTotal; }
}

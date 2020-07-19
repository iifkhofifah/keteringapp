package com.example.keteringapp.ui.pesan;

public class getpesta {
    String pempesId;
    String pempesNama;
    String pempesNohp;
    String pempesAlamat;
    String pempesSppras;
    String pempesTanggal;
    String pempesCbtamb;
    int pempesTotal;

    public getpesta(){

    }

    public getpesta(String pempesId, String pempesNama, String pempesNohp, String pempesAlamat, String pempesSppras, String pempesTanggal, String pempesCbtamb, int pempesTotal) {
        this.pempesId = pempesId;
        this.pempesNama = pempesNama;
        this.pempesNohp = pempesNohp;
        this.pempesAlamat = pempesAlamat;
        this.pempesSppras = pempesSppras;
        this.pempesTanggal = pempesTanggal;
        this.pempesCbtamb = pempesCbtamb;
        this.pempesTotal = pempesTotal;
    }

    public String getPempesId() {
        return pempesId;
    }

    public String getPempesNama() {
        return pempesNama;
    }

    public String getPempesNohp() {
        return pempesNohp;
    }

    public String getPempesAlamat() {
        return pempesAlamat;
    }

    public String getPempesSppras() {
        return pempesSppras;
    }

    public String getPempesTanggal() {
        return pempesTanggal;
    }

    public String getPempesCbtamb() {
        return pempesCbtamb;
    }
    public int getPempesTotal() {
        return pempesTotal;
    }
}

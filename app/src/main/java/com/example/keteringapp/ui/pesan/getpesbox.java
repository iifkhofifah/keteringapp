package com.example.keteringapp.ui.pesan;

public class getpesbox {
    String pemboxId;
    String pemboxNama;
    String pemboxNohp;
    String pemboxAlamat;
    String pemboxSppil;
    String pemboxTanggal;
    String pemboxPaket;
    String pemboxPorsi;
    int pemboxTotal;

    public getpesbox(){

    }

    public getpesbox(String pemboxId, String pemboxNama, String pemboxNohp, String pemboxAlamat, String pemboxSppil, String pemboxTanggal,String pemboxPaket,String pemboxPorsi, int pemboxTotal) {
        this.pemboxId = pemboxId;
        this.pemboxNama = pemboxNama;
        this.pemboxNohp = pemboxNohp;
        this.pemboxAlamat = pemboxAlamat;
        this.pemboxSppil = pemboxSppil;
        this.pemboxTanggal = pemboxTanggal;
        this.pemboxPaket = pemboxPaket;
        this.pemboxPorsi = pemboxPorsi;
        this.pemboxTotal = pemboxTotal;
    }

    public String getPemboxId() {
        return pemboxId;
    }

    public String getPemboxNama() {
        return pemboxNama;
    }

    public String getPemboxNohp() {
        return pemboxNohp;
    }

    public String getPemboxAlamat() {
        return pemboxAlamat;
    }

    public String getPemboxSppil() {
        return pemboxSppil;
    }

    public String getPemboxTanggal() {
        return pemboxTanggal;
    }

    public String getPemboxPaket() {
        return pemboxPaket;
    }

    public String getPemboxPorsi() {
        return pemboxPorsi;
    }

    public int getPemboxTotal() {
        return pemboxTotal;
    }
}

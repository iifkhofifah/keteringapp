package com.example.keteringapp.ui.pesan;

public class getpesaqiqah {
    String pemdietId;
    String pemdietNama;
    String pemdietNohp;
    String pemdietAlamat;
    String pemdietSpinneraqiqah;
    String pemdietTanggal;
    int pemdietHarga;

    public getpesaqiqah(){

    }

    public getpesaqiqah(String pemdietId, String pemdietNama, String pemdietNohp, String pemdietAlamat, String pemdietSpinneraqiqah, String pemdietTanggal, int pemdietHarga) {
        this.pemdietId = pemdietId;
        this.pemdietNama = pemdietNama;
        this.pemdietNohp = pemdietNohp;
        this.pemdietAlamat = pemdietAlamat;
        this.pemdietSpinneraqiqah = pemdietSpinneraqiqah;
        this.pemdietTanggal = pemdietTanggal;
        this.pemdietHarga = pemdietHarga;
    }

    public String getPemdietId() {
        return pemdietId;
    }

    public String getPemdietNama() {
        return pemdietNama;
    }

    public String getPemdietNohp() {
        return pemdietNohp;
    }

    public String getPemdietAlamat() {
        return pemdietAlamat;
    }

    public String getPemdietSpinneraqiqah() {
        return pemdietSpinneraqiqah;
    }

    public String getPemdietTanggal() {
        return pemdietTanggal;
    }
    public int getPemdietHarga(){
        return pemdietHarga;
    }
}

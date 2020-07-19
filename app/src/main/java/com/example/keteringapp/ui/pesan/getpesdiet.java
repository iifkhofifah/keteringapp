package com.example.keteringapp.ui.pesan;

public class getpesdiet {
    String pemdietId;
    String pemdietNama;
    String pemdietNohp;
    String pemdietAlamat;
    String pemdietSpinnerdiet;
    String pemdietBeratbdn;
    String pemdietSpinnerpkt;
    int pemdietTotal;

    public getpesdiet(){

    }

    public getpesdiet(String pemdietId, String pemdietNama, String pemdietNohp, String pemdietAlamat, String pemdietSpinnerdiet, String pemdietBeratbdn, String pemdietSpinnerpkt, int pemdietTotal) {
        this.pemdietId = pemdietId;
        this.pemdietNama = pemdietNama;
        this.pemdietNohp = pemdietNohp;
        this.pemdietAlamat = pemdietAlamat;
        this.pemdietSpinnerdiet = pemdietSpinnerdiet;
        this.pemdietBeratbdn = pemdietBeratbdn;
        this.pemdietSpinnerpkt = pemdietSpinnerpkt;
        this.pemdietTotal = pemdietTotal;
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

    public String getPemdietSpinnerdiet() {
        return pemdietSpinnerdiet;
    }

    public String getPemdietBeratbdn() {
        return pemdietBeratbdn;
    }

    public String getPemdietSpinnerpkt() {
        return pemdietSpinnerpkt;
    }
    public int getPemdietTotal(){
        return pemdietTotal;
    }
}

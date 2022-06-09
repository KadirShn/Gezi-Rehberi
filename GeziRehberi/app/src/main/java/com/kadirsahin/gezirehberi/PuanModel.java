package com.kadirsahin.gezirehberi;

public class PuanModel {
    public String toplampuan;
    public String kisisayi;
    public String ortalama;


    public PuanModel(){
    }

    public PuanModel(String toplampuan, String kisisayi, String ortalama) {
        this.toplampuan = toplampuan;
        this.kisisayi = kisisayi;
        this.ortalama = ortalama;
    }

    public String getToplampuan() {
        return toplampuan;
    }

    public void setToplampuan(String toplampuan) {
        this.toplampuan = toplampuan;
    }

    public String getKisisayi() {
        return kisisayi;
    }

    public void setKisisayi(String kisisayi) {
        this.kisisayi = kisisayi;
    }

    public String getOrtalama() {
        return ortalama;
    }

    public void setOrtalama(String ortalama) {
        this.ortalama = ortalama;
    }
}

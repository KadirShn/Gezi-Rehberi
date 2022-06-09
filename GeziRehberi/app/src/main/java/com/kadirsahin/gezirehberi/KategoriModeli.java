package com.kadirsahin.gezirehberi;
public class KategoriModeli {

    String yeradi,yerfotograf,yerbilgi,yersure,yerucret,yerno,yerpuanstr;
    Integer toplamkisi;
    Float toplampuanlar,sayi;

    public KategoriModeli(){

    }

    public KategoriModeli(String yeradi, String yerfotograf, String yerbilgi, String yersure, String yerucret, String yerno, String yerpuanstr, Integer toplamkisi, Float toplampuanlar, Float sayi) {
        this.yeradi = yeradi;
        this.yerfotograf = yerfotograf;
        this.yerbilgi = yerbilgi;
        this.yersure = yersure;
        this.yerucret = yerucret;
        this.yerno = yerno;
        this.yerpuanstr = yerpuanstr;
        this.toplamkisi = toplamkisi;
        this.toplampuanlar = toplampuanlar;
        this.sayi = sayi;
    }

    public KategoriModeli(Integer toplamkisi, Float toplampuanlar, Float sayi) {
        this.toplamkisi = toplamkisi;
        this.toplampuanlar = toplampuanlar;
        this.sayi = sayi;
    }

    public String getYeradi() {
        return yeradi;
    }

    public void setYeradi(String yeradi) {
        this.yeradi = yeradi;
    }

    public String getYerfotograf() {
        return yerfotograf;
    }

    public void setYerfotograf(String yerfotograf) {
        this.yerfotograf = yerfotograf;
    }

    public String getYerbilgi() {
        return yerbilgi;
    }

    public void setYerbilgi(String yerbilgi) {
        this.yerbilgi = yerbilgi;
    }

    public String getYersure() {
        return yersure;
    }

    public void setYersure(String yersure) {
        this.yersure = yersure;
    }

    public String getYerucret() {
        return yerucret;
    }

    public void setYerucret(String yerucret) {
        this.yerucret = yerucret;
    }

    public String getYerno() {
        return yerno;
    }

    public void setYerno(String yerno) {
        this.yerno = yerno;
    }

    public String getYerpuanstr() {
        return yerpuanstr;
    }

    public void setYerpuanstr(String yerpuanstr) {
        this.yerpuanstr = yerpuanstr;
    }

    public Integer getToplamkisi() {
        return toplamkisi;
    }

    public void setToplamkisi(Integer toplamkisi) {
        this.toplamkisi = toplamkisi;
    }

    public Float getToplampuanlar() {
        return toplampuanlar;
    }

    public void setToplampuanlar(Float toplampuanlar) {
        this.toplampuanlar = toplampuanlar;
    }

    public Float getSayi() {
        return sayi;
    }

    public void setSayi(Float sayi) {
        this.sayi = sayi;
    }
}

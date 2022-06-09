package com.kadirsahin.gezirehberi;

public class YorumModel {
    public String kullaniciad;
    public String yorum;

    public YorumModel() {

    }

    public YorumModel(String kullaniciad, String yorum) {
        this.yorum = yorum;
        this.kullaniciad = kullaniciad;
    }

    public String getKullaniciad() {
        return kullaniciad;
    }

    public void setKullaniciad(String kullaniciad) {
        this.kullaniciad = kullaniciad;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }
}

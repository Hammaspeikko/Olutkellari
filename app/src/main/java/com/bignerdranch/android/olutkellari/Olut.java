package com.bignerdranch.android.olutkellari;

/**
 * Created by Sami on 24.1.2017.
 */

public class Olut {

    Integer olutID;
    String nimi;
    String tyyppi;
    Integer arvosana;
    String paikka;
    Double hinta;
    String maa;

    public Olut() {
    }

    public String getMaa() {
        return maa;
    }

    public void setMaa(String maa) {
        this.maa = maa;
    }

    public Integer getOlutID() {
        return olutID;
    }

    public void setOlutID(Integer olutID) {
        this.olutID = olutID;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Integer getArvosana() {
        return arvosana;
    }

    public void setArvosana(Integer arvosana) {
        this.arvosana = arvosana;
    }

    public String getPaikka() {
        return paikka;
    }

    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }

    public Double getHinta() {
        return hinta;
    }

    public void setHinta(Double hinta) {
        this.hinta = hinta;
    }

    @Override
    public String toString() {
        return "Olut{" +
                "olutID=" + olutID +
                ", nimi='" + nimi + '\'' +
                ", tyyppi='" + tyyppi + '\'' +
                ", arvosana=" + arvosana +
                ", paikka='" + paikka + '\'' +
                ", hinta=" + hinta +
                ", maa='" + maa + '\'' +
                '}';
    }
}

package com.bignerdranch.android.olutkellari;

import java.io.Serializable;

/**
 * Created by Sami on 26.1.2017.
 */

class OlutKortti implements Serializable {

    private String nimi;
    private String tyyppi;
    private String maa;
    private String paikka;
    private Double alkoholi;
    private Double hinta;
    private Double arvosana;

    public OlutKortti(String nimi, String tyyppi, String maa, String paikka, Double alkoholi, Double hinta, Double arvosana) {
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.maa = maa;
        this.paikka = paikka;
        this.alkoholi = alkoholi;
        this.hinta = hinta;
        this.arvosana = arvosana;
    }

    public OlutKortti() {
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

    public String getMaa() {
        return maa;
    }

    public void setMaa(String maa) {
        this.maa = maa;
    }

    public String getPaikka() {
        return paikka;
    }

    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }

    public Double getAlkoholi() {
        return alkoholi;
    }

    public void setAlkoholi(Double alkoholi) {
        this.alkoholi = alkoholi;
    }

    public Double getHinta() {
        return hinta;
    }

    public void setHinta(Double hinta) {
        this.hinta = hinta;
    }

    public Double getArvosana() {
        return arvosana;
    }

    public void setArvosana(Double arvosana) {
        this.arvosana = arvosana;
    }

    @Override
    public String toString() {
        return "OlutKortti{" +
                "nimi='" + nimi + '\'' +
                ", tyyppi='" + tyyppi + '\'' +
                ", maa='" + maa + '\'' +
                ", paikka='" + paikka + '\'' +
                ", alkoholi=" + alkoholi +
                ", hinta=" + hinta +
                ", arvosana=" + arvosana +
                '}';
    }
}

package com.engeto.StateVat;

public class Stat {
    private String zkratkaStatu;
    private String nazevStatu;
    private double plnaSazba;
    private double snizenaSazba;
    private boolean specialnisazba;

   /*
    public Stat(String zkratkaStatu, String nazevStatu, double plnaSazba, double snizenaSazba, boolean specialnisazba) {
        this.zkratkaStatu = zkratkaStatu;
        this.nazevStatu = nazevStatu;
        this.plnaSazba = plnaSazba;
        this.snizenaSazba = snizenaSazba;
        this.specialnisazba = specialnisazba;
    }*/

    public String getZkratkaStatu() {
        return zkratkaStatu;
    }

    public void setZkratkaStatu(String zkratkaStatu) {
        this.zkratkaStatu = zkratkaStatu;
    }

    public String getNazevStatu() {
        return nazevStatu;
    }

    public void setNazevStatu(String nazevStatu) {
        this.nazevStatu = nazevStatu;
    }

    public double getPlnaSazba() {
        return plnaSazba;
    }

    public void setPlnaSazba(double plnaSazba) {
        this.plnaSazba = plnaSazba;
    }

    public double getSnizenaSazba() {
        return snizenaSazba;
    }

    public void setSnizenaSazba(double snizenaSazba) {
        this.snizenaSazba = snizenaSazba;
    }

    public boolean isSpecialnisazba() {
        return specialnisazba;
    }

    public void setSpecialnisazba(boolean specialnisazba) {
        this.specialnisazba = specialnisazba;
    }
}

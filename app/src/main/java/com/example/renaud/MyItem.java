package com.example.renaud;

import java.io.Serializable;

public class MyItem implements Serializable {
    private String name, surname, telephone, sexe;
    private int temperature_corporelle, pression_arterielle, temperature_ambiamte, rythme_cardiaque;
    private boolean mouvement;

    public MyItem() {
    }

    public MyItem(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getTemperature_corporelle() {
        return temperature_corporelle;
    }

    public void setTemperature_corporelle(int temperature_corporelle) {
        this.temperature_corporelle = temperature_corporelle;
    }

    public int getPression_arterielle() {
        return pression_arterielle;
    }

    public void setPression_arterielle(int pression_arterielle) {
        this.pression_arterielle = pression_arterielle;
    }

    public int getTemperature_ambiamte() {
        return temperature_ambiamte;
    }

    public void setTemperature_ambiamte(int temperature_ambiamte) {
        this.temperature_ambiamte = temperature_ambiamte;
    }

    public int getRythme_cardiaque() {
        return rythme_cardiaque;
    }

    public void setRythme_cardiaque(int rythme_cardiaque) {
        this.rythme_cardiaque = rythme_cardiaque;
    }

    public boolean isMouvement() {
        return mouvement;
    }

    public void setMouvement(boolean mouvement) {
        this.mouvement = mouvement;
    }
}

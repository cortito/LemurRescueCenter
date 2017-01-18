package com.projet_100_heures.lemurrescuecenter.model;

/**
 * Created by corto_000 on 18/01/2017.
 */

public class PoidsModel {
    private int idDB;
    String  nom;
    String date;
    String poids;

    public PoidsModel() {
    }

    public PoidsModel(int idDB, String nom, String date, String poids) {
        this.idDB = idDB;
        this.nom = nom;
        this.date = date;
        this.poids = poids;
    }




}

package fr.lrc.model.poids;

import java.io.Serializable;

public class PoidsModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521490285320525552L;

	private int idDB;
	private String nom;
	private String date;
	private Double poids;

	public PoidsModel(int idDB, String nom, String date, Double poids) {
		this.idDB = idDB;
		this.nom = nom;
		this.date = date;
		this.poids = poids;
	}

	public PoidsModel() {
	}

	public PoidsModel(PoidsEntity poidsE) {
		this.idDB = poidsE.getIdDB();
		this.nom = poidsE.getNom();
		this.date = poidsE.getDate();
		this.poids = poidsE.getPoids();
	}

	public int getIdDB() {
		return idDB;
	}

	public void setIdDB(int idDB) {
		this.idDB = idDB;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public String toJSON() {
		StringBuilder s = new StringBuilder();
		s.append("{ \"idDB\" : " + idDB + ", ")
		.append("\"nom\" : \"" + nom + "\", ")
		.append("\"date\" : \"" + date + "\", ")
		.append("\"poids\" : \"" + poids + "\"}");

		return s.toString();
	}

}

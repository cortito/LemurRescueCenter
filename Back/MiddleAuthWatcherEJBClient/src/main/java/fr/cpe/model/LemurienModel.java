package fr.cpe.model;

import java.io.Serializable;
import java.time.LocalDate;

public class LemurienModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5698754191407303366L;

	private int id;
	private boolean sexe;
	private String nom;
	private LocalDate dateDeNaissance;
	private int taille;
	private double poids;
	private String caracteristiques;
	private String etat;
	private String famille;
	private double latitude;
	private double longitude;
	private int parent1;
	private int parent2;
	private LocalDate dernierScan;

	public LemurienModel(int id, boolean sexe, String nom, LocalDate dateDeNaissance, int taille, double poids,
			String caracteristiques, String etat, String famille, double latitude, double longitude, int parent1,
			int parent2, LocalDate dernierScan) {
		super();
		this.id = id;
		this.sexe = sexe;
		this.nom = nom;
		this.dateDeNaissance = dateDeNaissance;
		this.taille = taille;
		this.poids = poids;
		this.caracteristiques = caracteristiques;
		this.etat = etat;
		this.famille = famille;
		this.latitude = latitude;
		this.longitude = longitude;
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.dernierScan = dernierScan;
	}

	public LemurienModel() {
	}

	public LemurienModel(int id) {
		super();
		this.id = id;
	}

	public LemurienModel(LemurienEntity lemurienE) {
		this.id = lemurienE.getId();
		this.sexe = lemurienE.isSexe();
		this.nom = lemurienE.getNom();
		this.dateDeNaissance = lemurienE.getDateDeNaissance();
		this.taille = lemurienE.getTaille();
		this.poids = lemurienE.getPoids();
		this.caracteristiques = lemurienE.getCaracteristiques();
		this.etat = lemurienE.getEtat();
		this.famille = lemurienE.getFamille();
		this.latitude = lemurienE.getLatitude();
		this.longitude = lemurienE.getLongitude();
		this.parent1 = lemurienE.getParent1();
		this.parent2 = lemurienE.getParent2();
		this.dernierScan = lemurienE.getDernierScan();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSexe() {
		return sexe;
	}

	public void setSexe(boolean sexe) {
		this.sexe = sexe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public String getCaracteristiques() {
		return caracteristiques;
	}

	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques = caracteristiques;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getParent1() {
		return parent1;
	}

	public void setParent1(int parent1) {
		this.parent1 = parent1;
	}

	public int getParent2() {
		return parent2;
	}

	public void setParent2(int parent2) {
		this.parent2 = parent2;
	}

	public LocalDate getDernierScan() {
		return dernierScan;
	}

	public void setDernierScan(LocalDate dernierScan) {
		this.dernierScan = dernierScan;
	}

	public String toJSON() {
		StringBuilder s = new StringBuilder();
		s.append("{ \"id\" : " + id + ", ").append("\"nom\" : \"" + nom + "\", ").append("\"sexe\" : " + sexe + ", ")
				.append("\"dateDeNaissance\" : \"" + dateDeNaissance + "\", ").append("\"taille\" : " + taille + ", ")
				.append("\"poids\" : " + poids + ", ").append("\"caracteristiques\" : \"" + caracteristiques + "\", ")
				.append("\"etat\" : \"" + etat + "\", ").append("\"famille\" : \"" + famille + "\", ")
				.append("\"latitude\" : " + latitude + ", ").append("\"longitude\" : " + longitude + ", ")
				.append("\"parent1\" : " + parent1 + ", ").append("\"parent2\" : " + parent2 + ", ")
				.append("\"dernierScan\" : \"" + dernierScan + "\"}");

		return s.toString();
	}

}

package model;

import java.time.LocalDate;

public class Lemurien {

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

	public Lemurien(int id, boolean sexe, String nom, LocalDate dateDeNaissance, int taille, double poids,
			String caracteristiques, String etat, String famille, double latitude, double longitude, int parent1,
			int parent2) {
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

}

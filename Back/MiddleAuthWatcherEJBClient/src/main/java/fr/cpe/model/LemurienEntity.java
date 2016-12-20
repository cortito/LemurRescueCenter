package fr.cpe.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.jboss.arquillian.junit.InSequence;

@Entity
@Table(name = "Lemurien")
public class LemurienEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2686907338544888162L;

	@Id
	@NotNull
	@Column
	private int id;
	@Column
	private boolean sexe;
	@Column
	private String nom;
	@Column
	private LocalDate dateDeNaissance;
	@Column
	private int taille;
	@Column
	private double poids;
	@Column
	private String caracteristiques;
	@Column
	private String etat;
	@Column
	private String famille;
	@Column
	private double latitude;
	@Column
	private double longitude;
	@Null
	@Column
	private int parent1;
	@Null
	@Column
	private int parent2;
	@Column
	private LocalDate dernierScan;

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

}

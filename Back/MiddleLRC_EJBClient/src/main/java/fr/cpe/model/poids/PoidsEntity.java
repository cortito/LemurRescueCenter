package fr.cpe.model.poids;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Poids_Lemurien")
public class PoidsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3407837063151460070L;

	@Id
	@NotNull
	@Column
	private int idDB;
	@Column
	private String nom;
	@Column
	private String date;
	@Column
	private Double poids;

	public PoidsEntity(int idDB, String nom, String date, Double poids) {
		this.idDB = idDB;
		this.nom = nom;
		this.date = date;
		this.poids = poids;
	}

	public PoidsEntity() {
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

}

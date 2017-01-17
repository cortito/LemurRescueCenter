package fr.lrc.model.poids;

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
	private String poids;

	public PoidsEntity() {
	}

	public PoidsEntity(int idDB, String nom, String date, String poids) {
		this.idDB = idDB;
		this.nom = nom;
		this.date = date;
		this.poids = poids;
	}

	public PoidsEntity(PoidsModel poidsM, String paramNull) {
		if (!paramNull.equals(("id"))) {
			this.idDB = poidsM.getIdDB();
		}
		this.nom = poidsM.getNom();
		this.date = poidsM.getDate();
		this.poids = poidsM.getPoids();
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

	public String getPoids() {
		return poids;
	}

	public void setPoids(String poids) {
		this.poids = poids;
	}

}

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
@Table(name = "E_S_Lemurien")
public class LemurienEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2686907338544888162L;

	@Id
	@NotNull
	@Column
	private int idDB;
	@Column
	private String nom;
	@Column
	private String numeroIdentification;
	@Column
	private String sexe;
	@Column
	private String dateDeNaissance;
	@Column
	private String dateEntree;
	@Column
	private String origine;
	@Column
	private String natureEntree;
	@Column
	private String ancienProprietaire;
	@Column
	private String dateSortie;
	@Column
	private String natureSortie;
	@Column
	private String commentaireSortie;

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

	public String getNumeroIdentification() {
		return numeroIdentification;
	}

	public void setNumeroIdentification(String numeroIdentification) {
		this.numeroIdentification = numeroIdentification;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(String dateEntree) {
		this.dateEntree = dateEntree;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getNatureEntree() {
		return natureEntree;
	}

	public void setNatureEntree(String natureEntree) {
		this.natureEntree = natureEntree;
	}

	public String getAncienProprietaire() {
		return ancienProprietaire;
	}

	public void setAncienProprietaire(String ancienProprietaire) {
		this.ancienProprietaire = ancienProprietaire;
	}

	public String getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}

	public String getNatureSortie() {
		return natureSortie;
	}

	public void setNatureSortie(String natureSortie) {
		this.natureSortie = natureSortie;
	}

	public String getCommentaireSortie() {
		return commentaireSortie;
	}

	public void setCommentaireSortie(String commentaireSortie) {
		this.commentaireSortie = commentaireSortie;
	}

}

package fr.lrc.model.lemurien;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	private String nom = "";
	@Column
	private String numeroIdentification = "";
	@Column
	private String sexe = "";
	@Column
	private String dateDeNaissance = "";
	@Column
	private String dateEntree = "";
	@Column
	private String origine = "";
	@Column
	private String natureEntree = "";
	@Column
	private String ancienProprietaire = "";
	@Column
	private String dateSortie = "";
	@Column
	private String natureSortie = "";
	@Column
	private String commentaireSortie = "";

	public LemurienEntity() {
	}

	public LemurienEntity(LemurienModel lemurienM, String paramNull) {
		if (!paramNull.equals(("id"))) {
			this.idDB = lemurienM.getIdDB();
		}
		this.nom = controleNotNull(lemurienM.getNom());
		this.numeroIdentification = controleNotNull(lemurienM.getNumeroIdentification());
		this.sexe = controleNotNull(lemurienM.getSexe());
		this.dateDeNaissance = controleNotNull(lemurienM.getDateDeNaissance());
		this.dateEntree = controleNotNull(lemurienM.getDateEntree());
		this.origine = controleNotNull(lemurienM.getOrigine());
		this.natureEntree = controleNotNull(lemurienM.getNatureEntree());
		this.ancienProprietaire = controleNotNull(lemurienM.getAncienProprietaire());
		this.dateSortie = controleNotNull(lemurienM.getDateSortie());
		this.natureSortie = controleNotNull(lemurienM.getNatureSortie());
		this.commentaireSortie = controleNotNull(lemurienM.getCommentaireSortie());
	}

	public LemurienEntity(LemurienModel lemurienM) {
		this.idDB = lemurienM.getIdDB();
		this.nom = controleNotNull(lemurienM.getNom());
		this.numeroIdentification = controleNotNull(lemurienM.getNumeroIdentification());
		this.sexe = controleNotNull(lemurienM.getSexe());
		this.dateDeNaissance = controleNotNull(lemurienM.getDateDeNaissance());
		this.dateEntree = controleNotNull(lemurienM.getDateEntree());
		this.origine = controleNotNull(lemurienM.getOrigine());
		this.natureEntree = controleNotNull(lemurienM.getNatureEntree());
		this.ancienProprietaire = controleNotNull(lemurienM.getAncienProprietaire());
		this.dateSortie = controleNotNull(lemurienM.getDateSortie());
		this.natureSortie = controleNotNull(lemurienM.getNatureSortie());
		this.commentaireSortie = controleNotNull(lemurienM.getCommentaireSortie());
	}

	public LemurienEntity(LemurienEntity lemurienE, LemurienModel lemurienM) {
		this.idDB = lemurienE.getIdDB();
		this.nom = mergeData(lemurienE.getNom(), lemurienM.getNom());
		this.numeroIdentification = mergeData(lemurienE.getNumeroIdentification(), lemurienM.getNumeroIdentification());
		this.sexe = mergeData(lemurienE.getSexe(), lemurienM.getSexe());
		this.dateDeNaissance = mergeData(lemurienE.getDateDeNaissance(), lemurienM.getDateDeNaissance());
		this.dateEntree = mergeData(lemurienE.getDateEntree(), lemurienM.getDateEntree());
		this.origine = mergeData(lemurienE.getOrigine(), lemurienM.getOrigine());
		this.natureEntree = mergeData(lemurienE.getNatureEntree(), lemurienM.getNatureEntree());
		this.ancienProprietaire = mergeData(lemurienE.getAncienProprietaire(), lemurienM.getAncienProprietaire());
		this.dateSortie = mergeData(lemurienE.getDateSortie(), lemurienM.getDateSortie());
		this.natureSortie = mergeData(lemurienE.getNatureSortie(), lemurienM.getNatureSortie());
		this.commentaireSortie = mergeData(lemurienE.getCommentaireSortie(), lemurienM.getCommentaireSortie());
	}

	private String mergeData(String defaut, String merge) {
		return merge;
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

	private String controleNotNull(String s) {
		return s == null ? "" : s;
	}

	@Override
	public String toString() {
		return "LemurienEntity [idDB=" + idDB + ", nom=" + nom + ", numeroIdentification=" + numeroIdentification
				+ ", sexe=" + sexe + ", dateDeNaissance=" + dateDeNaissance + ", dateEntree=" + dateEntree
				+ ", origine=" + origine + ", natureEntree=" + natureEntree + ", ancienProprietaire="
				+ ancienProprietaire + ", dateSortie=" + dateSortie + ", natureSortie=" + natureSortie
				+ ", commentaireSortie=" + commentaireSortie + "]";
	}

}

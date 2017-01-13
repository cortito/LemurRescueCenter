package fr.lrc.model.lemurien;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LemurienModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5698754191407303366L;

	private int idDB;
	private String nom = "";
	private String numeroIdentification = "";
	private String sexe = "";
	private String dateDeNaissance = "";
	private String dateEntree = "";
	private String origine = "";
	private String natureEntree = "";
	private String ancienProprietaire = "";
	private String dateSortie = "";
	private String natureSortie = "";
	private String commentaireSortie = "";

	public LemurienModel(int idDB, String nom, String numeroIdentification, String sexe, String dateDeNaissance,
			String dateEntree, String origine, String natureEntree, String ancienProprietaire, String dateSortie,
			String natureSortie, String commentaireSortie) {
		this.idDB = idDB;
		this.nom = nom;
		this.numeroIdentification = numeroIdentification;
		this.sexe = sexe;
		this.dateDeNaissance = dateDeNaissance;
		this.dateEntree = dateEntree;
		this.origine = origine;
		this.natureEntree = natureEntree;
		this.ancienProprietaire = ancienProprietaire;
		this.dateSortie = dateSortie;
		this.natureSortie = natureSortie;
		this.commentaireSortie = commentaireSortie;
	}

	public LemurienModel() {
	}

	public LemurienModel(int idDB) {
		this.idDB = idDB;
	}

	public LemurienModel(LemurienEntity lemurienE) {
		this.idDB = lemurienE.getIdDB();
		this.nom = controleNotNull(lemurienE.getNom());
		this.numeroIdentification = controleNotNull(lemurienE.getNumeroIdentification());
		this.sexe = controleNotNull(lemurienE.getSexe());
		this.dateDeNaissance = controleNotNull(lemurienE.getDateDeNaissance());
		this.dateEntree = controleNotNull(lemurienE.getDateEntree());
		this.origine = controleNotNull(lemurienE.getOrigine());
		this.natureEntree = controleNotNull(lemurienE.getNatureEntree());
		this.ancienProprietaire = controleNotNull(lemurienE.getAncienProprietaire());
		this.dateSortie = controleNotNull(lemurienE.getDateSortie());
		this.natureSortie = controleNotNull(lemurienE.getNatureSortie());
		this.commentaireSortie = controleNotNull(lemurienE.getCommentaireSortie());

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

	public String toJSON() {
		StringBuilder s = new StringBuilder();
		s.append("{ \"idDB\" : " + idDB + ", ").append("\"nom\" : \"" + nom + "\", ")
				.append("\"numeroIdentification\" : \"" + numeroIdentification + "\", ")
				.append("\"sexe\" : \"" + sexe + "\", ").append("\"dateDeNaissance\" : \"" + dateDeNaissance + "\", ")
				.append("\"dateEntree\" : \"" + dateEntree + "\", ").append("\"origine\" : \"" + origine + "\", ")
				.append("\"natureEntree\" : \"" + natureEntree + "\", ")
				.append("\"ancienProprietaire\" : \"" + ancienProprietaire + "\", ")
				.append("\"dateSortie\" : \"" + dateSortie + "\", ")
				.append("\"natureSortie\" : \"" + natureSortie + "\", ")
				.append("\"commentaireSortie\" : \"" + commentaireSortie + "\"}");

		return s.toString();
	}

	private String controleNotNull(String s) {
		return s == null ? "" : s;
	}

	@Override
	public String toString() {
		return toJSON();
	}

	public Map<String, String> getAllParameters() {
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put("Nom", this.nom);
		mapParam.put("Numero identification", this.numeroIdentification);
		mapParam.put("Sexe", this.sexe);
		mapParam.put("Date de naissance", this.dateDeNaissance);
		mapParam.put("Date entrée", this.dateEntree);
		mapParam.put("Origine", this.origine);
		mapParam.put("Nature entrée", this.natureEntree);
		mapParam.put("Ancien proprietaire", this.ancienProprietaire);
		mapParam.put("Date sortie", this.dateSortie);
		mapParam.put("Nature sortie", this.natureSortie);
		mapParam.put("Commentaire sortie", this.commentaireSortie);

		return mapParam;
	}

}

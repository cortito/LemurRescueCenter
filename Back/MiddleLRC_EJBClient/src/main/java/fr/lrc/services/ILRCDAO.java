package fr.lrc.services;

import java.util.List;

import javax.ejb.Local;

import fr.lrc.model.lemurien.LemurienEntity;
import fr.lrc.model.lemurien.LemurienModel;
import fr.lrc.model.poids.PoidsEntity;
import fr.lrc.model.poids.PoidsModel;

@Local
public interface ILRCDAO {

	public List<LemurienEntity> getAllLemurien();

	public LemurienEntity getLemurienById(int id);

	public LemurienEntity getLemurienByName(String nom);

	public String addLemurien(LemurienModel lemurienM);

	public List<PoidsEntity> getPoidsByName(String nom);

	public PoidsEntity getPoidsByNameAndDate(String nom, String date);

	public String updateLemurien(LemurienModel lemurienM);

	public String deleteLemurien(LemurienModel lemurienM);

	public String addPoids(PoidsEntity poidsE);

	public PoidsEntity getPoidsById(int id);

	public String deletePoids(PoidsModel poidsM);

}

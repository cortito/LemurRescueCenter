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

	public List<PoidsEntity> getPoidsByName(String nom);

	public LemurienEntity addLemurien(LemurienModel lemurienM);
	
	public LemurienEntity updateLemurien(LemurienModel lemurienM);

	public boolean deleteLemurien(LemurienModel lemurienM);

	public PoidsEntity addPoids(PoidsModel poidsM);

	public PoidsEntity getPoidsById(int id);

}

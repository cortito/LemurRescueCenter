package fr.cpe.services;

import java.util.List;

import javax.ejb.Local;

import fr.cpe.model.lemurien.LemurienEntity;
import fr.cpe.model.poids.PoidsEntity;

@Local
public interface ILRCDAO {

	public List<LemurienEntity> getAllLemurien();

	public LemurienEntity getLemurienById(int id);

	public List<PoidsEntity> getPoidsByName(String nom);
}

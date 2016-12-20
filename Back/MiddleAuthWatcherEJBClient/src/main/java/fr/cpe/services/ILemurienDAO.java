package fr.cpe.services;

import java.util.List;

import javax.ejb.Local;

import fr.cpe.model.LemurienEntity;

@Local
public interface ILemurienDAO {
	
	public List<LemurienEntity> getAllLemurien();
	
	public LemurienEntity getLemurienById(int id);
}

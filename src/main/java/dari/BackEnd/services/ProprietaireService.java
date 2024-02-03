package dari.BackEnd.services;

import java.util.List;

import dari.BackEnd.entites.Proprietaire;

public interface ProprietaireService {

	public List<Proprietaire> getAllProps();

	public Proprietaire findPropById(int id);

	public Proprietaire createProp(Proprietaire p);

	public Proprietaire updateProp(Proprietaire p);

	public void deleteProp(int id);
	
	public List<Proprietaire> findByPrenom(String prenom);
	


}
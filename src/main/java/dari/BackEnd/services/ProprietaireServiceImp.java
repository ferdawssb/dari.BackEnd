package dari.BackEnd.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dari.BackEnd.entites.Proprietaire;
import dari.BackEnd.repostories.ProprietaireRepositorie;

@Service
public class ProprietaireServiceImp implements ProprietaireService {

	@Autowired
	private ProprietaireRepositorie proprietaireRepositorie;

	@Override
	public List<Proprietaire> getAllProps() {

		return proprietaireRepositorie.findAll();
	}

	@Override
	public Proprietaire findPropById(int id) {
		Optional<Proprietaire> proOptional = proprietaireRepositorie.findById(id);
		if (proOptional.isEmpty()) {
			return null;
		} else {
			return proOptional.get();
		}

	}

	@Override
	public Proprietaire createProp(Proprietaire p) {

		return proprietaireRepositorie.save(p);
	}

	@Override
	public Proprietaire updateProp(Proprietaire p) {
		Optional<Proprietaire> proOptional = proprietaireRepositorie.findById(p.getId());
		if (proOptional.isEmpty()) {
			return null;
		} else {
			return proprietaireRepositorie.save(p);
		}
	}

	@Override
	public void deleteProp(int id) {
		proprietaireRepositorie.deleteById(id);

	}

	@Override
	public List<Proprietaire> findByPrenom(String prenom) {
		
		return  proprietaireRepositorie.findByPrenom(prenom) ;
	}

}
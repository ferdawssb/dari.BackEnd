package dari.BackEnd.repostories;
import dari.BackEnd.*;
import dari.BackEnd.entites.Proprietaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ProprietaireRepositorie extends JpaRepository <Proprietaire, Integer>{
	
	public List<Proprietaire> findByPrenom(String prenom);
	

} 
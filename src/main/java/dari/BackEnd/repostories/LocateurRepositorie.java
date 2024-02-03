package dari.BackEnd.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import dari.BackEnd.entites.Locateur;

@Repository
public interface LocateurRepositorie extends JpaRepository<Locateur, Integer> {

}

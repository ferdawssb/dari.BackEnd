package dari.BackEnd.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dari.BackEnd.entites.Administrateur;

@Repository
public interface AdministrateurRepositorie extends JpaRepository<Administrateur, Integer> {

}

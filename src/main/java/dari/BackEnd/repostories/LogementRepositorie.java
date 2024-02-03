package dari.BackEnd.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dari.BackEnd.entites.Logement;


@Repository
public interface LogementRepositorie extends JpaRepository< Logement, Integer>{

}

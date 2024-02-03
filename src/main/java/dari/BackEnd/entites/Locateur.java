package dari.BackEnd.entites;

import java.util.List;
import jakarta.persistence.*;


@Entity
@DiscriminatorValue("LOC")
public class Locateur extends Utilisateur {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "loca")
	List<Reservation> res ;

}
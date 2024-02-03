package dari.BackEnd.entites;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;



@Entity
@DiscriminatorValue("ADMIN")
public class Administrateur extends Utilisateur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
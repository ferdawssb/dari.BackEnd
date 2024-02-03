package dari.BackEnd.entites;

import java.util.*;

import jakarta.persistence.*;


@Entity
@DiscriminatorValue("PROP")
public class Proprietaire extends Utilisateur {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "prop")
	List<Logement> logement;

}
package dari.BackEnd.entites;

import java.io.Serializable;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Logement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idlog;
	@ManyToOne
	@JoinColumn(name = "idprop")
	private Proprietaire prop;
	private String type;
	private String ville;
	private String adress;
	private float prix;
	private String description;
	String image;
	private int places;
	private boolean disponibiltée;
	
	@OneToMany(mappedBy = "log")
	List<Reservation> reservation;

	


     
	public Logement(Proprietaire prop,String type,String ville,String address, float prix,String description,int places,boolean disponibiltée) {
		this.prop = prop;
	    this.type = type;
	    this.ville = ville;
	    this.adress = address; 
	    this.prix = prix;
	    this.description = description;
	    this.places = places;
	    this.disponibiltée = disponibiltée;
		
	}
	
	public Logement() {}
	
	public List<Reservation> getReservation() {
		return reservation;
	}



	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}
	

	public int getIdlog() {
		return idlog;
	}

	public void setIdlog(int idlog) {
		this.idlog = idlog;
	}

	public Proprietaire getProp() {
		return prop;
	}

	public void setProp(Proprietaire prop) {
		this.prop = prop;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public boolean getDisponibiltée() {
		return disponibiltée;
	}

	public void setDisponibiltée(boolean disponibiltée) {
		this.disponibiltée = disponibiltée;
	}

	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
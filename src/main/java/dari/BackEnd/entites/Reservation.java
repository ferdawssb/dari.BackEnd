package dari.BackEnd.entites;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.*;


@Entity
public class Reservation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idres;

	@ManyToOne
	@JoinColumn(name = "idloc")
	private Locateur loca;

	@ManyToOne
	@JoinColumn(name = "idlog")
	private Logement log;

	private Date dateDebut;
	private Date dateFin;
	private String reponce;
	
	public Reservation() {
		super();
	}

	public int getId() {
		return idres;
	}

	public void setId(int id) {
		this.idres = id;
	}

	public Locateur getLoca() {
		return loca;
	}

	public void setLoca(Locateur loca) {
		this.loca = loca;
	}

	public Logement getLog() {
		return log;
	}

	public void setLog(Logement log) {
		this.log = log;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getReponce() {
		return reponce;
	}

	public void setReponce(String reponce) {
		this.reponce = reponce;
	}

	
	

}
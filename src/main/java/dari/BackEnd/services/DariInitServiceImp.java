package dari.BackEnd.services;


import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dari.BackEnd.entites.Administrateur;
import dari.BackEnd.entites.Locateur;
import dari.BackEnd.entites.Logement;
import dari.BackEnd.entites.Proprietaire;
import dari.BackEnd.entites.Reservation;
import dari.BackEnd.repostories.AdministrateurRepositorie;
import dari.BackEnd.repostories.LocateurRepositorie;
import dari.BackEnd.repostories.LogementRepositorie;
import dari.BackEnd.repostories.ProprietaireRepositorie;
import dari.BackEnd.repostories.ReservationRepositorie;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DariInitServiceImp implements IDariInitService {

	@Autowired
	private ProprietaireRepositorie proprietaireRepositorie;
	@Autowired
	private LocateurRepositorie locateurRepositorie;
	@Autowired
	private AdministrateurRepositorie administrateurRepositorie;
	@Autowired
	private LogementRepositorie logementRepositorie;
	@Autowired
	private ReservationRepositorie reservationRepositorie;

	@Override
	public void initLocateurs() {
		Stream.of("Marwa", "Maryem", "Molka").forEach(nomLoc -> {
			Locateur loc = new Locateur();
			loc.setPrenom(nomLoc);
			locateurRepositorie.save(loc);

		});

	}

	@Override
	public void initProps() {
		Stream.of("Ali", "Salah", "Mounir").forEach(nomProp -> {
			Proprietaire prop = new Proprietaire();
			prop.setPrenom(nomProp);
			proprietaireRepositorie.save(prop);

		});

	}

	@Override
	public void initAdmins() {
		Stream.of("Khalil", "Salah", "Ali").forEach(nomAdm -> {
			Administrateur adm = new Administrateur();
			adm.setPrenom(nomAdm);
			administrateurRepositorie.save(adm);

		});
	}

	@Override
	public void initLogements() {

		List<Proprietaire> lp = proprietaireRepositorie.findAll();
		Stream.of("Maison", "Appartement", "Villa").forEach(typeLog -> {
			Logement log = new Logement();
			log.setType(typeLog);
			log.setPlaces(3 + (int) (Math.random() * 7));
			log.setPrix(200 + (float) Math.random() * 400);
			log.setImage("maison");
			log.setProp(lp.get(new Random().nextInt(lp.size())));
			logementRepositorie.save(log);

		});

	}

	@Override
	public void initReservs() {
		        List<Locateur> lloc = locateurRepositorie.findAll();
		        List<Logement> llog = logementRepositorie.findAll();

		        
				Stream.of("Valide", "Non Valide", "Valide").forEach(reponce -> {
					Reservation res = new Reservation();
					res.setReponce(reponce);
					res.setLoca(lloc.get(new Random().nextInt(lloc.size())));
					res.setLog(llog.get(new Random().nextInt(llog.size())));
					reservationRepositorie.save(res);

				

		});

	}

}

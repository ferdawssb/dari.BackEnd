package dari.BackEnd.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dari.BackEnd.entites.Locateur;
import dari.BackEnd.entites.Logement;
import dari.BackEnd.entites.Reservation;
import dari.BackEnd.repostories.LogementRepositorie;
import dari.BackEnd.repostories.ReservationRepositorie;
@Service
public class LogementServiceImp implements LogementService {

	@Autowired
	private LogementRepositorie logementRepositorie;
	@Autowired
	private ReservationRepositorie reservationRepositorie;

	public List<Logement> getAllLog() {
		
		return logementRepositorie.findAll();
	}

	public Logement findLogById(int id) {
		Optional<Logement> logOptional = logementRepositorie.findById(id);
		if (logOptional.isEmpty()) {
			return null;
		} else {
			return logOptional.get();
		}
	}

	public Logement createLog(Logement l) {

		return logementRepositorie.save(l);
	}

	public Logement updateLog(Logement l) {
		Optional<Logement> logOptional = logementRepositorie.findById(l.getIdlog());
		if (logOptional.isEmpty()) {
			return null;
		} else {
			return logementRepositorie.save(l);
		}
	}

	public void deleteLog(int id) {
		logementRepositorie.deleteById(id);

	}
	


}

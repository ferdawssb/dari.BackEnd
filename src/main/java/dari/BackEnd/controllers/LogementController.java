package dari.BackEnd.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dari.BackEnd.entites.Locateur;
import dari.BackEnd.entites.Logement;
import dari.BackEnd.entites.Reservation;
import dari.BackEnd.repostories.LogementRepositorie;
import dari.BackEnd.repostories.ReservationRepositorie;
import dari.BackEnd.services.*;

@RestController
@RequestMapping("/logement") // localhost:8080/logement
@CrossOrigin("*")//marcher pas avec spring sucirity
public class LogementController {

	@Autowired
	private LogementRepositorie logementRepositorie;
	@Autowired
	private LogementService logementService;
	@Autowired
	ReservationRepositorie reservationRepositorie;

	@GetMapping("/get")
	public List<Logement> getAllLoge() {
		return logementService.getAllLog();

	}

	@GetMapping(path = "/{id}")
	@PreAuthorize("hasAuthority('SCOPE_LOC')")
	public ResponseEntity<Logement> findLogeById(@PathVariable int id) {

		Logement l = logementService.findLogById(id);
		if (l == null) {
			return new ResponseEntity<Logement>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Logement>(l, HttpStatus.OK);
		}

	}
	
	    
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('SCOPE_LOC')")
	public ResponseEntity<Logement> createLog(@RequestBody  Logement l, @RequestParam("file") MultipartFile file) {
	    try {
	        // Vérifiez si un fichier a été téléchargé
	        if (!file.isEmpty()) {
	            String fileName = UUID.randomUUID().toString(); // Utilisez un nom de fichier unique
	            Files.copy(file.getInputStream(), Paths.get(System.getProperty("user.home"), "/dari/images/", fileName));
	            l.setImage(fileName);
	        }

	        Logement createdLogement = logementService.createLog(l);

	        if (createdLogement != null) {
	            return new ResponseEntity<>(createdLogement, HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@PutMapping("/update") 
	@PreAuthorize("hasAuthority('SCOPE_PROP')")
	public Logement updateLoge(@RequestBody Logement l) {

		return logementService.updateLog(l);

	}

	@DeleteMapping(path = "/{id}")
	public void deleteLoge(@PathVariable int id) {

		logementService.deleteLog(id);
	}
	
	

	@GetMapping(path = "/imageLogement/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageByIdLog(@PathVariable(name = "id") int id) throws Exception {
		Logement l = logementRepositorie.findById(id).orElse(null);

		if (l != null && l.getImage() != null) {
			String photoName = l.getImage();
			try {
				File file = new File(System.getProperty("user.home") + "/dari/images/" + photoName + ".jpg");
				Path path = Paths.get(file.toURI());
				byte[] imageData = Files.readAllBytes(path);

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.IMAGE_JPEG);
				return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
			} catch (IOException e) {
				e.printStackTrace();
				// Gérer l'erreur, par exemple, renvoyer une réponse appropriée
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			// Gérer le cas où l'objet Logement est null ou son image est null
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}

	

	
	

	
	

}

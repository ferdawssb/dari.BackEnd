package dari.BackEnd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dari.BackEnd.entites.Proprietaire;
import dari.BackEnd.services.ProprietaireService;

@RestController
@RequestMapping("/proprietaire") // localhost:8080/proprietaire
public class ProprietaireController {
	@Autowired
	private ProprietaireService proprietaireService;

	@GetMapping("/get")
	public List<Proprietaire> getAllProp() {

		return proprietaireService.getAllProps();

	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Proprietaire> findPropById(@PathVariable int id) {

		Proprietaire p = proprietaireService.findPropById(id);
		if (p == null) {
			return new ResponseEntity<Proprietaire>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Proprietaire>(p, HttpStatus.OK);
		}

	}

	@PostMapping("/add")
	public Proprietaire createProp(@RequestBody Proprietaire p) {

		return proprietaireService.createProp(p);
	}

	@PutMapping("/update")
	public Proprietaire updateProp(@RequestBody Proprietaire p) {

		return proprietaireService.updateProp(p);

	}

	@DeleteMapping(path = "/{id}")
	public void deleteProp(@PathVariable int id) {

		proprietaireService.deleteProp(id);
	}

	@GetMapping(path = "/findByPrenom/{prenom}")
	public ResponseEntity<List<Proprietaire>> findPropByPrenom(@PathVariable String prenom) {

		List<Proprietaire> lp = proprietaireService.findByPrenom(prenom);
		if (lp == null) {
			return new ResponseEntity<List<Proprietaire>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Proprietaire>>(lp, HttpStatus.OK);
		}

	}

}
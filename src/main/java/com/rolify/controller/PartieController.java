package com.rolify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.rolify.dao.PartieDao;
import com.rolify.dao.UtilisateurDAO;
import com.rolify.entity.Partie;
import com.rolify.entity.Utilisateur;
import com.rolify.entity.Views;

@CrossOrigin
@RestController
public class PartieController {

	@Autowired
	PartieDao partieDao;

	@Autowired
	UtilisateurDAO utilDao;
	
	@JsonView(Views.PartieWithAll.class)
	@GetMapping("/api/parties")
	public ResponseEntity<List<Partie>> findAll() {
		List<Partie> woners = partieDao.findAll();
		
		return new ResponseEntity<List<Partie>>(woners, HttpStatus.OK);
	}
	
	@GetMapping("/api/parties/{id}")
	@JsonView(Views.PartieWithAll.class)
	public ResponseEntity<Partie> findOne(@PathVariable("id") Integer id) {
		Partie ownr = partieDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Partie>(ownr, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/parties/{id}")
	@JsonView(Views.PartieWithAll.class)
	public ResponseEntity<Partie> delete(@PathVariable("id") Integer id) {
		Partie ownr = partieDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		partieDao.delete(ownr);
		return new ResponseEntity<Partie>(ownr, HttpStatus.OK);
	}
	
	@PostMapping("/api/parties")
	@JsonView(Views.PartieWithAll.class)
	public ResponseEntity<Partie> create(@RequestBody Partie user) {
		
		if (user.getId() > 0) {
			return new ResponseEntity<Partie>(user, HttpStatus.BAD_REQUEST);
		}
		
		partieDao.save(user);
		
		return new ResponseEntity<Partie>(user, HttpStatus.OK);
		
	}
	
	@PutMapping("/api/parties")
	@JsonView(Views.PartieWithAll.class)
	public ResponseEntity<Partie> update(@RequestBody Partie user) {
		Partie ch = partieDao.findByPrimaryKey(user.getId());		
		if (ch == null) {
			ch = partieDao.save(user);
		} else {
			ch = partieDao.update(user);
		}		
		return new ResponseEntity<Partie>(ch, HttpStatus.OK);
	}
	
	@PutMapping("/api/parties/{id_partie}/setMj/{id_mj}")
	@JsonView(Views.PartieWithAll.class)
	public ResponseEntity<Partie> setMJ(@PathVariable("id_partie") int idPartie, @PathVariable("id_mj") String idMj) {
		Utilisateur mj = utilDao.findByPrimaryKey(idMj);
		Partie partie = partieDao.findByPrimaryKey(idPartie);
		if (mj == null || partie == null) {
			return new ResponseEntity<Partie>(partie, HttpStatus.PRECONDITION_FAILED);
		}
		// TODO : vérifier que mj n'est pas un joueur
		partie.changeMJ(mj);
		partieDao.update(partie);
		removeAssociationsMJ(mj, partie);
		partie = partieDao.findByPrimaryKey(idPartie);
		
		
		return new ResponseEntity<Partie>(partie, HttpStatus.OK);
	}
	
	private void removeAssociationsMJ(Utilisateur mj, Partie partie) {
		// TODO voir UtilisateurController.removeAssociationsJoueur
	}

}

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
import com.rolify.dao.PersonnageDAO;
import com.rolify.entity.AssociationPartieUtilisateurPersonnage;
import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Views;

@CrossOrigin
@RestController
public class PersonnageController {

	@Autowired
	PersonnageDAO personnageDao;

	@JsonView(Views.PersonnageWithAssociation.class)
	@GetMapping("/api/personnages")
	public ResponseEntity<List<Personnage>> findAll() {
		List<Personnage> woners = personnageDao.findAll();
		
		return new ResponseEntity<List<Personnage>>(woners, HttpStatus.OK);
	}

	@JsonView(Views.PersonnageWithAssociation.class)
	@GetMapping("/api/personnages/{id}")
	public ResponseEntity<Personnage> findOne(@PathVariable("id") Integer id) {
		Personnage ownr = personnageDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Personnage>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.PersonnageWithAssociation.class)
	@DeleteMapping("/api/personnages/{id}")
	public ResponseEntity<Personnage> delete(@PathVariable("id") Integer id) {
		Personnage ownr = personnageDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		personnageDao.delete(ownr);
		return new ResponseEntity<Personnage>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.PersonnageWithAssociation.class)
	@PostMapping("/api/personnages")
	public ResponseEntity<Personnage> create(@RequestBody Personnage user) {
		
		if (user.getId() > 0) {
			return new ResponseEntity<Personnage>(user, HttpStatus.BAD_REQUEST);
		}
		
		personnageDao.save(user);
		
		return new ResponseEntity<Personnage>(user, HttpStatus.OK);
		
	}

	@JsonView(Views.PersonnageWithAssociation.class)
	@PutMapping("/api/personnages")
	public ResponseEntity<Personnage> update(@RequestBody Personnage user) {
		Personnage ch = personnageDao.findByPrimaryKey(user.getId());		
		if (ch == null) {
			ch = personnageDao.save(user);
		} else {
			ch = personnageDao.update(user);
		}		
		return new ResponseEntity<Personnage>(ch, HttpStatus.OK);
	}

}

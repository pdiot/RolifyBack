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

import com.rolify.dao.UtilisateurDAO;
import com.rolify.entity.Utilisateur;

@CrossOrigin
@RestController
public class UtilisateurController {

	@Autowired
	UtilisateurDAO utilDao;
	
	@GetMapping("/api/utilisateurs")
	public ResponseEntity<List<Utilisateur>> findAll() {
		List<Utilisateur> woners = utilDao.findAll();
		return new ResponseEntity<List<Utilisateur>>(woners, HttpStatus.OK);
	}
	
	@GetMapping("/api/utilisateurs/{id}")
	public ResponseEntity<Utilisateur> findOne(@PathVariable("id") String id) {
		Utilisateur ownr = utilDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Utilisateur>(ownr, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/utilisateurs/{id}")
	public ResponseEntity<Utilisateur> delete(@PathVariable("id") String id) {
		Utilisateur ownr = utilDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		utilDao.delete(ownr);
		return new ResponseEntity<Utilisateur>(ownr, HttpStatus.OK);
	}
	
	@PostMapping("/api/utilisateurs")
	public ResponseEntity<Utilisateur> create(@RequestBody Utilisateur user) {
		
		if (!user.getId().equals("")) {
			return new ResponseEntity<Utilisateur>(user, HttpStatus.BAD_REQUEST);
		}
		
		utilDao.save(user);
		
		return new ResponseEntity<Utilisateur>(user, HttpStatus.OK);
		
	}
	
	@PutMapping("/api/utilisateurs")
	public ResponseEntity<Utilisateur> update(@RequestBody Utilisateur user) {
		Utilisateur ch = utilDao.findByPrimaryKey(user.getId());		
		if (ch == null) {
			ch = utilDao.save(user);
		} else {
			ch = utilDao.update(user);
		}		
		return new ResponseEntity<Utilisateur>(ch, HttpStatus.OK);
	}

}

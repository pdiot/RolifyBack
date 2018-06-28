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
import com.rolify.chat.GroupeDiscussion;
import com.rolify.dao.GroupeDiscussionDAO;
import com.rolify.dao.UtilisateurDAO;
import com.rolify.entity.AssociationPartieUtilisateurPersonnage;
import com.rolify.entity.Utilisateur;
import com.rolify.entity.Views;

@CrossOrigin
@RestController
public class UtilisateurController {

	@Autowired
	UtilisateurDAO utilDao;
	@Autowired
	GroupeDiscussionDAO groupeDao;

	@JsonView(Views.UtilisateurWithAll.class)
	@GetMapping("/api/utilisateurs")
	public ResponseEntity<List<Utilisateur>> findAll() {
		List<Utilisateur> woners = utilDao.findAll();
		return new ResponseEntity<List<Utilisateur>>(woners, HttpStatus.OK);
	}

	@JsonView(Views.UtilisateurWithAll.class)
	@GetMapping("/api/utilisateurs/{id}")
	public ResponseEntity<Utilisateur> findOne(@PathVariable("id") String id) {
		Utilisateur ownr = utilDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Utilisateur>(ownr, HttpStatus.OK);
	}
	
	@JsonView(Views.Utilisateur.class)
	@GetMapping("/api/utilisateurs/common/{id}")
	public ResponseEntity<Utilisateur> findOneCommon(@PathVariable("id") String id) {
		Utilisateur ownr = utilDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Utilisateur>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.UtilisateurWithAll.class)
	@DeleteMapping("/api/utilisateurs/{id}")
	public ResponseEntity<Utilisateur> delete(@PathVariable("id") String id) {
		Utilisateur ownr = utilDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		utilDao.delete(ownr);
		return new ResponseEntity<Utilisateur>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.UtilisateurWithAll.class)
	@PostMapping("/api/utilisateurs")
	public ResponseEntity<Utilisateur> create(@RequestBody Utilisateur user) {
		
		if (utilDao.findByPrimaryKey(user.getId()) != null) {
			return new ResponseEntity<Utilisateur>(user, HttpStatus.BAD_REQUEST);
		}
		utilDao.save(user);
		
		return new ResponseEntity<Utilisateur>(user, HttpStatus.OK);
		
	}

	@JsonView(Views.UtilisateurWithAll.class)
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

	@JsonView(Views.UtilisateurWithAll.class)
	@PutMapping("/api/utilisateurs/{id}/discussions/join/{idgroupe}")
	public ResponseEntity<Utilisateur> joinGroupeDiscussion(@PathVariable("id") String idUtil, @PathVariable("idgroupe") int idGroupe) {
		Utilisateur util = utilDao.findByPrimaryKey(idUtil);
		GroupeDiscussion groupe = groupeDao.findByPrimaryKey(idGroupe);
		if (util == null || groupe == null) {
			return new ResponseEntity<Utilisateur>(util, HttpStatus.PRECONDITION_FAILED);
		}
		util.joinDiscussion(groupe);
		groupeDao.update(groupe);
		utilDao.update(util);
		util = utilDao.findByPrimaryKey(idUtil);
		return new ResponseEntity<Utilisateur>(util, HttpStatus.OK);
	}
	
	public ResponseEntity<Utilisateur> leaveGroupeDiscussion() {
		
		// TODO
		
		return null;
	}
	
	public ResponseEntity<Utilisateur> joinPartieJoueur() {
		
		// TODO
		
		return null;
	}
	
	public ResponseEntity<Utilisateur> leavePartieJoueur() {
		
		// TODO
		
		return null;
	}

	
	
}

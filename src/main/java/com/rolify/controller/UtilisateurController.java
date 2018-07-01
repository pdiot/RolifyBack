package com.rolify.controller;

import java.util.ArrayList;
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
import com.rolify.dao.AssociationPartieUtilisateurPersonnageDAO;
import com.rolify.dao.GroupeDiscussionDAO;
import com.rolify.dao.PartieDao;
import com.rolify.dao.UtilisateurDAO;
import com.rolify.entity.AssociationPartieUtilisateurPersonnage;
import com.rolify.entity.Partie;
import com.rolify.entity.Utilisateur;
import com.rolify.entity.Views;

@CrossOrigin
@RestController
public class UtilisateurController {

	@Autowired
	UtilisateurDAO utilDao;
	@Autowired
	GroupeDiscussionDAO groupeDao;
	@Autowired
	PartieDao partieDao;
	@Autowired
	AssociationPartieUtilisateurPersonnageDAO assoDao;

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
	
	@JsonView(Views.UtilisateurWithAll.class)
	@PutMapping("/api/utilisateurs/{id}/parties/join/{idpartie}")
	public ResponseEntity<Utilisateur> joinPartieJoueur(@PathVariable("id") String idUtil, @PathVariable("idpartie") int idPartie) {
		Utilisateur joueur = utilDao.findByPrimaryKey(idUtil);
		Partie partie = partieDao.findByPrimaryKey(idPartie);
		if (joueur == null || partie == null) {
			return new ResponseEntity<Utilisateur>(joueur, HttpStatus.PRECONDITION_FAILED);
		}
		
		// TODO : métier pour vérifier que le joueur n'est pas déjà dans la partie en tant que joueur ou MJ
		//			et que le nombre maximal de joueurs n'est pas atteint
		
		joueur.joinPartieJoueur(partie);
		utilDao.update(joueur);
		joueur = utilDao.findByPrimaryKey(idUtil);
		return new ResponseEntity<Utilisateur>(joueur, HttpStatus.OK);
		
	}
	
	@JsonView(Views.UtilisateurWithAll.class)
	@PutMapping("/api/utilisateurs/{idutil}/parties/leave/{idpartie}")
	public ResponseEntity<Utilisateur> leavePartieJoueur(@PathVariable("idutil") String idUtil, @PathVariable("idpartie") int idPartie) {
		Utilisateur joueur = utilDao.findByPrimaryKey(idUtil);
		Partie partie = partieDao.findByPrimaryKey(idPartie);
		
		if (joueur == null || partie == null) {
			return new ResponseEntity<Utilisateur>(joueur, HttpStatus.PRECONDITION_FAILED);
		}
		boolean isInPartie = false;
		for (Utilisateur util : partie.getJoueurs()) {
			if (util.getId().equals(idUtil)) {
				isInPartie = true;
			}
		}
		
		if (!isInPartie) { // Si le joueur passé en url ne fait pas partie des joueurs de la partie
			return new ResponseEntity<Utilisateur>(joueur, HttpStatus.PRECONDITION_REQUIRED);
		}
		
		joueur.leavePartieJoueur(partie);
		partieDao.update(partie);
		utilDao.update(joueur);
		// On supprime les associations entre ce joueur cette partie et son personnage
		removeAssociationsJoueur(joueur, partie);
		joueur = utilDao.findByPrimaryKey(idUtil);
		return new ResponseEntity<Utilisateur>(joueur, HttpStatus.OK);
	}
	
	private void removeAssociationsJoueur(Utilisateur joueur, Partie partie) {
		List<AssociationPartieUtilisateurPersonnage> assos = assoDao.findByJoueurPartie(joueur, partie);
		for (AssociationPartieUtilisateurPersonnage assoToRemove : assos) {
			assoDao.delete(assoToRemove);
		}
	}
	
	@JsonView(Views.UtilisateurWithAll.class)
	@GetMapping("/api/utilisateurs/partie/{idpartie}")
	public ResponseEntity<List<Utilisateur>> findJoueursByPartie(@PathVariable("idpartie") int idPartie) {
		Partie partie = partieDao.findByPrimaryKey(idPartie);
		List<Utilisateur> list = new ArrayList<Utilisateur>();

		if (partie == null) {
			list = null;
			return new ResponseEntity<List<Utilisateur>>(list, HttpStatus.PRECONDITION_FAILED);
		}
		list = utilDao.findJoueursByPartie(partie);
		return new ResponseEntity<List<Utilisateur>>(list, HttpStatus.OK);		
	}
	
	@JsonView(Views.UtilisateurWithAll.class)
	@GetMapping("/api/utilisateurs/partie/mjDispos/{idpartie}")
	public ResponseEntity<List<Utilisateur>> findMjDispos(@PathVariable("idpartie") int idPartie) {
		Partie partie = partieDao.findByPrimaryKey(idPartie);
		List<Utilisateur> list = new ArrayList<Utilisateur>();

		if (partie == null) {
			list = null;
			return new ResponseEntity<List<Utilisateur>>(list, HttpStatus.PRECONDITION_FAILED);
		}
		
		List<Utilisateur> joueurs = utilDao.findJoueursByPartie(partie);
		List<Utilisateur> all = utilDao.findAll();
		Utilisateur mj = partie.getMj();
		
		for (Utilisateur util : all) {
			boolean attrib = false;
			for (Utilisateur joueur : joueurs) {
				if (util.getId().equals(joueur.getId())) {
					attrib = true;
				}
			}
			if (util.getId().equals(mj.getId())) {
				attrib = true;
			}
			if (!attrib) {
				list.add(util);
			}
		}
		
		return new ResponseEntity<List<Utilisateur>>(list, HttpStatus.OK);		
	}
	
	
	
	

	
	
}

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
import com.rolify.dao.AssociationPartieUtilisateurPersonnageDAO;
import com.rolify.dao.PartieDao;
import com.rolify.dao.PersonnageDAO;
import com.rolify.dao.UtilisateurDAO;
import com.rolify.entity.AssociationPartieUtilisateurPersonnage;
import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Utilisateur;
import com.rolify.entity.Views;
import com.rolify.enums.Role;

@CrossOrigin
@RestController
public class AssociationController {

	@Autowired
	AssociationPartieUtilisateurPersonnageDAO assoDAO;
	@Autowired
	UtilisateurDAO  utilDao;
	@Autowired
	PartieDao partieDao;
	@Autowired
	PersonnageDAO persoDao;

	@JsonView(Views.AssociationWithAll.class)
	@GetMapping("/api/assos")
	public ResponseEntity<List<AssociationPartieUtilisateurPersonnage>> findAll() {
		List<AssociationPartieUtilisateurPersonnage> woners = assoDAO.findAll();
		
		return new ResponseEntity<List<AssociationPartieUtilisateurPersonnage>>(woners, HttpStatus.OK);
	}

	@JsonView(Views.AssociationWithAll.class)
	@GetMapping("/api/assos/{id}")
	public ResponseEntity<AssociationPartieUtilisateurPersonnage> findOne(@PathVariable("id") Integer id) {
		AssociationPartieUtilisateurPersonnage ownr = assoDAO.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AssociationPartieUtilisateurPersonnage>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.AssociationWithAll.class)
	@DeleteMapping("/api/assos/{id}")
	public ResponseEntity<AssociationPartieUtilisateurPersonnage> delete(@PathVariable("id") Integer id) {
		AssociationPartieUtilisateurPersonnage ownr = assoDAO.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		assoDAO.delete(ownr);
		return new ResponseEntity<AssociationPartieUtilisateurPersonnage>(ownr, HttpStatus.OK);
	}
	

	@JsonView(Views.AssociationWithAll.class)
	@PostMapping("/api/assos")
	public ResponseEntity<AssociationPartieUtilisateurPersonnage> create(@RequestBody AssociationPartieUtilisateurPersonnage asso) {
		
		if (asso.getId() > 0) {
			return new ResponseEntity<AssociationPartieUtilisateurPersonnage>(asso, HttpStatus.BAD_REQUEST);
		}
		
		AssociationPartieUtilisateurPersonnage association = reInstanciate(asso);
		
		if (checkUtilInPartie(association)) {
			return new ResponseEntity<AssociationPartieUtilisateurPersonnage>(association, HttpStatus.PRECONDITION_FAILED);
		}
		if (checkConflitRole(association)) {
			return new ResponseEntity<AssociationPartieUtilisateurPersonnage>(association, HttpStatus.PRECONDITION_FAILED);
		}
		if (checkPresent(association)) {
			return new ResponseEntity<AssociationPartieUtilisateurPersonnage>(association, HttpStatus.PRECONDITION_FAILED);
		}
		
		assoDAO.save(association);
		
		return new ResponseEntity<AssociationPartieUtilisateurPersonnage>(asso, HttpStatus.OK);
		
	}
	
	private AssociationPartieUtilisateurPersonnage reInstanciate(AssociationPartieUtilisateurPersonnage asso) {
		Utilisateur utilisateur = utilDao.findByPrimaryKey(asso.getUtilisateur().getId());
		Partie partie = partieDao.findByPrimaryKey(asso.getPartie().getId());
		Personnage personnage = persoDao.findByPrimaryKey(asso.getPersonnage().getId());
		
		AssociationPartieUtilisateurPersonnage association = new AssociationPartieUtilisateurPersonnage();
		association.setUtilisateur(utilisateur);
		association.setPartie(partie);
		association.setPersonnage(personnage);
		association.setRole(asso.getRole());
		
		return association;
	}
	/**
	 * 
	 * @param asso
	 * @return renvoie true si l'utilisateur n'est ni mj ni joueur de la partie
	 */
	private boolean checkPresent(AssociationPartieUtilisateurPersonnage asso) {

		// Si le personnage est déjà présent dans une association
		List<AssociationPartieUtilisateurPersonnage> result = assoDAO.findByPersonnage(asso.getPersonnage());
		if (result.size() > 0) {
			return true;
		}
		
		// Si l'association exacte existe déjà
		result = assoDAO.findByPartieUtilisateurPersonnage(asso.getUtilisateur(), asso.getPartie(), asso.getPersonnage());
		
		if (result.size() > 0) {
			return true;
		}
		
		// Si on veut faire une association de joueur, on regarde s'il existe déjà une association utilisateur / personnage pour cette partie
		if (asso.getRole() == Role.JOUEUR) {
			result = assoDAO.findByJoueurPartie(asso.getUtilisateur(), asso.getPartie());
			
			if (result.size() > 0) {
				return true;
			}
		}
		
		return false;
		
	}
	/**
	 * 
	 * @param asso
	 * @return renvoie true si on veut faire une association
	 * 					de joueur avec le MJ
	 * 					de MJ avec un joueur
	 */
	private boolean checkConflitRole(AssociationPartieUtilisateurPersonnage asso) {
		Utilisateur utilisateur = utilDao.findByPrimaryKey(asso.getUtilisateur().getId());
		Partie partie = partieDao.findByPrimaryKey(asso.getPartie().getId());
		
		if (utilisateur == partie.getMj() && asso.getRole() == Role.JOUEUR) {
			return true;
		}
		
		if (partie.getJoueurs().contains(utilisateur) && asso.getRole() == Role.MJ) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param asso
	 * @return renvoie true si on veut faire une association
	 * 							avec un utilisateur non joueur de la partie si asso joueur
	 * 							avec un utilisateur non MJ de la partie si asso MJ
	 */
	private boolean checkUtilInPartie(AssociationPartieUtilisateurPersonnage asso) {
		Utilisateur utilisateur = utilDao.findByPrimaryKey(asso.getUtilisateur().getId());
		Partie partie = partieDao.findByPrimaryKey(asso.getPartie().getId());
		
		if (asso.getRole() == Role.JOUEUR) {
			if (!partie.getJoueurs().contains(utilisateur)) {
				return true;
			}
		}
		else {
			if (partie.getMj() != utilisateur) {
				return true;
			}
		}
		
		return false;
		
	}


}

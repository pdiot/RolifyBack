package com.rolify.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;
import com.rolify.enums.Role;

@Entity
public class AssociationPartieUtilisateurPersonnage {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private int id;
	
	@ManyToOne
	@JsonView(Views.AssociationWithUtilisateur.class)
	private Utilisateur utilisateur;
	@ManyToOne
	@JsonView(Views.AssociationWithPartie.class)
	private Partie partie;
	@ManyToOne
	@JsonView(Views.AssociationWithPersonnage.class)
	private Personnage personnage;

	@JsonView(Views.Common.class)
	private Role role;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Partie getPartie() {
		return partie;
	}
	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	public Personnage getPersonnage() {
		return personnage;
	}
	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}
	public AssociationPartieUtilisateurPersonnage() {
		super();
	}
	
	/**
	   * Associer une partie, un personnage et un utilisateur. 
	   * Le role indique s'il s'agit d'un PJ ou d'un personnage MJ
	   * @param role 	Le role du personnage
	   * 				Soit 'MJ', soit 'JOUEUR'
	   */
	public AssociationPartieUtilisateurPersonnage(Partie partie, Utilisateur utilisateur, Personnage personnage, Role role) {
		this.partie = partie;
		this.utilisateur = utilisateur;
		this.personnage = personnage;
		this.role = role;
	}

	
}

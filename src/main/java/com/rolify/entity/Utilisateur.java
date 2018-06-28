package com.rolify.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;
import com.rolify.chat.GroupeDiscussion;
import com.rolify.chat.Message;

@Entity
public class Utilisateur {
	
	@Id
	@JsonView(Views.Common.class)
	@Column(length = 512)
	private String id;
	

	@JsonView(Views.Common.class)
	@Column(length = 50)
	private String pseudo;
	
	@JsonView(Views.Common.class)
	@Column(length = 512)
	private String urlAvatar;
	
	@OneToMany (mappedBy="utilisateur", fetch=FetchType.EAGER)
	@JsonView(Views.UtilisateurWithAssociations.class)
	private Set<AssociationPartieUtilisateurPersonnage> associations; 
	
	@OneToMany (mappedBy="mj", fetch=FetchType.EAGER)
	@JsonView(Views.UtilisateurWithPartiesMJ.class)
	private Set<Partie> partiesMJ;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonView(Views.UtilisateurWithPartiesJoueur.class)
	private Set<Partie> partiesJoueur;
	
	@OneToMany (mappedBy="source", fetch=FetchType.EAGER)
	@JsonView(Views.UtilisateurWithMessages.class)
	private Set<Message> messages;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonView(Views.UtilisateurWithGroupes.class)
	private Set<GroupeDiscussion> groupes;

	public Utilisateur() {
		super();
		this.partiesJoueur = new HashSet<Partie>();
		this.messages = new HashSet<Message>();
		this.associations = new HashSet<AssociationPartieUtilisateurPersonnage>();
		this.partiesMJ = new HashSet<Partie>();
		this.groupes = new HashSet<GroupeDiscussion>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<AssociationPartieUtilisateurPersonnage> getAssociations() {
		return associations;
	}

	public void setAssociations(Set<AssociationPartieUtilisateurPersonnage> associations) {
		this.associations = associations;
	}

	public Set<Partie> getPartiesMJ() {
		return partiesMJ;
	}

	public void setPartiesMJ(Set<Partie> partiesMJ) {
		this.partiesMJ = partiesMJ;
	}

	public Set<Partie> getPartiesJoueur() {
		return partiesJoueur;
	}

	public void setPartiesJoueur(Set<Partie> partiesJoueur) {
		this.partiesJoueur = partiesJoueur;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<GroupeDiscussion> getGroupes() {
		return groupes;
	}

	public void setGroupes(Set<GroupeDiscussion> groupes) {
		this.groupes = groupes;
	}

	
	public void ajouterPartieJoueur(Partie partie) { //crée association entre un joueur et une partie
	    this.partiesJoueur.add(partie); 
	    partie.addJoueur(this); 
	 } 
	
	public void ajouterPartieMJ(Partie partie) {
		this.partiesMJ.add(partie);
	}
	
	public void joinDiscussion(GroupeDiscussion groupe) {
		this.groupes.add(groupe);
		groupe.addUtilisateur(this);
	}
	
	
	
}

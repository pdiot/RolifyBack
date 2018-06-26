package com.rolify.entity;

import java.util.HashSet;
import java.util.Set;

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
	private String id;
	
	@OneToMany (mappedBy="utilisateur", fetch=FetchType.EAGER)
	@JsonView(Views.Common.class)
	private Set<AssociationPartieUtilisateurPersonnage> associations; 
	
	@OneToMany (mappedBy="mj", fetch=FetchType.EAGER)
	@JsonView(Views.Common.class)
	private Set<Partie> partiesMJ;
	
	@ManyToMany
	@JsonView(Views.Common.class)
	private Set<Partie> partiesJoueur;
	
	@OneToMany (mappedBy="source", fetch=FetchType.LAZY)
	@JsonView(Views.Common.class)
	private Set<Message> messages;
	
	@ManyToMany
	@JsonView(Views.Common.class)
	private Set<GroupeDiscussion> groupes;

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

	public Utilisateur() {
		super();
		this.partiesJoueur = new HashSet<Partie>();
	}
	
	public void ajouterPartieJoueur(Partie partie) {
		this.partiesJoueur.add(partie);
		partie.addJoueur(this);
	}
	
	
	
}

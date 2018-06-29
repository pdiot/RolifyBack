package com.rolify.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;
import com.rolify.chat.GroupeDiscussion;
import com.rolify.chat.Message;
import com.rolify.chat.MessagePartie;

@Entity
public class Partie {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private int id;
	
	@ManyToOne
	@JsonView(Views.PartieWithMJ.class)
	private Utilisateur mj;

	@JsonView(Views.Common.class)
	@Column(length = 500)
	private String image;
	@JsonView(Views.Common.class)
	@Column(length = 100)
	private String titre;
	@JsonView(Views.Common.class)
	@Column(length = 4000)
	private String description;
	@JsonView(Views.Common.class)
	private int nombreDeJoueurs;
	
	@ManyToMany (mappedBy="partiesJoueur", fetch=FetchType.EAGER)
	@JsonView(Views.PartieWithJoueurs.class)
	private Set<Utilisateur> joueurs;
	
	@OneToMany (mappedBy="partie", fetch= FetchType.EAGER)
	@JsonView(Views.PartieWithAssociations.class)
	private Set<AssociationPartieUtilisateurPersonnage> associations;
	
	@OneToMany (mappedBy="partie", fetch= FetchType.EAGER)
	@JsonView(Views.PartieWithMessages.class)
	private Set<MessagePartie> messages;


	public Partie() {
		super();
		this.joueurs = new HashSet<Utilisateur>();
		this.associations = new HashSet<AssociationPartieUtilisateurPersonnage>();
		this.messages = new HashSet<MessagePartie>();
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getMj() {
		return mj;
	}

	public void setMj(Utilisateur mj) {
		this.mj = mj;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Set<Utilisateur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(Set<Utilisateur> joueurs) {
		this.joueurs = joueurs;
	}

	public Set<AssociationPartieUtilisateurPersonnage> getAssociations() {
		return associations;
	}

	public void setAssociations(Set<AssociationPartieUtilisateurPersonnage> associations) {
		this.associations = associations;
	}

	public Set<MessagePartie> getMessages() {
		return messages;
	}

	public void setMessages(Set<MessagePartie> messages) {
		this.messages = messages;
	}


	public void addJoueur(Utilisateur joueur) {
		this.joueurs.add(joueur);
	}
	
	public void removeJoueur(Utilisateur joueur) {
		Utilisateur joueurToRemove = null;
	    for (Utilisateur util : this.joueurs) {
	    	if (util.getId().equals(joueur.getId())) {
	    		joueurToRemove = util;
	    	}
	    }
	    if (joueurToRemove != null) {
	    	this.joueurs.remove(joueurToRemove);
	    }
	}
	
	public void changeMJ(Utilisateur mj) {
		this.mj=mj;
		mj.ajouterPartieMJ(this);
	}
	
}

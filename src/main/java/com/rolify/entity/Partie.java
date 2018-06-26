package com.rolify.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;
import com.rolify.chat.Message;
import com.rolify.chat.MessagePartie;

@Entity
public class Partie {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private int id;
	
	@ManyToOne
	@JsonView(Views.Common.class)
	private Utilisateur mj;

	@JsonView(Views.Common.class)
	private String image;
	@JsonView(Views.Common.class)
	private String titre;
	
	@ManyToMany (mappedBy="partiesJoueur")
	@JsonView(Views.Common.class)
	private Set<Utilisateur> joueurs;
	
	@OneToMany (mappedBy="partie")
	@JsonView(Views.Common.class)
	private Set<AssociationPartieUtilisateurPersonnage> associations;
	
	@OneToMany (mappedBy="partie")
	@JsonView(Views.Common.class)
	private Set<Personnage> personnages;
	
	@OneToMany (mappedBy="partie")
	@JsonView(Views.Common.class)
	private Set<MessagePartie> messages;

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

	public Set<Personnage> getPersonnages() {
		return personnages;
	}

	public void setPersonnages(Set<Personnage> personnages) {
		this.personnages = personnages;
	}

	public Set<MessagePartie> getMessages() {
		return messages;
	}

	public void setMessages(Set<MessagePartie> messages) {
		this.messages = messages;
	}

	public Partie() {
		super();
		this.joueurs = new HashSet<Utilisateur>();
	}

	public void addJoueur(Utilisateur joueur) {
		this.joueurs.add(joueur);
	}
 	
	
}

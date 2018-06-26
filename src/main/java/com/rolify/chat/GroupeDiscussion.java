package com.rolify.chat;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;
import com.rolify.entity.Utilisateur;
import com.rolify.entity.Views;

@Entity
public class GroupeDiscussion {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private int id;
	
	@ManyToMany (mappedBy="groupes")
	@JsonView(Views.Common.class)
	private Set<Utilisateur> utilisateurs;
	
	@OneToMany (mappedBy="groupe")
	@JsonView(Views.Common.class)
	private Set<MessagePrive> messages;

	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Set<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}



	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}



	public Set<MessagePrive> getMessages() {
		return messages;
	}



	public void setMessages(Set<MessagePrive> messages) {
		this.messages = messages;
	}



	public GroupeDiscussion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void addUtilisateur(Utilisateur utilisateur) {
		this.utilisateurs.add(utilisateur);
	}
	

}

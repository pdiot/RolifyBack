package com.rolify.chat;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonView;
import com.rolify.entity.Utilisateur;
import com.rolify.entity.Views;

@Entity
@PrimaryKeyJoinColumn(name="Message_id")
public class MessagePrive extends Message {

	@ManyToOne
	@JsonView(Views.MessageWithGroupe.class)
	private GroupeDiscussion groupe;

	public GroupeDiscussion getGroupe() {
		return groupe;
	}

	public void setGroupe(GroupeDiscussion groupe) {
		this.groupe = groupe;
	}

	public MessagePrive() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
}

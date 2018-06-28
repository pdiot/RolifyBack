package com.rolify.chat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonView;
import com.rolify.entity.Partie;
import com.rolify.entity.Views;

@Entity
@PrimaryKeyJoinColumn(name="Message_id")
public class MessagePartie extends Message {

	@JsonView(Views.Common.class)
	@ManyToOne
	private Partie partie;

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public MessagePartie() {
		super();
	}
	
	

}

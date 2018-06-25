package com.rolify.chat;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;
import com.rolify.entity.Views;
import com.rolify.entity.Utilisateur;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Message {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private int id;

	@ManyToOne
	@JsonView(Views.Common.class)
	private Utilisateur source;

	@JsonView(Views.Common.class)
	private LocalDateTime dateTime;

	@JsonView(Views.Common.class)
	private String body;

}

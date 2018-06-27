package com.rolify.chat;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

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
	@JsonView(Views.MessageWithSource.class)
	private Utilisateur source;

	@JsonView(Views.Common.class)
	@Column (updatable = false)
	private Date dateTime;

	@JsonView(Views.Common.class)
	@Column(length = 300)
	private String body;
	
	@PrePersist
	protected void onCreate() {
	    if (dateTime == null) { dateTime = new Date(); }
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getSource() {
		return source;
	}

	public void setSource(Utilisateur source) {
		this.source = source;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	

}

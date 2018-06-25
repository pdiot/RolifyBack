package com.rolify.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Personnage {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private int id;
	@JsonView(Views.Common.class)
	private String nom;
	@JsonView(Views.Common.class)
	private String classe;
	@JsonView(Views.Common.class)
	private String race;
	@JsonView(Views.Common.class)
	private String sexe;
	@JsonView(Views.Common.class)
	private int pv;
	@JsonView(Views.Common.class)
	private int fo;
	@JsonView(Views.Common.class)
	private int defense;
	@JsonView(Views.Common.class)
	private int esprit;
	@JsonView(Views.Common.class)
	private int intelligence;
	@JsonView(Views.Common.class)
	private int initiative;
	@JsonView(Views.Common.class)
	private String equipement;
	@JsonView(Views.Common.class)
	private String inventaire;
	@JsonView(Views.Common.class)
	private String background;
	@JsonView(Views.Common.class)

	@OneToMany (mappedBy="personnage")
	private Set<AssociationPartieUtilisateurPersonnage> associations;
	
	@ManyToOne
	private Partie partie;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getForce() {
		return fo;
	}

	public void setForce(int force) {
		this.fo = force;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getEsprit() {
		return esprit;
	}

	public void setEsprit(int esprit) {
		this.esprit = esprit;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public String getEquipement() {
		return equipement;
	}

	public void setEquipement(String equipement) {
		this.equipement = equipement;
	}

	public String getInventaire() {
		return inventaire;
	}

	public void setInventaire(String inventaire) {
		this.inventaire = inventaire;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public Set<AssociationPartieUtilisateurPersonnage> getAssociations() {
		return associations;
	}

	public void setAssociations(Set<AssociationPartieUtilisateurPersonnage> associations) {
		this.associations = associations;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Personnage() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}

package com.rolify.dao;

import java.util.List;

import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Utilisateur;

public interface PersonnageDAO extends GenericDao<Personnage, Integer> {
	public List<Personnage> findByPartie(Partie partie);
	public List<Personnage> findByJoueur(Utilisateur joueur);
	public List<Personnage> findByMJ(Utilisateur mj);
	public Personnage findByPartieJoueur(Partie partie, Utilisateur joueur);
	public List<Personnage> findByPartieMj(Partie partie, Utilisateur mj);
}

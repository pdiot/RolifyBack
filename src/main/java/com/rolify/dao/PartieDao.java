package com.rolify.dao;

import java.util.List;

import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Utilisateur;

public interface PartieDao extends GenericDao <Partie, Integer> {
	public List<Partie> findByMj(Utilisateur utilisateurMj);
	public List<Partie> findByJoueur(Utilisateur idJoueur);
	public List<Partie> findByPersonnage(Personnage personnage);
	public List<Partie> findNotIn(Utilisateur joueur);
}

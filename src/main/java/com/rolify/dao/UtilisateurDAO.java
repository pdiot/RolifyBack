package com.rolify.dao;

import java.util.List;

import com.rolify.entity.Partie;
import com.rolify.entity.Utilisateur;

public interface UtilisateurDAO extends GenericDao<Utilisateur, String> {
	public void becomeMJ(Utilisateur util, Partie partie);
	public void joinPartie(Utilisateur util, Partie partie);
	public List<Partie> findParties(Utilisateur util);
}

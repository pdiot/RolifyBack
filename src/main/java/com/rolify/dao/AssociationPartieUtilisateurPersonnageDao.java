package com.rolify.dao;

import java.util.List;

import com.rolify.entity.AssociationPartieUtilisateurPersonnage;
import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Utilisateur;

public interface AssociationPartieUtilisateurPersonnageDao extends GenericDao <AssociationPartieUtilisateurPersonnage, Integer> {
	/**
	 * 
	 * @param personnage
	 * @return l'association li�e � ce personnage
	 */
	public AssociationPartieUtilisateurPersonnage findByPersonnage(Personnage personnage);
	/**
	 * 
	 * @param partie
	 * @return la liste des associations li�es � cette partie
	 */
	public List<AssociationPartieUtilisateurPersonnage> findByPartie(Partie partie);
	/**
	 * 
	 * @param utilisateur
	 * @return la liste des associations li�es � cet utilisateur avec le role joueur
	 */
	public List<AssociationPartieUtilisateurPersonnage> findByJoueur(Utilisateur utilisateur);
	/**
	 * 
	 * @param utilisateur
	 * @param partie
	 * @return l'association li�e � cet utilisateur et cette partie en tant que joueur
	 */
	public AssociationPartieUtilisateurPersonnage findByJoueurPartie(Utilisateur utilisateur, Partie partie);
	/**
	 * 
	 * @param utilisateur
	 * @return toutes les associations li�es � cet utilisateur en tant que MJ
	 */
	public List<AssociationPartieUtilisateurPersonnage> findByMj(Utilisateur utilisateur);
	/**
	 * 
	 * @param utilisateur
	 * @param partie
	 * @return les associations li�es � cette partie et cet utilisateur en tant que MJ
	 */
	public List<AssociationPartieUtilisateurPersonnage> findByMjPartie(Utilisateur utilisateur, Partie partie);

}

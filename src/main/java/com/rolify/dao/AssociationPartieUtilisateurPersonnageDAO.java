package com.rolify.dao;

import java.util.List;

import com.rolify.entity.AssociationPartieUtilisateurPersonnage;
import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Utilisateur;

public interface AssociationPartieUtilisateurPersonnageDAO extends GenericDao <AssociationPartieUtilisateurPersonnage, Integer> {
	/**
	 * 
	 * @param personnage
	 * @return l'association li�e � ce personnage
	 */
	public List<AssociationPartieUtilisateurPersonnage> findByPersonnage(Personnage personnage);
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
	public List<AssociationPartieUtilisateurPersonnage> findByJoueurPartie(Utilisateur utilisateur, Partie partie);
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
	/**
	 * 
	 * @param utilisateur
	 * @param partie
	 * @param personnage
	 * @return Renvoie les associations existantes entre les 3 param�tres (utilis�e pour assurer l'unicit� des associations)
	 */
	public List<AssociationPartieUtilisateurPersonnage> findByPartieUtilisateurPersonnage(Utilisateur utilisateur, Partie partie, Personnage personnage);
	/**
	 * 
	 * @param partie
	 * @return la liste des associations de role Joueur li�es � cette partie
	 */
	public List<AssociationPartieUtilisateurPersonnage> findAssoJoueursByPartie(Partie partie);
}

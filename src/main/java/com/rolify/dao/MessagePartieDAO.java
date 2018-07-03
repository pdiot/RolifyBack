package com.rolify.dao;

import java.util.List;

import com.rolify.chat.MessagePartie;
import com.rolify.entity.Partie;

public interface MessagePartieDAO extends GenericDao<MessagePartie, Integer> {

	/**
	 * 
	 * @param partie
	 * @return la liste des messages envoyés dans cette partie
	 */
	public List<MessagePartie> findByPartie(Integer Idpartie);
	
	/**
	 * 
	 * @param dernierId
	 * @param partie
	 * @return la liste des messages envoyés dans cette partie dont l'id est supérieur à dernierId
	 */
	public List<MessagePartie> findSinceIdForGroupe(Integer dernierId, Partie partie);
}

package com.rolify.dao;

import java.util.List;

import com.rolify.chat.MessagePartie;
import com.rolify.entity.Partie;

public interface MessagePartieDAO extends GenericDao<MessagePartie, Integer> {

	/**
	 * 
	 * @param partie
	 * @return la liste des messages envoy�s dans cette partie
	 */
	public List<MessagePartie> findByPartie(Integer Idpartie);
	
	/**
	 * 
	 * @param dernierId
	 * @param partie
	 * @return la liste des messages envoy�s dans cette partie dont l'id est sup�rieur � dernierId
	 */
	public List<MessagePartie> findSinceIdForGroupe(Integer dernierId, Partie partie);
}

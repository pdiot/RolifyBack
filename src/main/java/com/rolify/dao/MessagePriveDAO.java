package com.rolify.dao;

import java.util.List;

import com.rolify.chat.GroupeDiscussion;
import com.rolify.chat.MessagePrive;
import com.rolify.entity.Utilisateur;

public interface MessagePriveDAO extends GenericDao<MessagePrive, Integer> {

	/**
	 * 
	 * @param groupe
	 * @return la liste des messages privés adressés à ce groupe de discussion
	 */
	public List<MessagePrive> findByGroupe(GroupeDiscussion groupe);

	/**
	 * 
	 * @param dernierId
	 * @param groupe
	 * @return la liste des messages privés adressés à ce groupe de discussion dont l'id est supérieur à dernierId
	 */
	public List<MessagePrive> findSinceIdForGroupe(Integer dernierId, GroupeDiscussion groupe);
}

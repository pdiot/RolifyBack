package com.rolify.dao;

import java.util.List;

import com.rolify.chat.MessageGlobal;

public interface MessageGlobalDAO extends GenericDao<MessageGlobal, Integer> {
	/**
	 * 
	 * @param dernierId
	 * @return la liste des messages globaux envoyés dont l'id est supérieure à dernierId
	 */
	public List<MessageGlobal> findSinceLast(Integer dernierId);
}

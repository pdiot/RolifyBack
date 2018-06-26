package com.rolify.dao;

import java.util.List;

import com.rolify.chat.GroupeDiscussion;
import com.rolify.entity.Utilisateur;

public interface GroupeDiscussionDAO extends GenericDao<GroupeDiscussion, Integer> {
	/**
	 * 
	 * @param utilisateur
	 * @return la liste des groupes de discussion privée dont l'utilisateur est membre
	 */
	public List<GroupeDiscussion> findByUtilisateur(Utilisateur utilisateur);
	
}

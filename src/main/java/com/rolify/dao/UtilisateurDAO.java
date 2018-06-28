package com.rolify.dao;

import java.util.List;

import com.rolify.chat.GroupeDiscussion;
import com.rolify.chat.Message;
import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Utilisateur;

public interface UtilisateurDAO extends GenericDao<Utilisateur, String> {
	public List<Utilisateur> findMjByPartie(Partie partie);
	public List<Utilisateur> findJoueursByPartie(Partie partie);
	public List<Utilisateur> findUtilisateurByDiscussion(GroupeDiscussion groupe);
	public List<Utilisateur> findUtilisateurByMessage(Message message);
}

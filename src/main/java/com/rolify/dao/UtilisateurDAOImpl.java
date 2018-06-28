package com.rolify.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.rolify.chat.GroupeDiscussion;
import com.rolify.chat.Message;
import com.rolify.chat.MessageGlobal;
import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Utilisateur;

@Transactional
@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Utilisateur> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Utilisateur> crit = cb.createQuery(Utilisateur.class);
		Root<Utilisateur> r = crit.from(Utilisateur.class);

		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public Utilisateur save(Utilisateur entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Utilisateur entity) {
		Utilisateur eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	
	@Override
	public Utilisateur findByPrimaryKey(String id) {
		return em.find(Utilisateur.class, id);
	}

	@Override
	public Utilisateur update(Utilisateur entity) {
		return em.merge(entity);
	}

	@Override
	public List<Utilisateur> findMjByPartie(Partie partie) {
		
		String querystring = "select u from Utilisateur u join u.partiesMJ p where p = ?1";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, partie);	
		return query.getResultList();
	}

	@Override
	public List<Utilisateur> findJoueursByPartie(Partie partie) {
		
		String querystring = "select u from Utilisateur u join u.partiesJoueur p where p = ?1";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, partie);
		
		return (List<Utilisateur>) query.getResultList();
		
		
	}

	@Override
	public List<Utilisateur> findUtilisateurByDiscussion(GroupeDiscussion groupe) {
		
		String querystring = "select u from Utilisateur u join u.groupes p where p = ?1";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, groupe);
		
		return (List<Utilisateur>) query.getResultList();
		
	}

	@Override
	public List<Utilisateur> findUtilisateurByMessage(Message message) {
		
		String querystring = "select utilisateur from Utilisateur u where u.message = ?1";  //?1 = Parameter
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, message); //set Parameter here
		return query.getResultList();
	}
	


}

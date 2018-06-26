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
		System.out.println("Dans findall");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Utilisateur> crit = cb.createQuery(Utilisateur.class);
		Root<Utilisateur> r = crit.from(Utilisateur.class);

		System.out.println("Avant select");
		crit.select(r);

		System.out.println("Après select");
		
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
	public Utilisateur findMjByPartie(Partie partie) {
		
		String querystring = "select u from Utilisateur u where u == ?1";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, partie.getMj());
		
		return (Utilisateur) query.getSingleResult();
	}

	@Override
	public List<Utilisateur> findJoueursByPartie(Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> findUtilisateurByDiscussion(GroupeDiscussion groupe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur findUtilisateurByPersonnage(Personnage personnage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur findUtilisateurByMessage(Message message) {
		// TODO Auto-generated method stub
		return null;
	}
	


}

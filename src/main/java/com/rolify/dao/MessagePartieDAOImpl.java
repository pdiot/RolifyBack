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

import com.rolify.chat.MessagePartie;
import com.rolify.entity.Partie;

@Transactional
@Repository
public class MessagePartieDAOImpl implements MessagePartieDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<MessagePartie> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MessagePartie> crit = cb.createQuery(MessagePartie.class);
		Root<MessagePartie> r = crit.from(MessagePartie.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}
	
	/*public List<MessagePartie> findAllPartie(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MessagePartie> crit = cb.createQuery(MessagePartie.class);
		Root<MessagePartie> r = crit.from(MessagePartie.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}*/

	@Override
	public MessagePartie save(MessagePartie entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(MessagePartie entity) {
		MessagePartie eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	
	@Override
	public MessagePartie findByPrimaryKey(Integer id) {
		return em.find(MessagePartie.class, id);
	}

	@Override
	public MessagePartie update(MessagePartie entity) {
		return em.merge(entity);
	}

	@Override
	public List<MessagePartie> findByPartie(Integer Idpartie) {
		String querystring = "select m from MessagePartie m  where m.partie.id= ?1";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, Idpartie); //set Parameter here
		return query.getResultList();
	}

	@Override
	public List<MessagePartie> findSinceIdForGroupe(Integer dernierId, Partie partie) {
		String querystring = "select m from MessagePartie m where m.id> ?1 and m.partie= ?2";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, dernierId); 
		query.setParameter(2, partie); 
		return query.getResultList();
	}

}

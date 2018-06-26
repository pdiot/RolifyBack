package com.rolify.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public MessagePartie findByPrimaryKey(String id) {
		return em.find(MessagePartie.class, id);
	}

	@Override
	public MessagePartie update(MessagePartie entity) {
		return em.merge(entity);
	}

	@Override
	public List<MessagePartie> findByPartie(Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessagePartie> findSinceIdForGroupe(Integer dernierId, Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

}

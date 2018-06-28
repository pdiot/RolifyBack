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
import com.rolify.chat.MessagePartie;
import com.rolify.chat.MessagePrive;
import com.rolify.entity.Utilisateur;

@Transactional
@Repository
public class MessagePriveDAOImpl implements MessagePriveDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<MessagePrive> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MessagePrive> crit = cb.createQuery(MessagePrive.class);
		Root<MessagePrive> r = crit.from(MessagePrive.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public MessagePrive save(MessagePrive entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(MessagePrive entity) {
		MessagePrive eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	

	@Override
	public MessagePrive update(MessagePrive entity) {
		return em.merge(entity);
	}

	@Override
	public MessagePrive findByPrimaryKey(Integer id) {
		return em.find(MessagePrive.class, id);
	}

	@Override
	public List<MessagePrive> findByGroupe(GroupeDiscussion groupe) {
		String querystring = "select m from MessagePrive m where m.groupe = ?1";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, groupe); //set Parameter here
		return query.getResultList();
	}

	@Override
	public List<MessagePrive> findSinceIdForGroupe(Integer dernierId, GroupeDiscussion groupe) {
		String querystring = "select m from MessagePrive m where m.id > ?1 and m.groupe=?2";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, dernierId); 
		query.setParameter(2, groupe); 
		return query.getResultList();
	}



}

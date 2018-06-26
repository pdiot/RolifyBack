package com.rolify.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.rolify.chat.MessagePrive;

@Transactional
@Repository
public class MesagePriveDAOImpl implements MessagePriveDAO {

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
	public MessagePrive findByPrimaryKey(String id) {
		return em.find(MessagePrive.class, id);
	}

	@Override
	public MessagePrive update(MessagePrive entity) {
		return em.merge(entity);
	}

}

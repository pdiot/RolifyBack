package com.rolify.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.rolify.chat.MessageGlobal;

@Transactional
@Repository
public class MessageGlobalDAOImpl implements MessageGlobalDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<MessageGlobal> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MessageGlobal> crit = cb.createQuery(MessageGlobal.class);
		Root<MessageGlobal> r = crit.from(MessageGlobal.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public MessageGlobal save(MessageGlobal entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(MessageGlobal entity) {
		MessageGlobal eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	

	@Override
	public MessageGlobal update(MessageGlobal entity) {
		return em.merge(entity);
	}

	@Override
	public MessageGlobal findByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageGlobal> findSinceLast(Integer dernierId) {
		// TODO Auto-generated method stub
		return null;
	}

}
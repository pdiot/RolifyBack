package com.rolify.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.rolify.chat.GroupeDiscussion;

@Transactional
@Repository
public class GroupeDiscussionDAOImpl implements GroupeDiscussionDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<GroupeDiscussion> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GroupeDiscussion> crit = cb.createQuery(GroupeDiscussion.class);
		Root<GroupeDiscussion> r = crit.from(GroupeDiscussion.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public GroupeDiscussion save(GroupeDiscussion entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(GroupeDiscussion entity) {
		GroupeDiscussion eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	

	@Override
	public GroupeDiscussion update(GroupeDiscussion entity) {
		return em.merge(entity);
	}

	@Override
	public GroupeDiscussion findByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

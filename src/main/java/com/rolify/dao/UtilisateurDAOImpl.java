package com.rolify.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
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

}

package com.rolify.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rolify.entity.AssociationPartieUtilisateurPersonnage;




@Transactional
@Repository
public class AssociationPartieUtilisateurPersonnageImpl implements AssociationPartieUtilisateurPersonnageDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<AssociationPartieUtilisateurPersonnage> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AssociationPartieUtilisateurPersonnage> crit = cb.createQuery(AssociationPartieUtilisateurPersonnage.class);
		Root<AssociationPartieUtilisateurPersonnage> r = crit.from(AssociationPartieUtilisateurPersonnage.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public AssociationPartieUtilisateurPersonnage save(AssociationPartieUtilisateurPersonnage entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(AssociationPartieUtilisateurPersonnage entity) {
		AssociationPartieUtilisateurPersonnage eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	
	@Override
	public AssociationPartieUtilisateurPersonnage findByPrimaryKey(String id) {
		return em.find(AssociationPartieUtilisateurPersonnage.class, id);
	}

	@Override
	public AssociationPartieUtilisateurPersonnage update(AssociationPartieUtilisateurPersonnage entity) {
		return em.merge(entity);
	}

}

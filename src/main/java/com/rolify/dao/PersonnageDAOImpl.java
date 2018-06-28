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

import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Utilisateur;
import com.rolify.enums.Role;

@Transactional
@Repository
public class PersonnageDAOImpl implements PersonnageDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Personnage> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Personnage> crit = cb.createQuery(Personnage.class);
		Root<Personnage> r = crit.from(Personnage.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public Personnage save(Personnage entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Personnage entity) {
		Personnage eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	
	@Override
	public Personnage findByPrimaryKey(Integer id) {
		return em.find(Personnage.class, id);
	}

	@Override
	public Personnage update(Personnage entity) {
		return em.merge(entity);
	}



}

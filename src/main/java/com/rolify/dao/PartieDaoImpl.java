package com.rolify.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;




@Transactional
@Repository
public class PartieDaoImpl implements PartieDao{

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Partie> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Partie> crit = cb.createQuery(Partie.class);
		Root<Partie> r = crit.from(Partie.class);
		
		crit.select(r);
		
		return em.createQuery(crit).getResultList();
	}

	@Override
	public Partie save(Partie entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void delete(Partie entity) {
		Partie eMerged = em.merge(entity);
		em.remove(eMerged);
		
	}
	
	@Override
	public Partie findByPrimaryKey(Integer id) {
		return em.find(Partie.class, id);
	}

	@Override
	public Partie update(Partie entity) {
		return em.merge(entity);
	}

	@Override
	public List<Partie> findByMj(String idMj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Partie> findByJoueur(String idJoueur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partie findByPersonnage(Personnage personnage) {
		// TODO Auto-generated method stub
		return null;
	}

}

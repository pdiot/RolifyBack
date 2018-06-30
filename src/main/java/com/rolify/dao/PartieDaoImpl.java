package com.rolify.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Utilisateur;




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
	public List<Partie> findByMj(Utilisateur utilisateurMj) {
		String querystring = "select p from Partie p where p.mj = ?1";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, utilisateurMj);
		
		return (List<Partie>) query.getResultList();
		
	}

	@Override
	public List<Partie> findByJoueur(Utilisateur joueur) {
		String querystring = "select p from Partie p join p.joueurs j where j = ?1";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, joueur);
		
		return (List<Partie>) query.getResultList();
	}

	@Override
	public List<Partie> findNotIn(Utilisateur util) {
		List<Partie> allParties = findAll();
		List<Partie> partiesMj = findByMj(util);
		List<Partie> partiesJoueur = findByJoueur(util);
		List<Partie> partiesNotIn = new ArrayList<Partie>();
		for (Partie partie : allParties) {
			if (!partiesMj.contains(partie) && !partiesJoueur.contains(partie)) {
				partiesNotIn.add(partie);
			}
		}
		return partiesNotIn;
	}

	@Override
	public Partie findByPersonnage(Personnage personnage) {
		
		String querystring = "select p from Partie p where p.personnages = ?1";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, personnage.getId());
		
		return (Partie) query.getResultList();
	}

}

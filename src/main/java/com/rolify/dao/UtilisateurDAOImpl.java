package com.rolify.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.rolify.entity.Partie;
import com.rolify.entity.Utilisateur;

@Transactional
@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Utilisateur> findAll() {
		System.out.println("Dans findall");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Utilisateur> crit = cb.createQuery(Utilisateur.class);
		Root<Utilisateur> r = crit.from(Utilisateur.class);

		System.out.println("Avant select");
		crit.select(r);

		System.out.println("Après select");
		
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
	
	@Override
	public void becomeMJ(Utilisateur util, Partie partie) {
		// TODO
	}

	@Override
	public void joinPartie(Utilisateur util, Partie partie) {
		// TODO
		
	}

	@Override
	public List<Partie> findParties(Utilisateur util) {
		// TODO Auto-generated method stub
		// Doit renvoyer la liste des parties dans lesquelles util est joueur
		return null;
	}

}

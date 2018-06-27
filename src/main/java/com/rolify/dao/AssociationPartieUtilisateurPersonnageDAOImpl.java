package com.rolify.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rolify.entity.AssociationPartieUtilisateurPersonnage;
import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;
import com.rolify.entity.Utilisateur;
import com.rolify.enums.Role;

@Transactional
@Repository
public class AssociationPartieUtilisateurPersonnageDAOImpl implements AssociationPartieUtilisateurPersonnageDAO {

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
	public AssociationPartieUtilisateurPersonnage findByPrimaryKey(Integer id) {
		return em.find(AssociationPartieUtilisateurPersonnage.class, id);
	}

	@Override
	public AssociationPartieUtilisateurPersonnage update(AssociationPartieUtilisateurPersonnage entity) {
		return em.merge(entity);
	}

	@Override
	public List<AssociationPartieUtilisateurPersonnage> findByPersonnage(Personnage personnage) {
		String querystring = "select a from AssociationPartieUtilisateurPersonnage a  where a.personnage = ?1";
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, personnage); //set Parameter here
		
		return query.getResultList();
	}

	@Override
	public List<AssociationPartieUtilisateurPersonnage> findByPartie(Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssociationPartieUtilisateurPersonnage> findByJoueur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssociationPartieUtilisateurPersonnage> findByJoueurPartie(Utilisateur utilisateur, Partie partie) {
		String querystring = "select a from AssociationPartieUtilisateurPersonnage a  where a.utilisateur = ?1" +
				 " and a.partie = ?2 and a.role = ?3";  //?1 = Parameter
				Query query = em.createQuery( querystring ) ;
				query.setParameter(1, utilisateur); //set Parameter here
				query.setParameter(2, partie); //set Parameter here
				query.setParameter(3, Role.JOUEUR); //set Parameter here
				
				return query.getResultList();
	}

	@Override
	public List<AssociationPartieUtilisateurPersonnage> findByMj(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssociationPartieUtilisateurPersonnage> findByMjPartie(Utilisateur utilisateur, Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssociationPartieUtilisateurPersonnage> findByPartieUtilisateurPersonnage(Utilisateur utilisateur,
			Partie partie, Personnage personnage) {
		
		String querystring = "select a from AssociationPartieUtilisateurPersonnage a  where a.utilisateur = ?1" +
		 " and a.partie = ?2 and a.personnage = ?3";  //?1 = Parameter
		Query query = em.createQuery( querystring ) ;
		query.setParameter(1, utilisateur); //set Parameter here
		query.setParameter(2, partie); //set Parameter here
		query.setParameter(3, personnage); //set Parameter here
		
		return query.getResultList();
	}

}

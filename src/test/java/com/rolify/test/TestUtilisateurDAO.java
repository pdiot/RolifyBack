package com.rolify.test;

import com.rolify.dao.PartieDao;
import com.rolify.dao.PartieDaoImpl;
import com.rolify.dao.UtilisateurDAO;
import com.rolify.dao.UtilisateurDAOImpl;
import com.rolify.entity.Partie;
import com.rolify.entity.Utilisateur;

public class TestUtilisateurDAO {

	private static UtilisateurDAO daoUtil;
	private static PartieDao daoPartie;
	
	public static void main (String[] args) {
		
		System.out.println("Before");
		daoUtil = new UtilisateurDAOImpl();
		daoPartie = new PartieDaoImpl();
		testFindAll();
		testSave();
		testDelete();
		testFindByPrimaryKey();
		testJoinPartie();
		testBecomeMJ();
	}
	
	
	public static void before() {
	}

	public static void testFindAll() {
		System.out.println("Test");
		if (daoUtil.findAll().size() == 2) {
			System.out.println("Find ALl OK");
		} else {
			System.out.println("KO");
		}
	}

	public static void testSave() {
		Utilisateur util = new Utilisateur();
		util.setId("TestoNomo");
		if (daoUtil.save(util) != null) {
			System.out.println("Save 1 OK");
		} else {
			System.out.println("KO");
		}
		if(daoUtil.findAll().size() == 3) {
			System.out.println("Save 2 OK");
		} else {
			System.out.println("KO");
		}
	}


	public static void testDelete() {
		Utilisateur util = new Utilisateur();
		util.setId("TestoNomo");
		daoUtil.delete(util);
		if (daoUtil.findAll().size() == 2) {
			System.out.println("Test Del OK");
		} else {
			System.out.println("KO");
		}
	}

	public static void testFindByPrimaryKey() {
		Utilisateur util = new Utilisateur();
		util.setId("Toto");
		if (daoUtil.findByPrimaryKey("Toto") == util) {
			System.out.println("Test find by i ok");
		} else {
			System.out.println("KO");
		}
	}

	public static void testJoinPartie() {
		Partie partie = daoPartie.findByPrimaryKey(1);
		Utilisateur util = daoUtil.findByPrimaryKey("Tata");
		if (daoPartie.findByJoueur(util.getId()).size() == 0) {
			System.out.println("Test join partie joueur 1 OK");
		} else {
			System.out.println("KO");
		}
		util.ajouterPartieJoueur(partie);
		daoUtil.update(util);
		if (daoPartie.findByJoueur(util.getId()).size() == 1) {
			System.out.println("Test join Partie joueur 2 OK");
		} else {
			System.out.println("KO");
		}
	}
	

	public static void testBecomeMJ() {
		Partie partie = daoPartie.findByPrimaryKey(1);
		Utilisateur util = daoUtil.findByPrimaryKey("Tata");
		if (daoPartie.findByMj(util.getId()).size() == 0) {
			System.out.println("test find by mj 1 ok");
		} else {
			System.out.println("KO");
		}
		util.ajouterPartieMJ(partie);
		daoUtil.update(util);
		if (daoPartie.findByMj(util.getId()).size() == 1) {
			System.out.println("test find by mj 2 ok");
		} else {
			System.out.println("KO");
		}
	}
	

	public static void testRejoindreDiscussion() {
		// TODO
	}
	

}

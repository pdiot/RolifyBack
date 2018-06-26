package com.rolify.dao;

import java.util.List;

import com.rolify.entity.Partie;
import com.rolify.entity.Personnage;

public interface PartieDao extends GenericDao <Partie, Integer> {
	public List<Partie> findByMj(String idMj);
	public List<Partie> findByJoueur(String idJoueur);
	public Partie findByPersonnage(Personnage personnage);
}

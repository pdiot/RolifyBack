package com.rolify.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import com.rolify.entity.Partie;
import com.rolify.entity.Utilisateur;

public interface GenericDao <T, ID extends Serializable> {
	T findByPrimaryKey(ID id);
	List<T> findAll ();
	T save(T entity);
	void delete(T entity);
	T update(T entity);
	
}

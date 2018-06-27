package com.rolify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rolify.dao.PartieDao;
import com.rolify.entity.Partie;

@CrossOrigin
@RestController
public class PartieController {

	@Autowired
	PartieDao partieDao;
	
	@GetMapping("/api/parties")
	public ResponseEntity<List<Partie>> findAll() {
		List<Partie> woners = partieDao.findAll();
		
		return new ResponseEntity<List<Partie>>(woners, HttpStatus.OK);
	}
	
	@GetMapping("/api/parties/{id}")
	public ResponseEntity<Partie> findOne(@PathVariable("id") Integer id) {
		Partie ownr = partieDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Partie>(ownr, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/parties/{id}")
	public ResponseEntity<Partie> delete(@PathVariable("id") Integer id) {
		Partie ownr = partieDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		partieDao.delete(ownr);
		return new ResponseEntity<Partie>(ownr, HttpStatus.OK);
	}
	
	@PostMapping("/api/parties")
	public ResponseEntity<Partie> create(@RequestBody Partie user) {
		
		if (user.getId() > 0) {
			return new ResponseEntity<Partie>(user, HttpStatus.BAD_REQUEST);
		}
		
		partieDao.save(user);
		
		return new ResponseEntity<Partie>(user, HttpStatus.OK);
		
	}
	
	@PutMapping("/api/parties")
	public ResponseEntity<Partie> update(@RequestBody Partie user) {
		Partie ch = partieDao.findByPrimaryKey(user.getId());		
		if (ch == null) {
			ch = partieDao.save(user);
		} else {
			ch = partieDao.update(user);
		}		
		return new ResponseEntity<Partie>(ch, HttpStatus.OK);
	}

}

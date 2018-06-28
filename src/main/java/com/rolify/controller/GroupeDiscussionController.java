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

import com.fasterxml.jackson.annotation.JsonView;
import com.rolify.chat.GroupeDiscussion;
import com.rolify.dao.GroupeDiscussionDAO;
import com.rolify.entity.Views;

@CrossOrigin
@RestController
public class GroupeDiscussionController {

	@Autowired
	GroupeDiscussionDAO groupeDao;
	
	@JsonView(Views.GroupeDiscussionWithAll.class)
	@GetMapping("/api/discussions")
	public ResponseEntity<List<GroupeDiscussion>> findAll() {
		List<GroupeDiscussion> woners = groupeDao.findAll();
		
		return new ResponseEntity<List<GroupeDiscussion>>(woners, HttpStatus.OK);
	}
	
	@GetMapping("/api/discussions/{id}")
	@JsonView(Views.GroupeDiscussionWithAll.class)
	public ResponseEntity<GroupeDiscussion> findOne(@PathVariable("id") Integer id) {
		GroupeDiscussion ownr = groupeDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<GroupeDiscussion>(ownr, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/discussions/{id}")
	@JsonView(Views.GroupeDiscussionWithAll.class)
	public ResponseEntity<GroupeDiscussion> delete(@PathVariable("id") Integer id) {
		GroupeDiscussion ownr = groupeDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		groupeDao.delete(ownr);
		return new ResponseEntity<GroupeDiscussion>(ownr, HttpStatus.OK);
	}
	
	@PostMapping("/api/discussions")
	@JsonView(Views.GroupeDiscussionWithAll.class)
	public ResponseEntity<GroupeDiscussion> create(@RequestBody GroupeDiscussion user) {
		
		if (user.getId() > 0) {
			return new ResponseEntity<GroupeDiscussion>(user, HttpStatus.BAD_REQUEST);
		}
		
		groupeDao.save(user);
		
		return new ResponseEntity<GroupeDiscussion>(user, HttpStatus.OK);
		
	}
	
	@PutMapping("/api/discussions")
	@JsonView(Views.GroupeDiscussionWithAll.class)
	public ResponseEntity<GroupeDiscussion> update(@RequestBody GroupeDiscussion user) {
		GroupeDiscussion ch = groupeDao.findByPrimaryKey(user.getId());		
		if (ch == null) {
			ch = groupeDao.save(user);
		} else {
			ch = groupeDao.update(user);
		}		
		return new ResponseEntity<GroupeDiscussion>(ch, HttpStatus.OK);
	}

}

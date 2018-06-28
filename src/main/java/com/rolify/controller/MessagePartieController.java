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
import com.rolify.dao.MessagePartieDAO;
import com.rolify.chat.MessagePartie;
import com.rolify.entity.Views;

@CrossOrigin
@RestController
public class MessagePartieController {

	@Autowired
	MessagePartieDAO messDao;

	@JsonView(Views.MessageWithAll.class)
	@GetMapping("/api/messagespartie")
	public ResponseEntity<List<MessagePartie>> findAll() {
		List<MessagePartie> woners = messDao.findAll();
		return new ResponseEntity<List<MessagePartie>>(woners, HttpStatus.OK);
	}

	@JsonView(Views.MessageWithAll.class)
	@GetMapping("/api/messagespartie/{id}")
	public ResponseEntity<MessagePartie> findOne(@PathVariable("id") Integer id) {
		MessagePartie ownr = messDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MessagePartie>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.MessageWithAll.class)
	@DeleteMapping("/api/messagespartie/{id}")
	public ResponseEntity<MessagePartie> delete(@PathVariable("id") Integer id) {
		MessagePartie ownr = messDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		messDao.delete(ownr);
		return new ResponseEntity<MessagePartie>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.MessageWithAll.class)
	@PostMapping("/api/messagespartie")
	public ResponseEntity<MessagePartie> create(@RequestBody MessagePartie user) {
		
		if (user.getId() > 0) {
			return new ResponseEntity<MessagePartie>(user, HttpStatus.BAD_REQUEST);
		}
		
		if (user.getSource() == null || user.getBody().equals("") ) {
			return new ResponseEntity<MessagePartie>(user, HttpStatus.PRECONDITION_REQUIRED);
		}
		messDao.save(user);
		
		return new ResponseEntity<MessagePartie>(user, HttpStatus.OK);
		
	}

	@JsonView(Views.MessageWithAll.class)
	@PutMapping("/api/messagespartie")
	public ResponseEntity<MessagePartie> update(@RequestBody MessagePartie user) {
		MessagePartie ch = messDao.findByPrimaryKey(user.getId());		
		if (ch == null) {
			ch = messDao.save(user);
		} else {
			ch = messDao.update(user);
		}
		return new ResponseEntity<MessagePartie>(ch, HttpStatus.OK);
	}

}

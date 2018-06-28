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
import com.rolify.dao.MessagePriveDAO;
import com.rolify.chat.MessagePrive;
import com.rolify.entity.Views;

@CrossOrigin
@RestController
public class MessagePriveController {

	@Autowired
	MessagePriveDAO messDao;

	@JsonView(Views.MessageWithAll.class)
	@GetMapping("/api/messagesprives")
	public ResponseEntity<List<MessagePrive>> findAll() {
		List<MessagePrive> woners = messDao.findAll();
		return new ResponseEntity<List<MessagePrive>>(woners, HttpStatus.OK);
	}

	@JsonView(Views.MessageWithAll.class)
	@GetMapping("/api/messagesprives/{id}")
	public ResponseEntity<MessagePrive> findOne(@PathVariable("id") Integer id) {
		MessagePrive ownr = messDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MessagePrive>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.MessageWithAll.class)
	@DeleteMapping("/api/messagesprives/{id}")
	public ResponseEntity<MessagePrive> delete(@PathVariable("id") Integer id) {
		MessagePrive ownr = messDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		messDao.delete(ownr);
		return new ResponseEntity<MessagePrive>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.MessageWithAll.class)
	@PostMapping("/api/messagesprives")
	public ResponseEntity<MessagePrive> create(@RequestBody MessagePrive user) {
		
		if (user.getId() > 0) {
			return new ResponseEntity<MessagePrive>(user, HttpStatus.BAD_REQUEST);
		}
		
		if (user.getSource() == null || user.getBody().equals("") ) {
			return new ResponseEntity<MessagePrive>(user, HttpStatus.PRECONDITION_REQUIRED);
		}
		messDao.save(user);
		
		return new ResponseEntity<MessagePrive>(user, HttpStatus.OK);
		
	}

	@JsonView(Views.MessageWithAll.class)
	@PutMapping("/api/messagesprives")
	public ResponseEntity<MessagePrive> update(@RequestBody MessagePrive user) {
		MessagePrive ch = messDao.findByPrimaryKey(user.getId());		
		if (ch == null) {
			ch = messDao.save(user);
		} else {
			ch = messDao.update(user);
		}
		return new ResponseEntity<MessagePrive>(ch, HttpStatus.OK);
	}

}

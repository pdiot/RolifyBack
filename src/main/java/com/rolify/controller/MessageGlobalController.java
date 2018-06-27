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
import com.rolify.dao.MessageGlobalDAO;
import com.rolify.chat.MessageGlobal;
import com.rolify.entity.Views;

@CrossOrigin
@RestController
public class MessageGlobalController {

	@Autowired
	MessageGlobalDAO messDao;

	@JsonView(Views.MessageWithSource.class)
	@GetMapping("/api/messagesglobal")
	public ResponseEntity<List<MessageGlobal>> findAll() {
		List<MessageGlobal> woners = messDao.findAll();
		return new ResponseEntity<List<MessageGlobal>>(woners, HttpStatus.OK);
	}

	@JsonView(Views.MessageWithSource.class)
	@GetMapping("/api/messagesglobal/{id}")
	public ResponseEntity<MessageGlobal> findOne(@PathVariable("id") Integer id) {
		MessageGlobal ownr = messDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MessageGlobal>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.MessageWithSource.class)
	@DeleteMapping("/api/messagesglobal/{id}")
	public ResponseEntity<MessageGlobal> delete(@PathVariable("id") Integer id) {
		MessageGlobal ownr = messDao.findByPrimaryKey(id);
		
		if (ownr == null) {
			return new ResponseEntity<>(ownr, HttpStatus.NOT_FOUND);
		}
		messDao.delete(ownr);
		return new ResponseEntity<MessageGlobal>(ownr, HttpStatus.OK);
	}

	@JsonView(Views.MessageWithSource.class)
	@PostMapping("/api/messagesglobal")
	public ResponseEntity<MessageGlobal> create(@RequestBody MessageGlobal user) {
		
		if (user.getId() > 0) {
			return new ResponseEntity<MessageGlobal>(user, HttpStatus.BAD_REQUEST);
		}
		
		if (user.getSource() == null || user.getBody().equals("") ) {
			return new ResponseEntity<MessageGlobal>(user, HttpStatus.PRECONDITION_REQUIRED);
		}
		messDao.save(user);
		
		return new ResponseEntity<MessageGlobal>(user, HttpStatus.OK);
		
	}

	@JsonView(Views.MessageWithSource.class)
	@PutMapping("/api/messagesglobal")
	public ResponseEntity<MessageGlobal> update(@RequestBody MessageGlobal user) {
		MessageGlobal ch = messDao.findByPrimaryKey(user.getId());		
		if (ch == null) {
			System.out.println("caca null");
			ch = messDao.save(user);
		} else {
			System.out.println("caca pas null");
			ch = messDao.update(user);
		}
		return new ResponseEntity<MessageGlobal>(ch, HttpStatus.OK);
	}

}

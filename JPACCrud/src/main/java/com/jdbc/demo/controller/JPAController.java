package com.jdbc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.demo.model.JPAModel;
import com.jdbc.demo.model.Response;
import com.jdbc.demo.service.JPAService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class JPAController {

	@Autowired
	JPAService jpa;

	@PostMapping("/insert")
	public ResponseEntity<Response> createUser(@RequestBody JPAModel values) {
		return ResponseEntity.ok(jpa.createUser(values));

	}

	@GetMapping("/get")
	public ResponseEntity<Response> getUser() {
		return ResponseEntity.ok(jpa.getUser());

	}

	@PutMapping("/update")
	public ResponseEntity<Response> updateUser(@RequestBody JPAModel values) {
		return ResponseEntity.ok(jpa.updateUser(values));
	}

	@GetMapping("/getOne")
	public ResponseEntity<Response> getOneUser() {
		return ResponseEntity.ok(jpa.getOneUser());
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteUser(@RequestParam String sNo) {
		return ResponseEntity.ok(jpa.deleteUser(sNo));
	}

	@PutMapping("/scam")
	public ResponseEntity<Response> scamUser() {
		return ResponseEntity.ok(jpa.scamUser());
	}

	@PostMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestParam String email, String password) {
		return ResponseEntity.ok(jpa.loginUser(email, password));
	}

}

package com.metaltraders.metallica.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metaltraders.metallica.user.entity.User;
import com.metaltraders.metallica.user.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserResource {


	@Autowired
	private UserRepository repository;
	
	@RequestMapping(path = "/user", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, String>> user(Principal principal){
		User currentPerson = repository.findOneByUsername(principal.getName());
		
		Map<String, String> map = new HashMap<>();
		map.put("name", currentPerson.getName());
		
		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
	}
	
	
}

package com.jvilchez.aplicacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jvilchez.aplicacion.entity.User;
import com.jvilchez.aplicacion.service.UserService;

@RestController
@RequestMapping("/api")
public class UserApiController {
	
	@Autowired
	  public UserService userService;
	
	
	@PostMapping("/usuario/crear")
	public ResponseEntity<?>agregarUsuario(@RequestBody User user){
		userService.save(user);
	  return new ResponseEntity<Void>(HttpStatus.CREATED);
	  }
	
	@PostMapping("login")
	public User login(@RequestParam String correo, @RequestParam String clave) throws Exception{
	    
		
		
		User user = userService.findByUsernameAndPassword(correo, clave);
		
		if(user == null)
			throw new Exception("Usuario y/o clave invalido: "+clave);
		return user;
	}

}

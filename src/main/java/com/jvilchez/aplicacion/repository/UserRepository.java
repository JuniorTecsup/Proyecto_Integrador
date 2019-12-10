package com.jvilchez.aplicacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jvilchez.aplicacion.entity.User;


//@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public Optional<User> findByCorreo(String correo);
	
	//@Query("SELECT * FROM user WHERE correo=correo AND clave=clave")
	@Query("SELECT u FROM User u WHERE u.correo=:correo AND u.clave=:clave")
	User findByUsernameAndPassword(@Param("correo") String correo, @Param("clave") String clave);
 }

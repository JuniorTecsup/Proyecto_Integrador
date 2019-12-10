package com.jvilchez.aplicacion.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jvilchez.aplicacion.entity.Enfermedad;


public interface EnfermedadRepository extends CrudRepository<Enfermedad, Long>{
     
	@Override
	List<Enfermedad> findAll();
	
}

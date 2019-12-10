package com.jvilchez.aplicacion.service;

import java.util.List;

import com.jvilchez.aplicacion.entity.Enfermedad;


public interface EnfermedadService {
  
	
    public List<Enfermedad> findAll();
	
	public Enfermedad findById(Long id);
	
	public void save(Enfermedad enfermedad);
	
	public void deleteById(Long id);
    
	//public void updateById(Long id);
	
	

	
}

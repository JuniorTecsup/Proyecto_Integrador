package com.jvilchez.aplicacion.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvilchez.aplicacion.entity.Enfermedad;
import com.jvilchez.aplicacion.repository.EnfermedadRepository;

@Service
@Transactional
public class EnfermedadServicelmpl implements EnfermedadService{
	
	@Autowired
	private EnfermedadRepository enfermedadRepository;
	
	@Override 
	public List<Enfermedad> findAll() {
		return enfermedadRepository.findAll();
	}
    //lista uno solo
	@Override
	public Enfermedad findById(Long id) {
		return enfermedadRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	public void save(Enfermedad enfermedad) {
		enfermedadRepository.save(enfermedad);
	}

	@Override
	public void deleteById(Long id) {
		enfermedadRepository.deleteById(id);
	}

}

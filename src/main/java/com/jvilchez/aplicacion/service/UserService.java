package com.jvilchez.aplicacion.service;

import com.jvilchez.aplicacion.Exception.UsernameOrIdNotFound;
import com.jvilchez.aplicacion.dto.ChangePasswordForm;
import com.jvilchez.aplicacion.entity.User;

public interface UserService {
	
	User findByUsernameAndPassword(String correo, String clave);	

	public Iterable<User> getAllUsers();

	public User createUser(User user) throws Exception;

	public User getUserById(Long id) throws Exception;
	
	public User updateUser(User user) throws Exception;
	
	public void deleteUser(Long id) throws UsernameOrIdNotFound;
	
	public User changePassword(ChangePasswordForm form) throws Exception;
	
	public void save(User user);
}

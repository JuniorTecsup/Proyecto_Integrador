package com.jvilchez.aplicacion.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.jvilchez.aplicacion.Exception.CustomeFieldValidationException;
import com.jvilchez.aplicacion.Exception.UsernameOrIdNotFound;
import com.jvilchez.aplicacion.dto.ChangePasswordForm;
import com.jvilchez.aplicacion.entity.User;
import com.jvilchez.aplicacion.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}
	
	@Override
	public void save(User user) {
		repository.save(user);
	}
	
	@Override
	public User findByUsernameAndPassword(String correo, String clave) {
		//String clave1="$2a$04$tOkXfqhxaZQA3JgGIGO7out4IRH07Q50UGMWdcLALVZd5vBt5OI/e";
		//String clave2=bCryptPasswordEncoder.encode(clave);
		//String clave1=bCryptPasswordEncoder.encode(clave);
		//System.out.println(clave2);
		return repository.findByUsernameAndPassword(correo, clave);
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
	
	private boolean checkUsernameAvailable(User user) throws Exception {
		Optional<User> userFound = repository.findByCorreo(user.getCorreo());
		if (userFound.isPresent()) {
			throw new CustomeFieldValidationException("Usuario no disponible","correo");
		}
		return true;
	}

	private boolean checkPasswordValid(User user) throws Exception {
		if (user.getConfirmClave() == null || user.getConfirmClave().isEmpty()) {
			throw new CustomeFieldValidationException("Confirm Password es obligatorio","confirmClave");
		}
		
		if ( !user.getClave().equals(user.getConfirmClave())) {
			throw new CustomeFieldValidationException("Contrasaeña y Confirmar Contraseña no son iguales","clave");
		}
		return true;
	}


	@Override  //Encriptacion de la clave
	public User createUser(User user) throws Exception {
		if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
			//System.out.println(user.getClave());
			String encodedPassword = bCryptPasswordEncoder.encode(user.getClave());
			//System.out.println(encodedPassword);
			user.setClave(encodedPassword);
			user = repository.save(user);
			
			
		}
		return user;
	}

	@Override
	public User getUserById(Long id) throws UsernameOrIdNotFound {
		return repository.findById(id).orElseThrow(() -> new UsernameOrIdNotFound("El Id del usuario no existe."));
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return repository.save(toUser);
	}
	
	protected void mapUser(User from,User to) {
		to.setNombres(from.getNombres());
		to.setApellidos(from.getApellidos());
		to.setDni(from.getDni());
		to.setEdad(from.getEdad());
		to.setAlergia(from.getAlergia());
		to.setCorreo(from.getCorreo());
		to.setRoles(from.getRoles());
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteUser(Long id) throws UsernameOrIdNotFound {
		User user = getUserById(id);
		repository.delete(user);
	}

	
	
	@Override
	public User changePassword(ChangePasswordForm form) throws Exception {
		User user = getUserById(form.getId());
		
		
		
		if( user.getClave().equals(form.getNewPassword())) {
			throw new Exception ("Nuevo debe ser diferente al password actual.");
		}
		
		if( !form.getNewPassword().equals(form.getConfirmClave())) {
			throw new Exception ("Nuevo Password y Confirm Password no coinciden.");
		}
		
		String encodePassword = bCryptPasswordEncoder.encode(form.getNewPassword());
		user.setClave(encodePassword);
		return repository.save(user);
	}
	
	private boolean isLoggedUserADMIN() {
		//Obtener el usuario logeado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails loggedUser = null;
		Object roles = null;

		//Verificar que ese objeto traido de sesion es el usuario
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;

			roles = loggedUser.getAuthorities().stream()
					.filter(x -> "ROLE_ADMIN".equals(x.getAuthority())).findFirst()
					.orElse(null); 
		}
		return roles != null ? true : false;
	}
	
	private User getLoggedUser() throws Exception {
		//Obtener el usuario logeado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails loggedUser = null;

		//Verificar que ese objeto traido de sesion es el usuario
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		
		User myUser = repository
				.findByCorreo(loggedUser.getUsername()).orElseThrow(() -> new Exception("Error obteniendo el usuario logeado desde la sesion."));
		
		return myUser;
	}
}

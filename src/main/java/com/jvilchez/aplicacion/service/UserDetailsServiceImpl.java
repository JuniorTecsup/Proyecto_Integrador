package com.jvilchez.aplicacion.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvilchez.aplicacion.entity.Role;
import com.jvilchez.aplicacion.repository.UserRepository;
 
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		
		com.jvilchez.aplicacion.entity.User appUser = userRepository.findByCorreo(correo).orElseThrow(() -> new UsernameNotFoundException("Login Username Invalido."));
		
		Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>(); 
		for (Role role: appUser.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescription());
            grantList.add(grantedAuthority);
		}
		UserDetails user = (UserDetails) new User(correo,appUser.getClave(),grantList);
		
		return user;
	}

}

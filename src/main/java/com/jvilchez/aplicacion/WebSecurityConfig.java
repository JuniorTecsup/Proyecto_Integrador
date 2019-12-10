package com.jvilchez.aplicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jvilchez.aplicacion.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends  WebSecurityConfigurerAdapter{

	String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
    };
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
        .antMatchers(resources).permitAll()  
        .antMatchers("/","/index","/enfermedades/listapi","/api/login","/api/insertar","/signup","/enfermedades/lista","/enfermedades/nuevo","/enfermedades/crear","/enfermedades/editar/{id}","/enfermedades/actualizar","/enfermedades/borrar/{id}","/api/enfermedades","/api/enfermedad/{id}","/api/enfermedad/crear","/api/enfermedad/eliminar/{id}","/api/enfermedad/editar/{id}","auth/login").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/enfermedades/listauser")//userForm
            .failureUrl("/login?error=true")
            .usernameParameter("correo")
            .passwordParameter("clave")//rara exception..Clave 
            .and()
            .csrf().disable()
        .logout()
            .permitAll()
            .logoutSuccessUrl("/login?logout");
    }
	
	BCryptPasswordEncoder bCryptPasswordEncoder;

 
    
  
    
}

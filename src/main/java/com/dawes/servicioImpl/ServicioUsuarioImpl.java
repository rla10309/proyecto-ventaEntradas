package com.dawes.servicioImpl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dawes.modelo.UsuarioVO;
import com.dawes.repositorio.UsuarioRepositorio;

@Service
public class ServicioUsuarioImpl implements UserDetailsService {
	
	@Autowired
	UsuarioRepositorio ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(ur.findByUsername(username) == null)
			throw new UsernameNotFoundException("User not found with username " + username);
		
			
		return ur.findByUsername(username);
	}
	
	public UsuarioVO newUser(UsuarioVO usuario) {
		 return ur.save(usuario);
	}

	
	 

	
	

}

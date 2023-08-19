package com.dawes.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.dawes.modelo.UsuarioVO;

public interface UsuarioRepositorio extends CrudRepository<UsuarioVO, Integer> {
	
	UserDetails findByUsername(String username);

}

package com.dawes.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dawes.modelo.UsuarioRolVO;

public interface UsuarioRolRepositorio extends CrudRepository<UsuarioRolVO, Integer> {
	Optional<UsuarioRolVO> findByRol(String nombre);

}

package com.dawes.servicio;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dawes.modelo.UsuarioRolVO;

@Service
public interface ServicioUsuarioRol {

	Optional<UsuarioRolVO> findByRol(String nombre);

	Optional<UsuarioRolVO> findById(Integer id);

	Iterable<UsuarioRolVO> findAll();

	<S extends UsuarioRolVO> S save(S entity);

}
package com.dawes.servicioImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.UsuarioRolVO;
import com.dawes.repositorio.UsuarioRolRepositorio;
import com.dawes.servicio.ServicioUsuarioRol;

@Service
public class ServicioUsuarioRolImpl implements ServicioUsuarioRol {

	@Autowired
	UsuarioRolRepositorio urr;

	@Override
	public Optional<UsuarioRolVO> findByRol(String nombre) {
		return urr.findByRol(nombre);
	}

	@Override
	public Optional<UsuarioRolVO> findById(Integer id) {
		return urr.findById(id);
	}

	@Override
	public Iterable<UsuarioRolVO> findAll() {
		return urr.findAll();
	}

	public <S extends UsuarioRolVO> S save(S entity) {
		return urr.save(entity);
	}
	
	
	
}

package com.dawes.servicioImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.RolVO;
import com.dawes.repositorio.RolRepositorio;
import com.dawes.servicio.ServicioRol;

@Service
public class ServicioRolImpl implements ServicioRol {
	@Autowired
	RolRepositorio rr;

	@Override
	public Optional<RolVO> findByNombre(String nombre) {
		return rr.findByNombre(nombre);
	}

	@Override
	public Optional<RolVO> findById(Integer id) {
		return rr.findById(id);
	}

	@Override
	public Iterable<RolVO> findAll() {
		return rr.findAll();
	}
	
	

}

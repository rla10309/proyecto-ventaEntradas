package com.dawes.servicio;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dawes.modelo.RolVO;

@Service
public interface ServicioRol {

	Optional<RolVO> findByNombre(String nombre);

	Optional<RolVO> findById(Integer id);

	Iterable<RolVO> findAll();

}
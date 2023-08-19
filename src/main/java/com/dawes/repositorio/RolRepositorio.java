package com.dawes.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dawes.modelo.RolVO;

public interface RolRepositorio extends CrudRepository<RolVO, Integer>{
	Optional<RolVO> findByNombre(String nombre);

}

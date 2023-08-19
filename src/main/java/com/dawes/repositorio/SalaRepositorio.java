package com.dawes.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.SalaVO;

@Repository
public interface SalaRepositorio extends CrudRepository<SalaVO, Integer> {
	public Optional<SalaVO> findByNombre(String nombre);
	@Query("select s from SalaVO s join fetch s.conciertos where s.nombre = ?1")
	public Optional<SalaVO> findConciertosByNombre(String nombre);

}

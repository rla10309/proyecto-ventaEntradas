package com.dawes.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.ConciertoVO;

@Repository
public interface ConciertoRepositorio extends CrudRepository<ConciertoVO, Integer> {
	
	public Optional<ConciertoVO> findByGrupoAndFecha(String grupo, LocalDate fecha);
	
	public Optional<ConciertoVO> findByGrupo(String grupo);
	
	@Query("select c from ConciertoVO c join fetch c.ventas where c.grupo = ?1")
	public Optional<ConciertoVO> findVentasByConciertoAndGrupo(String grupo);
	
	public Optional<List<ConciertoVO>> findByGrupoAndFechaBetween(String grupo, LocalDate inicio, LocalDate fin);
	
	public Optional<List<ConciertoVO>> findByFechaBetween(LocalDate inicio, LocalDate fin);

}

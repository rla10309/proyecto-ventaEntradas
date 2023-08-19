package com.dawes.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dawes.modelo.ConciertoVO;

public interface ServicioConcierto {
	
	Optional<ConciertoVO> findByGrupo(String grupo);

	Optional<List<ConciertoVO>> findByFechaBetween(LocalDate inicio, LocalDate fin);

	Optional<ConciertoVO> findByGrupoAndFecha(String grupo, LocalDate fecha);
	 
	Optional<List<ConciertoVO>> findByGrupoAndFechaBetween(String grupo, LocalDate inicio, LocalDate fin);
	
	Optional<ConciertoVO> findVentasByConciertoAndGrupo(String grupo);

	<S extends ConciertoVO> S save(S entity);

	<S extends ConciertoVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<ConciertoVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<ConciertoVO> findAll();

	Iterable<ConciertoVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(ConciertoVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends ConciertoVO> entities);

	void deleteAll();

}
package com.dawes.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.VentaVO;

public interface ServicioVenta {
	
	
	Optional<List<VentaVO>> findVentasByGrupoAndFechaBetween(String grupo, LocalDate inicio, LocalDate fin);
	//Optional<List<VentaVO>> findVentasByConcierto(int idconcierto);
	Optional<List<VentaVO>> findVentasByConcierto(ConciertoVO c);

	 Optional<List<VentaVO>> findByDni(String dni);

	<S extends VentaVO> S save(S entity);

	<S extends VentaVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<VentaVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<VentaVO> findAll();

	Iterable<VentaVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(VentaVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends VentaVO> entities);

	void deleteAll();

}
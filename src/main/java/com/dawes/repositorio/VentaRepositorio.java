package com.dawes.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.VentaVO;

@Repository
public interface VentaRepositorio extends CrudRepository<VentaVO, Integer> {
	Optional<List<VentaVO>> findByDni(String dni);
	
	@Query("select v from VentaVO v where v.concierto = ?1")
	Optional<List<VentaVO>> findVentasByConcierto(ConciertoVO c);
	
	@Query("select v from VentaVO v INNER JOIN ConciertoVO c ON v.concierto.idconcierto = c.idconcierto and c.grupo =?1 and v.fechaventa between ?2 and ?3")
	Optional<List<VentaVO>> findVentasByGrupoAndFechaBetween(String grupo, LocalDate inicio, LocalDate fin);

}

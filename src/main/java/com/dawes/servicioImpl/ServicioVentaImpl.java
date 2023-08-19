package com.dawes.servicioImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.VentaVO;
import com.dawes.repositorio.ConciertoRepositorio;
import com.dawes.repositorio.VentaRepositorio;
import com.dawes.servicio.ServicioVenta;

import jakarta.transaction.Transactional;

@Service
public class ServicioVentaImpl implements ServicioVenta {
	@Autowired
	VentaRepositorio vr;
	@Autowired
	ConciertoRepositorio cr;

	public Optional<List<VentaVO>> findVentasByGrupoAndFechaBetween(String grupo, LocalDate inicio, LocalDate fin) {
		return vr.findVentasByGrupoAndFechaBetween(grupo, inicio, fin);
	}


	public Optional<List<VentaVO>> findVentasByConcierto(ConciertoVO c) {
		return vr.findVentasByConcierto(c);
	}


	public Optional<List<VentaVO>> findByDni(String dni) {
		return vr.findByDni(dni);
	}


	@Transactional
	@Override
	public <S extends VentaVO> S save(S entity) throws DataIntegrityViolationException{
		//Recuperamos el concierto para el se hace la venta
		ConciertoVO c = entity.getConcierto();
		//le asignamos la diferencia entre las plazas del concierto y el número de entradas vendidas
		try {
		if(entity.getIdventa() == 0 ) {
			c.setPlazas(entity.getConcierto().getPlazas() - entity.getNumeroentradas());
		//A través del repositorio del concierto, actualizamos la tabla con los nuevos datos
			cr.save(c);
			
		}
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error en la venta");
		}
		return vr.save(entity);
		
		
	}
	
	@Transactional
	@Override
	public void delete(VentaVO entity) {
		/*En caso de que una venta sea eliminada por el administrador, el número de entradas vendidas se sumara a las plazas del concierto*/
		ConciertoVO c = entity.getConcierto();
		c.setPlazas(entity.getConcierto().getPlazas() + entity.getNumeroentradas());
		cr.save(c);
		
		vr.delete(entity);
	}

	@Override
	public <S extends VentaVO> Iterable<S> saveAll(Iterable<S> entities) {
		return vr.saveAll(entities);
	}

	@Override
	public Optional<VentaVO> findById(Integer id) {
		return vr.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return vr.existsById(id);
	}

	@Override
	public Iterable<VentaVO> findAll() {
		return vr.findAll();
	}

	@Override
	public Iterable<VentaVO> findAllById(Iterable<Integer> ids) {
		return vr.findAllById(ids);
	}

	@Override
	public long count() {
		return vr.count();
	}

	@Override
	public void deleteById(Integer id) {
		vr.deleteById(id);
	}



	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		vr.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends VentaVO> entities) {
		vr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		vr.deleteAll();
	}

}

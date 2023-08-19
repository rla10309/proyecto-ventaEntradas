package com.dawes.servicioImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.SalaVO;
import com.dawes.repositorio.SalaRepositorio;
import com.dawes.servicio.ServicioSala;

@Service
public class ServicioSalaImpl implements ServicioSala {

	@Autowired
	SalaRepositorio sr;

	@Override
	public Optional<SalaVO> findByNombre(String nombre) {
		return sr.findByNombre(nombre);
	}

	public Optional<SalaVO> findConciertosByNombre(String nombre) {
		List<ConciertoVO> conciertos = sr.findConciertosByNombre(nombre).get().getConciertos();
		for (ConciertoVO c : conciertos) {

			System.out.println("Grupo: " + c.getGrupo());
			System.out.println("Fecha y hora: " + c.getFecha() + " - " + c.getHora());
			System.out.println("Plazas disponibles: " + c.getPlazas());
			System.out.println("---------------------------------------------");
		}
		return sr.findConciertosByNombre(nombre);
	}

	@Override
	public <S extends SalaVO> S save(S entity) {
		try {
			sr.save(entity);
		}catch (DataIntegrityViolationException e) {
			System.out.println("Esa sala ya existe");
			return entity;
		}
		return entity;
	}
	@Override
	public void delete(SalaVO entity) {
		try {
		sr.delete(entity);
		}catch (DataIntegrityViolationException e) {
			System.out.println("No podemos eliminar esa sala. Tiene conciertos pendientes");
		}
	}


	@Override
	public <S extends SalaVO> Iterable<S> saveAll(Iterable<S> entities) {
		return sr.saveAll(entities);
	}

	@Override
	public Optional<SalaVO> findById(Integer id) {
		return sr.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return sr.existsById(id);
	}

	@Override
	public Iterable<SalaVO> findAll() {
		return sr.findAll();
	}

	@Override
	public Iterable<SalaVO> findAllById(Iterable<Integer> ids) {
		return sr.findAllById(ids);
	}

	@Override
	public long count() {
		return sr.count();
	}

	@Override
	public void deleteById(Integer id) {
		sr.deleteById(id);
	}


	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		sr.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends SalaVO> entities) {
		sr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		sr.deleteAll();
	}

}

package com.dawes;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.SalaVO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioSala;
import com.dawes.servicio.ServicioVenta;

@SpringBootTest
class ApplicationTests {
	@Autowired
	ServicioSala ss;
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioVenta sv;

	// Inserta una sala
	@Test
	public void test01() {
		assertNotNull(ss.save(new SalaVO("Sala Corazón", "C/La Luz", 300, new ArrayList<ConciertoVO>())));
	}

	// Inserta un concierto
	@Test
	public void test02() {
		sc.save(new ConciertoVO("Queen", LocalDate.of(2023, 6, 1), LocalTime.of(22, 30), 40d, 50d, 300,
				ss.findByNombre("Sala Corazón").get(), new ArrayList<VentaVO>()));
		sc.save(new ConciertoVO("Queen", LocalDate.of(2023, 6, 7), LocalTime.of(22, 30), 40d, 50d, 300,
				ss.findByNombre("Sala Corazón").get(), new ArrayList<VentaVO>()));
		assertNotNull(sc.save(new ConciertoVO("Nirvana", LocalDate.of(2023, 7, 7), LocalTime.of(22, 30), 40d, 50d, 300,
				ss.findByNombre("Sala Corazón").get(), new ArrayList<VentaVO>())));
	}

	// Inserta un concierto con mismo grupo y misma fecha
	@Test
	public void test03() {
		try {
			assertNotNull(sc.save(new ConciertoVO("Queen", LocalDate.of(2023, 6, 1), LocalTime.of(22, 30), 40d, 50d,
					300, ss.findByNombre("Sala Corazón").get(), new ArrayList<VentaVO>())));
		} catch (DataIntegrityViolationException e) {
			System.out.println("Fecha ocupada");
		}

	}

	// Inserta ventas
	@Test
	public void test04() {
		sv.save(new VentaVO("111", 2,  LocalDate.of(2023, 4, 12), LocalTime.of(14, 22), sc.findByGrupoAndFecha("Queen", LocalDate.of(2023, 6, 1)).get()));
		sv.save(new VentaVO("111", 2, LocalDate.of(2023, 4, 14), LocalTime.of(12, 20), sc.findByGrupoAndFecha("Queen", LocalDate.of(2023, 6, 7)).get()));
		assertNotNull(sv.save(new VentaVO("222", 2, LocalDate.of(2023, 6, 12), LocalTime.of(21, 22), sc.findByGrupoAndFecha("Queen", LocalDate.of(2023, 6, 1)).get())));
	}


	// Comprobar que las plazas de un concierto se actualizan con las ventas
	@Test
	public void test05() {
		assertEquals(298, sc.findByGrupoAndFecha("Queen", LocalDate.of(2023, 6, 7)).get().getPlazas());
	}

	// Listado de ventas por concierto
	@Test
	public void test06() {
		ConciertoVO c = sc.findById(2).get();
		List<VentaVO> ventas = sv.findVentasByConcierto(c).get();
		assertEquals(2, ventas.size());
	}

	// Listado de ventas por grupo entre fechas
	@Test
	public void test07() {
		assertEquals(2,
				sv.findVentasByGrupoAndFechaBetween("Queen", 
						LocalDate.of(2023, 4, 1), LocalDate.of(2023,4 , 30)).get().size());
	}

	// Conciertos de un grupo entre fechas
	@Test
	public void test08() {
		assertEquals(2, sc.findByGrupoAndFechaBetween("Queen", 
				LocalDate.of(2023, 5, 27), LocalDate.of(2023, 6, 10)).get().size());
	}

	// Consulta de ventas por DNI del comprador
	@Test
	public void test09() {
		assertEquals(2, sv.findByDni("222").get().size());
	}

	// Borra un concierto con ventas
	@Test
	public void test10() {
		sc.delete(sc.findById(1).get());
		assertTrue(sc.findById(1).isPresent());
	}

	// Borra un concierto sin ventas
	@Test
	public void test11() {
		sc.delete(sc.findById(3).get());
		assertTrue(sc.findById(3).isEmpty());

	}

}

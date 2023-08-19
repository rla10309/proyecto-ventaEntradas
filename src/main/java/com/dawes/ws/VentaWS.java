package com.dawes.ws;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.dto.VentaDTO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioSala;
import com.dawes.servicio.ServicioVenta;

@RequestMapping("/venta")
@RestController
public class VentaWS {
	@Autowired
	ServicioVenta sv;
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioSala ss;
	
	
	@PostMapping("/inserta")
	public ResponseEntity<?> insertaVenta(@RequestBody VentaDTO venta){
		VentaVO nuevaVenta = new VentaVO(venta.getDni(),  venta.getNumeroentradas(),
				sc.findById(venta.getIdconcierto()).get());
		try {
		sv.save(nuevaVenta);
		return new ResponseEntity<>("Venta realizada con éxito", HttpStatus.OK);
		} catch (DataIntegrityViolationException e ) {
				return new ResponseEntity<>("Esta venta es nula", HttpStatus.BAD_REQUEST);
			} 
		}
		
	}
	
	



package com.dawes.ws;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.dto.ConciertoDTO;
import com.dawes.modelo.ConciertoVO;
import com.dawes.servicio.ServicioConcierto;

@RestController
public class ConciertoWS {

	@Autowired
	ServicioConcierto sc;

	/*Consulta entre fechas los conciertos de la sala*/
	@Secured("ROLE_ADMIN")
	@GetMapping("/concierto/{inicio}/{fin}")
	public ResponseEntity<?> findByFechaBetween(@PathVariable LocalDate inicio, @PathVariable LocalDate fin) {
		try {
			List<ConciertoDTO> conciertosDTO = new ArrayList<ConciertoDTO>();
			/*Recupera los conciertos de la sala*/
			List<ConciertoVO> conciertosVO = sc.findByFechaBetween(inicio, fin).get();
			for (ConciertoVO c : conciertosVO) {
				conciertosDTO.add(new ConciertoDTO(c.getGrupo(), c.getFecha(), c.getHora(), c.getPrecioanticipado(),
						c.getPreciotaquilla(), c.getPlazas(), c.getSala().getIdsala()));
			}
			return new ResponseEntity<List<ConciertoDTO>>(conciertosDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la búsqueda", HttpStatus.BAD_REQUEST);
		}
	}
	
	/*Modifica el concierto de un grupo en una fecha concreta*/
	@PutMapping("/concierto/{grupo}/{fecha}")
	public ResponseEntity<?> updateConcierto(@PathVariable String grupo, @PathVariable LocalDate fecha, @RequestBody ConciertoVO concierto){
		ConciertoVO c = sc.findByGrupoAndFecha(grupo, fecha).get();
		c.setHora(concierto.getHora());
		sc.save(c);
		return new ResponseEntity<>("Modificación efectuada", HttpStatus.OK);
	}
	

	

}

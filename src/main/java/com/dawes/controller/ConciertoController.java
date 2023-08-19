package com.dawes.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.ConciertoVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioSala;

@Controller
@RequestMapping("/concierto")
public class ConciertoController {
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioSala ss;

	@RequestMapping("/listadoconciertos")
	public String listadoconciertos(Model modelo) {
		modelo.addAttribute("conciertos", sc.findAll());
		modelo.addAttribute("salas", ss.findAll());
		return "admin/concierto/listadoconciertos";
	}

	@RequestMapping("/forminsertar")
	public String forminsertar(Model modelo) {
		modelo.addAttribute("concierto", new ConciertoVO());
		modelo.addAttribute("salas", ss.findAll());
		return "admin/concierto/forminsertar";
	}

	@RequestMapping("/formbuscar")
	public String formbuscar(Model modelo) {
		modelo.addAttribute("conciertos", sc.findAll());
		return "admin/concierto/formbuscar";
	}

	@RequestMapping("/insertar")
	public String insertar(@ModelAttribute ConciertoVO concierto) {
		try {
			sc.save(concierto);
			return "redirect:/concierto/listadoconciertos";
		} catch (DataIntegrityViolationException e) {
			return "redirect:/concierto/forminsertar?error=true";
		}

	}

	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam int idconcierto, Model modelo) {
		ConciertoVO c = sc.findById(idconcierto).get();
		if (c.getVentas().size() == 0) {
			sc.delete(c);
		} else {
			modelo.addAttribute("msgError", "Ese concierto tiene ventas asociadas");
			//return "redirect:/concierto/listadoconciertos?error=true";
		}

		return "admin/concierto/listadoconciertos";
	}

	@RequestMapping("/modificar")
	public String modificar(@RequestParam int idconcierto, Model modelo) {

		modelo.addAttribute("concierto", sc.findById(idconcierto).get());
		modelo.addAttribute("conciertos", sc.findAll());
		modelo.addAttribute("salas", ss.findAll());

		return "admin/concierto/formeditar";
	}

	@RequestMapping("/buscarporgrupoandfecha")
	public String buscarporgrupoandfecha(@RequestParam String grupo, @RequestParam LocalDate inicio,
			@RequestParam LocalDate fin, Model modelo) {

		List<ConciertoVO> conciertos = sc.findByGrupoAndFechaBetween(grupo, inicio, fin).get();

		if (conciertos.size() == 0) {
			modelo.addAttribute("msgError", "No hay datos que mostrar");
			//return "redirect:/concierto/listadoconciertos?error=true";
			//return "admin/concierto/listadoconciertos";
		} else {

			modelo.addAttribute("conciertos", conciertos);
		}
		return "admin/concierto/listadoconciertos";

	}

}

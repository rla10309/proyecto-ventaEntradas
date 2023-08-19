package com.dawes.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioSala;
import com.dawes.servicio.ServicioVenta;

@Controller
@RequestMapping("/venta")
public class VentaController {
	@Autowired
	ServicioVenta sv;
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioSala ss;

	@RequestMapping("/listadoventas")
	public String listadoventas(Model modelo) {
		modelo.addAttribute("ventas", sv.findAll());
		modelo.addAttribute("conciertos", sc.findAll());
		return "admin/venta/listadoventas";
	}

	@RequestMapping("/forminsertar")
	public String forminsertar(Model modelo) {
		modelo.addAttribute("venta", new VentaVO());
		modelo.addAttribute("conciertos", sc.findAll());
		return "admin/venta/forminsertar";
	}

	@RequestMapping("/insertar")
	public String insertar(@ModelAttribute VentaVO venta) {
		try {
			sv.save(venta);
		} catch (DataIntegrityViolationException e) {
			return "redirect:/venta/forminsertar?error=true";
		}
		return "redirect:/venta/listadoventas";
	}

	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam int idventa) {
		sv.delete(sv.findById(idventa).get());
		return "redirect:/venta/listadoventas";
	}

	@RequestMapping("/modificar")
	public String modificar(@RequestParam int idventa, Model modelo) {
		modelo.addAttribute("venta", sv.findById(idventa).get());
		modelo.addAttribute("conciertos", sc.findAll());
		return "admin/venta/formeditar";
	}

	@RequestMapping("/buscarventas")
	public String buscarventas(@RequestParam int idconcierto, Model modelo) {
		ConciertoVO c = sc.findById(idconcierto).get();
		List<VentaVO> listado = sv.findVentasByConcierto(c).get();
		modelo.addAttribute("ventas", listado);
		return "admin/venta/informeventas";
	}

	@RequestMapping("/formbuscadni")
	public String formbuscadni() {
		return "admin/venta/formbusca_dni";
	}

	@RequestMapping("/buscarpordni")
	public String buscarpordni(@RequestParam String dni, Model modelo) {
		modelo.addAttribute("ventas", sv.findByDni(dni).get());
		return "admin/venta/informeventas";
	}

	@RequestMapping("/buscarporgrupoandfecha")
	public String buscarporgrupoandfecha(@RequestParam String grupo, @RequestParam LocalDate inicio,
			@RequestParam LocalDate fin, Model modelo) {

		modelo.addAttribute("ventas", sv.findVentasByGrupoAndFechaBetween(grupo, inicio, fin).get());
		return "admin/venta/informeventas";
	}

}

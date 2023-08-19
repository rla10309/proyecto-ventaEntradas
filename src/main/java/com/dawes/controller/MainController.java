package com.dawes.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.RolVO;
import com.dawes.modelo.UsuarioRolVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioRol;
import com.dawes.servicio.ServicioSala;
import com.dawes.servicio.ServicioUsuarioRol;
import com.dawes.servicio.ServicioVenta;
import com.dawes.servicioImpl.ServicioUsuarioImpl;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioSala ss;
	@Autowired
	ServicioVenta sv;
	
	
	@RequestMapping({"/", "/index"})
	public String index() {
		return "index";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "/admin/admin";
	}
	@RequestMapping("/user")
	public String user(Model modelo) {
		modelo.addAttribute("conciertos", sc.findAll());
		modelo.addAttribute("salas", ss.findAll());
		return "/user/user";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
//	@RequestMapping("/logout")
//	public String logout() {
//		return "logout";
//	}
	@RequestMapping("/403")
	public String error() {
		return "error/403";
	}
	@RequestMapping("/salir")
	public String salir(HttpServletRequest request) {
		request.getSession(true).invalidate();
		return "index";
	}
	
	/*acciones solo para el user*/
	@RequestMapping("/formbuscar")
	public String formbuscar(Model modelo) {
		modelo.addAttribute("conciertos", sc.findAll());
		return "user/formbuscar";
	}
	@RequestMapping("/buscarporgrupoandfecha")
	public String buscarporgrupoandfecha(@RequestParam String grupo, @RequestParam LocalDate inicio,
			@RequestParam LocalDate fin, Model modelo) {

		List<ConciertoVO> conciertos = sc.findByGrupoAndFechaBetween(grupo, inicio, fin).get();

		if (conciertos.size() == 0) {
			return "redirect:/user?error=true";
		} else {

			modelo.addAttribute("conciertos", conciertos);
		}
		return "user/user";

	}
	@RequestMapping("/forminsertar")
	public String forminsertar(Model modelo) {
		modelo.addAttribute("venta", new VentaVO());
		modelo.addAttribute("conciertos", sc.findAll());
		return "user/forminsertar";
	}
	@RequestMapping("/insertar")
	public String insertar(@ModelAttribute VentaVO venta) {
		try {
			sv.save(venta);
		} catch (DataIntegrityViolationException e) {
			return "redirect:/forminsertar?error=true";
		}
		return "user/ticket";
	}
	
	

		 


}

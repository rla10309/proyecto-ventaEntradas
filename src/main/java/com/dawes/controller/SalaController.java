package com.dawes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.SalaVO;
import com.dawes.servicio.ServicioSala;


@Controller
@RequestMapping("/sala")
public class SalaController {
	
	@Autowired
	ServicioSala ss;
	
	@RequestMapping("/listadosalas")
	public String listadosalas(Model modelo) {
		modelo.addAttribute("salas", ss.findAll());
		return "admin/sala/listadosalas";
	}
	
	@RequestMapping("/forminsertar")
	public String forminsertar(Model modelo) {
		modelo.addAttribute("sala", new SalaVO());
		return "admin/sala/forminsertar";
	}
	
	@RequestMapping("/insertar")
	public String insertar(@ModelAttribute SalaVO sala) {
		ss.save(sala);
		return "redirect:/sala/listadosalas";
	}
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam int idsala) {
		SalaVO sala = ss.findById(idsala).get();
		if(sala.getConciertos().size() == 0) {
			ss.delete(sala);
		} else {
			
			return "redirect:/sala/listadosalas?error=true";
		} 
		return "redirect:/sala/listadosalas";
			
		
	}
	
	
	@RequestMapping("/modificar")
	public String modificar(@RequestParam int idsala, Model modelo) {
		modelo.addAttribute("sala", ss.findById(idsala).get());
		return "admin/sala/formeditar";
		
		
	}
	

}

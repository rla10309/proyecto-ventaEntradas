package com.dawes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dawes.modelo.RolVO;
import com.dawes.modelo.UsuarioRolVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicio.ServicioRol;
import com.dawes.servicio.ServicioUsuarioRol;
import com.dawes.servicioImpl.ServicioUsuarioImpl;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	ServicioUsuarioImpl su;
	@Autowired
	ServicioUsuarioRol sur;
	@Autowired
	ServicioRol sr;
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@RequestMapping("/formregistro")
	public String registrar(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioVO());
		return "formregistro";
	}
	@RequestMapping("/registrarusuario")
	public String salvar(@ModelAttribute UsuarioVO usuario) {
		 RolVO r = sr.findByNombre("ROLE_USER").get();
		 usuario.setPassword(encoder.encode(usuario.getPassword()));
		 su.newUser(usuario);
		 UsuarioRolVO usuariorol = new UsuarioRolVO(usuario, r);
		 sur.save(usuariorol);
		 return "login";
	}

}

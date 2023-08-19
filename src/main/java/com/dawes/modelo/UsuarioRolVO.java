package com.dawes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuariorol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRolVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusuariorol;
	@ManyToOne
	@JoinColumn(name="idusuario")
	private UsuarioVO usuario;
	@ManyToOne
	@JoinColumn(name="idrol")
	private RolVO rol;
	public UsuarioRolVO(UsuarioVO usuario, RolVO rol) {
		super();
		this.usuario = usuario;
		this.rol = rol;
	}
	
	

}

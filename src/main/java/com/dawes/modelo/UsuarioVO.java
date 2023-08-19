package com.dawes.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//******************************
//https://somospnt.com/blog/162-maneja-tus-usuarios-y-sus-roles-con-spring-security
//*************************************************************
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioVO implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusuario;
	private String username;
	private String password;
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<UsuarioRolVO> roles;
	
	
	
	

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> privilegios = new ArrayList<>();
		for (UsuarioRolVO r : roles) {
			privilegios.add(new SimpleGrantedAuthority(r.getRol().getNombre()));
		}

		return privilegios;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public UsuarioVO(String username, String password, List<UsuarioRolVO> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

}

package com.dawes.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salas", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class SalaVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idsala;
	private String nombre;
	private String direccion;
	private int aforo;

	@OneToMany(mappedBy = "sala", fetch = FetchType.LAZY)
	private List<ConciertoVO> conciertos;

	public SalaVO(String nombre, String direccion, int aforo, List<ConciertoVO> conciertos) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.aforo = aforo;
		this.conciertos = conciertos;
	}

	@Override
	public String toString() {
		return "SalaVO [idsala=" + idsala + ", nombre=" + nombre + ", direccion=" + direccion + ", aforo=" + aforo
				+ "]";
	}
	

}

package com.dawes.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "conciertos", uniqueConstraints = @UniqueConstraint(columnNames = {"grupo", "fecha"}))
public class ConciertoVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idconcierto;
	private String grupo;
	private LocalDate fecha;
	private LocalTime hora;
	private double precioanticipado;
	private double preciotaquilla;
	private int plazas;
	@ManyToOne
	@JoinColumn(name = "idsala", foreignKey = @ForeignKey(name = "FK_idsala"))
	private SalaVO sala;

	@OneToMany(mappedBy = "concierto", fetch = FetchType.EAGER)
	private List<VentaVO> ventas;



	@Override
	public String toString() {
		return "ConciertoVO [idconcierto=" + idconcierto + ", grupo=" + grupo + ", fecha=" + fecha + ", hora=" + hora
				+ ", precioanticipado=" + precioanticipado + ", preciotaquilla=" + preciotaquilla + ", plazas=" + plazas
				+ ", sala=" + sala + "]";
	}



	public ConciertoVO(String grupo, LocalDate fecha, LocalTime hora, double precioanticipado, double preciotaquilla,
			int plazas, SalaVO sala, List<VentaVO> ventas) {
		super();
		this.grupo = grupo;
		this.fecha = fecha;
		this.hora = hora;
		this.precioanticipado = precioanticipado;
		this.preciotaquilla = preciotaquilla;
		this.plazas = plazas;
		this.sala = sala;
		this.ventas = ventas;
	}


}

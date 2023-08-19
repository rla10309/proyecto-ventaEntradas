package com.dawes.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@Entity
@Table(name = "ventas", uniqueConstraints = @UniqueConstraint(columnNames = {"dni", "fechaventa", "horaventa"}))
public class VentaVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idventa;
	private String dni;
	private int numeroentradas;
	private LocalDate fechaventa;
	private LocalTime horaventa;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "idconcierto", foreignKey = @ForeignKey(name = "FK_idconcierto"))
	private ConciertoVO concierto;

	/*ESTE CONSTRUCTOR SE UTILIZA PARA COMPROBAR LAS CONSULTAS CON FECHAS*/
	public VentaVO(String dni, int numeroentradas, LocalDate fechaventa, LocalTime horaventa, ConciertoVO concierto) {
		super();
		this.dni = dni;
		this.numeroentradas = numeroentradas;
		this.fechaventa = fechaventa;
		this.horaventa = horaventa;
		this.concierto = concierto;
	}
	
	/*CON ESOS CONSTRUCTORES INSERTAMOS LA VENTA ACTUAL POR DEFECTO*/
	public VentaVO() {
		this.fechaventa = LocalDate.now();
		this.horaventa = LocalTime.now();
	}
	public VentaVO(String dni, int numeroentradas, ConciertoVO concierto) {
		super();
		this.dni = dni;
		this.numeroentradas = numeroentradas;
		this.fechaventa = LocalDate.now();
		this.horaventa = LocalTime.now();
		this.concierto = concierto;
	}
	
}

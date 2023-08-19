package com.dawes.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConciertoDTO {
	private String grupo;
	private LocalDate fecha;
	private LocalTime hora;
	private double precioanticipado;
	private double preciotaquilla;
	private int plazas;
	private int idsala;
	

}

package com.dawes.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
	private String dni;
	private int numeroentradas;
	private LocalDate fechaventa;
	private LocalTime horaventa;
	private int idconcierto;

}

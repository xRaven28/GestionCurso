package com.gestionCurso.GestionCurso.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CrearCurso {
    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotBlank
    private Integer creadoPorId;

    @Min(1)
    private Integer duracionHoras;

    @DecimalMin("0.0")
    private BigDecimal precio;

    @Min(1)
    private Integer capacidadMaxima;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;
}

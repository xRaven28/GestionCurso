package com.gestionCurso.GestionCurso.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.gestionCurso.GestionCurso.Enum.EstadoCurso;
import lombok.Data;

@Data
public class CursoDTO {
    private Long id;             
    private String nombre;
    private String descripcion;
    private Integer creadoPorId;
    private Integer duracionHoras;
    private BigDecimal precio;
    private Integer capacidadMaxima;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoCurso estado;  
}

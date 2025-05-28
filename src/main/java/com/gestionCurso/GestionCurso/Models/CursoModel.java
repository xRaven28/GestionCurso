package com.gestionCurso.GestionCurso.Models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.antlr.v4.runtime.misc.NotNull;
import com.gestionCurso.GestionCurso.Enum.EstadoCurso;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "cursos")
@Data
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotNull
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

    @Enumerated(EnumType.STRING)
    private EstadoCurso estado = EstadoCurso.BORRADOR;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorarioModel> horarios;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialCursoModel> materiales;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstudianteCursoModel> inscripciones;
}

package com.gestionCurso.GestionCurso.Models;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "inscripciones")
@Data
public class EstudianteCursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idEstudiante;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoModel curso;

    private LocalDate fechaInscripcion;

    @NotBlank
    private String estado;
}

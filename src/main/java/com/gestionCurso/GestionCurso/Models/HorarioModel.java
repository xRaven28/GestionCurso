package com.gestionCurso.GestionCurso.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "horarios")
@Data
public class HorarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String dia;

    @NotBlank
    private String horaInicio;

    @NotBlank
    private String horaFin;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoModel curso;
}

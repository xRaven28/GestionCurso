package com.gestionCurso.GestionCurso.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Table(name = "materiales")
@Data
public class MaterialCursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String tipo;

    @NotBlank
    private String url;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoModel curso;
}

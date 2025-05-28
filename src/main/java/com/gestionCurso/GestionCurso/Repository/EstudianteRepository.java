package com.gestionCurso.GestionCurso.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionCurso.GestionCurso.Models.EstudianteCursoModel;

public interface EstudianteRepository extends JpaRepository<EstudianteCursoModel, Long> {

}

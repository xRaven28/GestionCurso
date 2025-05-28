package com.gestionCurso.GestionCurso.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionCurso.GestionCurso.Models.CursoModel;

public interface CursoRepository extends JpaRepository<CursoModel, Long>{

}

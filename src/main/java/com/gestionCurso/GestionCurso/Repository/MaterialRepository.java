package com.gestionCurso.GestionCurso.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionCurso.GestionCurso.Models.MaterialCursoModel;

public interface MaterialRepository extends JpaRepository<MaterialCursoModel, Long>{

}

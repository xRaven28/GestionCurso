package com.gestionCurso.GestionCurso.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionCurso.GestionCurso.Models.HorarioModel;

public interface HorarioRepository extends JpaRepository<HorarioModel, Long> {

}

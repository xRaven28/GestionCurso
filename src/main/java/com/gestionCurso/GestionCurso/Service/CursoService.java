package com.gestionCurso.GestionCurso.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestionCurso.GestionCurso.DTO.CrearCurso;
import com.gestionCurso.GestionCurso.DTO.CursoDTO;
import com.gestionCurso.GestionCurso.Enum.EstadoCurso;

public interface CursoService {
    CursoDTO crearCurso(CrearCurso dto);
    CursoDTO obtenerCurso(Long id);
    CursoDTO editarCurso(Long id, CrearCurso dto);
    void eliminarCurso(Long id);
    Page<CursoDTO> listarCursos(Pageable pageable);
    CursoDTO cambiarEstado(Long id, EstadoCurso nuevoEstado);
    void inscribirEstudiante(Long cursoId, Long estudianteId);

}

package com.gestionCurso.GestionCurso.Controller;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import com.gestionCurso.GestionCurso.DTO.*;
import com.gestionCurso.GestionCurso.Service.CursoService;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class UsuarioController {
       private final CursoService cursoService;

    @PostMapping
    public CursoDTO crear(@Valid @RequestBody CrearCurso dto) {
        return cursoService.crearCurso(dto);
    }

    @GetMapping("/{id}")
    public CursoDTO obtener(@PathVariable Long id) {
        return cursoService.obtenerCurso(id);
    }

    @PutMapping("/{id}")
    public CursoDTO editar(@PathVariable Long id, @Valid @RequestBody CrearCurso dto) {
        return cursoService.editarCurso(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
    }

    @GetMapping
    public Page<CursoDTO> listar(Pageable pageable) {
        return cursoService.listarCursos(pageable);
    }

    @PostMapping("/{id}/inscribir")
    public void inscribirEstudiante(@PathVariable Long id, @RequestParam Long estudianteId) {
        cursoService.inscribirEstudiante(id, estudianteId);
    }

}

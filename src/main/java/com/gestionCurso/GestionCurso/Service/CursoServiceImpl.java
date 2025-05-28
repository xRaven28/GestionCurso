package com.gestionCurso.GestionCurso.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gestionCurso.GestionCurso.DTO.CrearCurso;
import com.gestionCurso.GestionCurso.DTO.CursoDTO;
import com.gestionCurso.GestionCurso.Enum.EstadoCurso;
import com.gestionCurso.GestionCurso.Models.CursoModel;
import com.gestionCurso.GestionCurso.Repository.CursoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
      private final CursoRepository cursoRepository;

    @Override
    public CursoDTO crearCurso(CrearCurso dto) {
        validarFechas(dto);
        CursoModel model = new CursoModel();
        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setCreadoPorId(dto.getCreadoPorId());
        model.setDuracionHoras(dto.getDuracionHoras());
        model.setPrecio(dto.getPrecio());
        model.setCapacidadMaxima(dto.getCapacidadMaxima());
        model.setFechaInicio(dto.getFechaInicio());
        model.setFechaFin(dto.getFechaFin());
        model.setEstado(EstadoCurso.BORRADOR);
        return toDTO(cursoRepository.save(model));
    }

    @Override
    public CursoDTO obtenerCurso(Long id) {
        return cursoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
    }

    @Override
    public CursoDTO editarCurso(Long id, CrearCurso dto) {
        validarFechas(dto);
        CursoModel model = cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setDuracionHoras(dto.getDuracionHoras());
        model.setPrecio(dto.getPrecio());
        model.setCapacidadMaxima(dto.getCapacidadMaxima());
        model.setFechaInicio(dto.getFechaInicio());
        model.setFechaFin(dto.getFechaFin());

        return toDTO(cursoRepository.save(model));
    }

    @Override
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Page<CursoDTO> listarCursos(Pageable pageable) {
        return cursoRepository.findAll(pageable).map(this::toDTO);
    }

    @Override
    public CursoDTO cambiarEstado(Long id, EstadoCurso nuevoEstado) {
        CursoModel model = cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
        model.setEstado(nuevoEstado);
        return toDTO(cursoRepository.save(model));
    }

    private CursoDTO toDTO(CursoModel model) {
        CursoDTO dto = new CursoDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setDuracionHoras(model.getDuracionHoras());
        dto.setPrecio(model.getPrecio());
        dto.setCapacidadMaxima(model.getCapacidadMaxima());
        dto.setFechaInicio(model.getFechaInicio());
        dto.setFechaFin(model.getFechaFin());
        dto.setEstado(model.getEstado());
        return dto;
    }

    private void validarFechas(CrearCurso dto) {
        if (dto.getFechaInicio().isAfter(dto.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
    }
}

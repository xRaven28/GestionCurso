package com.gestionCurso.GestionCurso.Service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.gestionCurso.GestionCurso.DTO.CrearCurso;
import com.gestionCurso.GestionCurso.DTO.CursoDTO;
import com.gestionCurso.GestionCurso.Enum.EstadoCurso;
import com.gestionCurso.GestionCurso.Models.CursoModel;
import com.gestionCurso.GestionCurso.Models.EstudianteCursoModel;
import com.gestionCurso.GestionCurso.Models.HorarioModel;
import com.gestionCurso.GestionCurso.Models.MaterialCursoModel;
import com.gestionCurso.GestionCurso.Repository.CursoRepository;
import com.gestionCurso.GestionCurso.Repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
  private final CursoRepository cursoRepository;
    private final EstudianteRepository estudianteCursoRepository;

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

        if (dto.getHorarios() != null) {
            List<HorarioModel> horarios = dto.getHorarios().stream().map(h -> {
                HorarioModel horario = new HorarioModel();
                horario.setDia(h.getDia());
                horario.setHoraInicio(h.getHoraInicio());
                horario.setHoraFin(h.getHoraFin());
                horario.setCurso(model);
                return horario;
            }).toList();
            model.setHorarios(horarios);
        }

        if (dto.getMateriales() != null) {
            List<MaterialCursoModel> materiales = dto.getMateriales().stream().map(m -> {
                MaterialCursoModel material = new MaterialCursoModel();
                material.setNombre(m.getNombre());
                material.setTipo(m.getTipo());
                material.setUrl(m.getUrl());
                material.setCurso(model);
                return material;
            }).toList();
            model.setMateriales(materiales);
        }

        CursoModel saved = cursoRepository.save(model);
        return toDTO(saved);
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

    @Override
    public void inscribirEstudiante(Long cursoId, Long estudianteId) {
        CursoModel curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        EstudianteCursoModel inscripcion = new EstudianteCursoModel();
        inscripcion.setCurso(curso);
        inscripcion.setIdEstudiante(estudianteId);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setEstado("INSCRITO");

        estudianteCursoRepository.save(inscripcion);
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

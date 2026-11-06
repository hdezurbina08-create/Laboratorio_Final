package com.laboratorio.profesores.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class profesorservice {

    private final List<ProfesorDTO> profesores = new ArrayList<>();
    private Long idContador = 1L;

    // Obtener todos los profesores
    public List<ProfesorDTO> listarTodos() {
        return profesores;
    }

    // Buscar profesor por ID
    public ProfesorDTO buscarPorId(Long id) {
        return profesores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Guardar un nuevo profesor
    public ProfesorDTO guardar(ProfesorDTO profesorDTO) {
        profesorDTO.setId(idContador++);
        profesores.add(profesorDTO);
        return profesorDTO;
    }

    // Actualizar un profesor existente
    public ProfesorDTO actualizar(Long id, ProfesorDTO profesorDTO) {
        for (int i = 0; i < profesores.size(); i++) {
            if (profesores.get(i).getId().equals(id)) {
                profesorDTO.setId(id);
                profesores.set(i, profesorDTO);
                return profesorDTO;
            }
        }
        return null;
    }

    // Eliminar un profesor
    public boolean eliminar(Long id) {
        return profesores.removeIf(p -> p.getId().equals(id));
    }
}
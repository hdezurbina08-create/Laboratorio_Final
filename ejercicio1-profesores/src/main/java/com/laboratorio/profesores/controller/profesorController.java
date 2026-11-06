package com.laboratorio.profesores.controller;

import com.laboratorio.profesores.dto.ProfesorDTO;
import com.laboratorio.profesores.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    // GET: Obtener todos los profesores
    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> listarTodos() {
        return ResponseEntity.ok(profesorService.listarTodos());
    }

    // GET: Buscar profesor por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> buscarPorId(@PathVariable Long id) {
        ProfesorDTO profesor = profesorService.buscarPorId(id);
        if (profesor != null) {
            return ResponseEntity.ok(profesor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // POST: Crear un nuevo profesor
    @PostMapping
    public ResponseEntity<ProfesorDTO> guardar(@Valid @RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO nuevoProfesor = profesorService.guardar(profesorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProfesor);
    }

    // PUT: Actualizar un profesor existente
    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO actualizado = profesorService.actualizar(id, profesorDTO);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DELETE: Eliminar un profesor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = profesorService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
package com.alura.foroHub.controller;


import com.alura.foroHub.dto.request.DatosRegistroTopico;
import com.alura.foroHub.dto.request.DatosActualizacionTopico;
import com.alura.foroHub.dto.response.DatosRespuestaTopico;
import com.alura.foroHub.model.Topico;
import com.alura.foroHub.service.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        return ResponseEntity.ok(topicoService.guardarTopico(datos));
    }

    @GetMapping
    public ResponseEntity<List<DatosRespuestaTopico>> listarTopicos() {
        return ResponseEntity.ok(topicoService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizacionTopico datos) {
        return ResponseEntity.ok(topicoService.actualizarTopico(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}


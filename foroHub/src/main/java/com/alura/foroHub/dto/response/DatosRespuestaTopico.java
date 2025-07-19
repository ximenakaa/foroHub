package com.alura.foroHub.dto.response;


public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String fecha,
        String curso,
        String autor,
        String status
) {}
